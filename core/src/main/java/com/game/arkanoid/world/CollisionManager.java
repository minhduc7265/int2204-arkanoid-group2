package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
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

    public void checkCollision(Ball ball, List<Brick> bricks) {
        float delta = Gdx.graphics.getDeltaTime();
        Vector2 vectorMove = new Vector2(ball.getVelocityX() * delta, ball.getVelocityY() * delta);
        float[] timeInfo = new float[2];
        float minT = Float.MAX_VALUE;
        Brick hitBrick = null;
        float[] bestInfo = new float[2];

        for (Brick brick : bricks) {
            if (!brick.getStatus()) continue;
            if (Collision.IntersectMovingSphereAABB(ball.getBoundCircle(), vectorMove, brick.getBounds(), timeInfo) == 1) {
                if (timeInfo[0] < minT) {
                    minT = timeInfo[0];
                    hitBrick = brick;
                    bestInfo[0] = timeInfo[0];
                    bestInfo[1] = timeInfo[1];
                }
            }
        }

        if (hitBrick == null) return;

        ball.setPosition(ball.getXPos() + bestInfo[0] * vectorMove.x,
            ball.getYPos() + bestInfo[0] * vectorMove.y);

        if ((int) bestInfo[1] == 1) {
            ball.bounceX();
            System.out.println("Va cạnh dọc");
        } else if ((int) bestInfo[1] == 2) {
            ball.bounceY();
            System.out.println("Va cạnh ngang");
        } else if ((int) bestInfo[1] == 3) {
            ball.bounceY();
            ball.bounceX();
            System.out.println("Va vào đỉnh");
        } else {
            ball.bounceX();
            ball.bounceY();
            System.out.println(bestInfo[1]);
            System.out.println("Tình trạng bóng vào trong viên gạch");

        }
        hitBrick.setBlood(hitBrick.getBlood() - 1);

        // 1. Lấy tâm của hitBrick
        float hitCenterX = hitBrick.getXPos() + hitBrick.getWidth() / 2;
        float hitCenterY = hitBrick.getYPos() + hitBrick.getHeight() / 2;


        if(ball.getFlag() == 1) {
            for(Brick brick: bricks){
                if(!brick.getStatus() || brick == hitBrick) continue;
                float brickCenterX = brick.getXPos() + brick.getWidth() / 2;
                float brickCenterY = brick.getYPos() + brick.getHeight() / 2;
                float dx = hitCenterX - brickCenterX;
                float dy = hitCenterY - brickCenterY;
                float distance = (float) Math.sqrt(dx*dx + dy*dy); // Tính khoảng cách
                float explosionRadius = 100.0f; // Ví dụ: bán kính 100 pixel
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
}
