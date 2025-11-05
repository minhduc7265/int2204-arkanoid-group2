package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.*;
import com.game.arkanoid.output.AnimationRender;
import com.game.arkanoid.output.SoundManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld {
    private GameManager gameManager;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private LevelLoader levelLoader;
    private PowerUpManager powerUpManager;

    public GameWorld() {
        this.levelLoader = new LevelLoader();
        this.gameManager = GameManager.getGameManager();
        this.gameManager.startNewGame();
        this.powerUpManager = new PowerUpManager();

        // TODO: Khởi tạo paddle, ball
        paddle = new Paddle(0, 0, 122, 44);
        paddle.setyFinal(100f);
        ball = null;
        bricks = new ArrayList<>();
        // paddle = new Paddle(...);
        // ball = new Ball(...);

        loadLevel(gameManager.getCurrentLevel());
    }

    public void resetBallPosition() {
        ball = new Ball(200f, 200f, 20, 19, 400f);
    }

    public void loadLevel(int level) {
        bricks = levelLoader.loadLevel(level);

        // TODO: Load ball
        ball = new Ball(700f, 700f, 20, 19, 400f);


        powerUpManager = new PowerUpManager();

        // TODO: Reset vị trí bóng và paddle
    }



    public void update(float delta) {
        paddle.updateUseMouse(getMouseX());
        powerUpManager.checkCollisionsAndApplyEffects(paddle, ball);
        powerUpManager.updateAll(paddle, ball);

        if (!ball.getStatus()) ball = null;

        Iterator<Brick> iteratorBrick = bricks.iterator();
        while (iteratorBrick.hasNext()) {
            Brick currentBrick = iteratorBrick.next();
            if (!currentBrick.getStatus()) {
                iteratorBrick.remove();
            }
        }
        if (ball != null) {
            checkCollision(ball, bricks);

            ball.update(delta);

            ball.handlePaddle(paddle);
        }


        if (bricks.isEmpty()) {
            gameManager.goToNextLevel();
            loadLevel(gameManager.getCurrentLevel());
        }

        if (ball == null) {
            gameManager.loseLife();
            resetBallPosition();
        }


        if(gameManager.getGameState() == GameManager.GameState.GAMEOVER) {
            gameManager.startNewGame();
            loadLevel(gameManager.getCurrentLevel());
        }


        // TODO: Xử lý input để di chuyển paddle
        // TODO: Cập nhật vị trí paddle.update(delta) và ball.update(delta)
        // TODO: Xử lý va chạm
        // TODO: Nếu hết gạch -> gameManager.goToNextLevel(); loadLevel(...);
        // TODO: Nếu bóng rơi -> gameManager.loseLife(); resetBallPosition(...);
    }

    // Getter để MasterRenderer có thể lấy đối tượng để vẽ
    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBalls() {
        return ball;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public float getMouseX() {
        return (float) Gdx.input.getX();
    }


    public List<PowerUp> getPowers() {return powerUpManager.getActivePowerUps(); }

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
        hitBrick.setStatus(false);

        powerUpManager.createPowerUpFromBrick(hitBrick);


        AnimationRender.getListAnimationRender().add(new AnimationRender("CLOUD_ANIM",
            (int)hitBrick.getXPos() + hitBrick.getWidth() / 2,
            (int)hitBrick.getYPos() + hitBrick.getHeight() / 2));
        /*AnimationRender.getListAnimationRender().add(new AnimationRender("FIRE_ANIM",
            (int)hitBrick.getXPos() + hitBrick.getWidth() / 2,
            (int)hitBrick.getYPos() + hitBrick.getHeight() / 2));*/

        SoundManager.getInstance().playSound("HIT");
    }


}
