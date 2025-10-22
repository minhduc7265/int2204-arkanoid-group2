package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.Paddle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld {
    private GameManager gameManager;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private LevelLoader levelLoader;

    public GameWorld() {
        this.levelLoader = new LevelLoader();
        this.gameManager = GameManager.getGameManager();
        this.gameManager.startNewGame();

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

        // TODO: Reset vị trí bóng và paddle
    }

    public void update(float delta) {
        paddle.updateUseMouse(getMouseX());

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


    public void checkCollision(Ball ball, List<Brick> bricks) {

        Vector2 vectorMove;
        float[] timeInfo = new float[2];

        for (Brick brick : bricks) {
            vectorMove = new Vector2(ball.getVelocityX() * 1/60f, ball.getVelocityY() * 1/60f);
            if (!brick.getStatus()) {
                continue;
            }
            if (Collision.IntersectMovingSphereAABB(ball.getBoundCircle(), vectorMove, brick.getBounds(), timeInfo) == 1) {
                ball.setPosition(ball.getXPos() + timeInfo[0] * vectorMove.x, ball.getYPos() + timeInfo[0] * vectorMove.y);
                if ((int) timeInfo[1] == 0) {
                    ball.bounceX();
                } else if ((int) timeInfo[1] == 1) {
                    ball.bounceY();
                }
                brick.setStatus(false);
            }
        }
    }

}
