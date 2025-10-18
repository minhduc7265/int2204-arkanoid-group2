package com.game.arkanoid.world;

import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.Paddle;
import java.util.List;

public class GameWorld {
    private GameManager gameManager;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;

    public GameWorld() {
        this.gameManager = GameManager.getGameManager();
        this.gameManager.startNewGame();

        // TODO: Khởi tạo paddle, ball
        // paddle = new Paddle(...);
        // ball = new Ball(...);
        
        loadLevel(gameManager.getCurrentLevel());
    }

    public void loadLevel(int level) {
        //bricks = LevelLoader.load(level);
        // TODO: Reset vị trí bóng và paddle
    }

    public void update(float delta) {
        // TODO: Xử lý input để di chuyển paddle
        // TODO: Cập nhật vị trí paddle.update(delta) và ball.update(delta)
        // TODO: Xử lý va chạm
        // TODO: Nếu hết gạch -> gameManager.goToNextLevel(); loadLevel(...);
        // TODO: Nếu bóng rơi -> gameManager.loseLife(); resetBallPosition(...);
    }
    
    // Getter để MasterRenderer có thể lấy đối tượng để vẽ
    public Paddle getPaddle() { return paddle; }
    public Ball getBall() { return ball; }
    public List<Brick> getBricks() { return bricks; }
}