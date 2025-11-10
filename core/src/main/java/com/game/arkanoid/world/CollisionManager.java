package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
import com.game.arkanoid.engine.CollisionResult;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.ItemManager;
import com.game.arkanoid.output.AnimationFactory;
import com.game.arkanoid.output.SoundManager;

import java.util.List;
import java.util.Map;

public class CollisionManager {

    private final GameManager gameManager;
    private final Map<Integer, Integer> score;
    private ItemManager itemManager;


    public CollisionManager(GameManager gameManager, Map<Integer, Integer> score) {
        this.gameManager = gameManager;
        this.score = score;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    /**
     * Check Collision.
     * @param ball
     * @param bricks
     */
    public void checkCollision(Ball ball, List<Brick> bricks) {
        float delta = Gdx.graphics.getDeltaTime();
        Vector2 vectorMove = new Vector2(ball.getVelocityX() * delta, ball.getVelocityY() * delta);
        Vector2 vIn = new Vector2(ball.getVelocityX(), ball.getVelocityY());
        float minT = Float.MAX_VALUE;
        Brick hitBrick = null;
        CollisionResult bestResult = new CollisionResult();

        Brick penetrationBrick = null;
        CollisionResult insideResult = new CollisionResult();


        for (Brick brick : bricks) {
            if (!brick.getStatus()) continue;
            CollisionResult collisionResult = new CollisionResult();
            if (Collision.IntersectMovingSphereAABB(ball.getBoundCircle(), vectorMove, brick.getBounds(), collisionResult) == 1) {

                if (collisionResult.typeCollision == CollisionResult.TypeCollision.INSIDE) {
                    penetrationBrick = brick;
                    insideResult.timeCollision = collisionResult.timeCollision;
                    insideResult.typeCollision = collisionResult.typeCollision;
                    insideResult.normal.set(collisionResult.normal);
                    break;
                }

                if (collisionResult.timeCollision < minT) {
                    minT = collisionResult.timeCollision;
                    hitBrick = brick;
                    bestResult.timeCollision = collisionResult.timeCollision;
                    bestResult.typeCollision = collisionResult.typeCollision;
                    bestResult.normal.set(collisionResult.normal);
                }
            }
        }

        if (penetrationBrick != null) {
            System.out.println("Exception ball inside brick");
            pushBallOut(ball, penetrationBrick.getBounds(), insideResult);
            if (insideResult.typeCollision == CollisionResult.TypeCollision.INSIDE_X) {
                ball.bounceX();
            } else {
                ball.bounceY();
            }
            handleBrickHit(penetrationBrick, ball, bricks);

            return;
        }
        if (hitBrick != null) {
            float best = bestResult.timeCollision;
            ball.setPosition(ball.getXPos() + best * vectorMove.x,
                ball.getYPos() + best * vectorMove.y);

            Vector2 n = new Vector2(bestResult.normal);
            float vInDotN = vIn.dot(n);
            if (vInDotN > 0 && bestResult.typeCollision != CollisionResult.TypeCollision.VERTEX) {
                float vOutX = vIn.x - 2 * vInDotN * n.x;
                float vOutY = vIn.y - 2 * vInDotN * n.y;
                ball.setVelocity(vOutX, vOutY);
            }


            if (bestResult.typeCollision == CollisionResult.TypeCollision.VERTICAL_EDGE) {
                System.out.println("Vertical Edge");
            } else if (bestResult.typeCollision == CollisionResult.TypeCollision.HORIZONTAL_EDGE) {
                System.out.println("Horizontal Edge");
            } else if (bestResult.typeCollision == CollisionResult.TypeCollision.VERTEX) {
                System.out.println("Vertex");
                ball.bounceX();
                ball.bounceY();
            }
            handleBrickHit(hitBrick, ball, bricks);
        }
    }

    /**
     * Handle after collision(Explosion or Decrease Blood)
     * @param hitBrick
     * @param ball
     * @param bricks
     */
    private void handleBrickHit(Brick hitBrick, Ball ball, List<Brick> bricks) {
        hitBrick.setBlood(hitBrick.getBlood() - 1);
        float hitCenterX = hitBrick.getXPos() + hitBrick.getWidth() / 2;
        float hitCenterY = hitBrick.getYPos() + hitBrick.getHeight() / 2;


        if (ball.getFlag() == 1) {
            for (Brick brick : bricks) {
                if (!brick.getStatus() || brick == hitBrick) continue;

                float brickCenterX = brick.getXPos() + brick.getWidth() / 2;
                float brickCenterY = brick.getYPos() + brick.getHeight() / 2;
                float dx = hitCenterX - brickCenterX;
                float dy = hitCenterY - brickCenterY;
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                float explosionRadius = 100.0f;
                if (distance < explosionRadius) {
                    brick.setBlood(brick.getBlood() - 1);
                }
            }
            AnimationFactory.call(AnimationFactory.TypeAnimation.FIRE_ANIM,
                (int) hitBrick.getXPos() + hitBrick.getWidth() / 2,
                (int) hitBrick.getYPos() + hitBrick.getHeight() / 2 + 60);
            SoundManager.getInstance().playSound("BOMB");


            ball.setFlag(0);
        }


        AnimationFactory.call(AnimationFactory.TypeAnimation.CLOUD_ANIM,
         (int) hitBrick.getXPos() + hitBrick.getWidth() / 2,
         (int) hitBrick.getYPos() + hitBrick.getHeight() / 2);

        SoundManager.getInstance().playSound("HIT");
    }

    /**
     * Push Ball Out.
     * Call this when ball inside brick
     * @param ball
     * @param brickBounds
     * @param result
     */
    private void pushBallOut(Ball ball, Rectangle brickBounds, CollisionResult result) {
        Circle ballCircle = ball.getBoundCircle();
        float cX = ballCircle.x;
        float cY = ballCircle.y;

        float dist_to_left = cX - brickBounds.x;
        float dist_to_right = (brickBounds.x + brickBounds.width) - cX;
        float dist_to_bottom = cY - brickBounds.y;
        float dist_to_top = (brickBounds.y + brickBounds.height) - cY;

        float min_dist = dist_to_left;
        Vector2 normal = new Vector2(-1, 0); // Đẩy trái
        result.typeCollision = CollisionResult.TypeCollision.INSIDE_X;

        if (dist_to_right < min_dist) {
            min_dist = dist_to_right;
            normal.set(1, 0); // Đẩy phải
            result.typeCollision = CollisionResult.TypeCollision.INSIDE_X;
        }
        if (dist_to_bottom < min_dist) {
            min_dist = dist_to_bottom;
            normal.set(0, -1); // Đẩy dưới
            result.typeCollision = CollisionResult.TypeCollision.INSIDE_Y;
        }
        if (dist_to_top < min_dist) {
            min_dist = dist_to_top;
            normal.set(0, 1); // Đẩy trên
            result.typeCollision = CollisionResult.TypeCollision.INSIDE_Y;
        }
        final float EPSILON = 5f;
        ball.setPosition(ball.getXPos() + normal.x * (min_dist + EPSILON),
            ball.getYPos() + normal.y * (min_dist + EPSILON));
    }
}
