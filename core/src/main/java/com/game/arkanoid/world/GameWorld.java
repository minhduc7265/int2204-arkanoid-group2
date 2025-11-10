package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.ItemManager;
import com.game.arkanoid.model.Paddle;
import com.game.arkanoid.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class GameWorld {
    private GameManager gameManager;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private LevelLoader levelLoader;
    private ItemManager itemManager;
    private final Map<Integer, Integer> Score;
    private boolean isPushBall;
    private static final int PADDLE_WID = 122;
    private static final int PADDLE_HEI = 44;


    private CollisionManager collisionManager;

    public GameWorld() {
        this.levelLoader = new LevelLoader();
        this.gameManager = GameManager.getGameManager();
        this.gameManager.startNewGame();
        this.itemManager = new ItemManager();
        this.Score = new HashMap<>();
        this.collisionManager = new CollisionManager(gameManager, Score);
        this.isPushBall = false;
        Score.put(1, 50);
        Score.put(2, 60);
        Score.put(3, 70);
        Score.put(4, 90);
        Score.put(5, 120);
        Score.put(6, 0);

        paddle = new Paddle(0, 0, PADDLE_WID, PADDLE_HEI);
        paddle.setyFinal(100f);
        ball = null;
        bricks = new ArrayList<>();


        initializeLevel(gameManager.getCurrentLevel());
    }

    public void resetBallPosition() {
        isPushBall = false;
        ball = new Ball(-100f, -100f, 20, 19, 400f);
    }

    public void initializeLevel(int level) {
        bricks = levelLoader.loadLevel(level);
        resetBallPosition();
        if (itemManager != null) {
            itemManager.shutdown();
        }
        itemManager = new ItemManager();
        collisionManager.setItemManager(itemManager);
        gameManager.writeData();
    }


    //Main update
    public void update(float delta) {
        handleInput();
        updateEntities(delta);
        handleLevelProgression();
    }


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


    public List<Item> getPowers() {
        return itemManager.getActivePowerUps();
    }

    private void handleInput() {
        paddle.updateUseMouse(getMouseX());
    }

    private void updateEntities(float delta) {
        handleBall(delta);
        itemManager.checkCollisionsAndApplyEffects(paddle, ball);
        //MultiThread
        itemManager.updateAllAsync();
        itemManager.removeExpiredItems(paddle, ball);
        //itemManager.updateAll(paddle, ball);
        checkBricks();
        checkBall();
    }


    private void checkBricks() {
        Iterator<Brick> iteratorBrick = bricks.iterator();
        while (iteratorBrick.hasNext()) {
            Brick currentBrick = iteratorBrick.next();
            if (currentBrick.getFlag() != 6 && currentBrick.getBlood() <= 0) {
                currentBrick.setStatus(false);
                itemManager.createPowerUpFromBrick(currentBrick);
                gameManager.addScore(Score.get(currentBrick.getFlag()));
            }
            if (!currentBrick.getStatus()) {
                iteratorBrick.remove();
            }
        }
    }

    private boolean areAllBricksCleared() {
        for (Brick brick : bricks) {
            if (brick.getFlag() != 6 && brick.getStatus()) {
                return false;
            }
        }
        return true;

    }

    private void handleBall(float delta) {
        if (ball.getStatus()) {
            if (isPushBall) {
                ball.update(delta);
                collisionManager.checkCollision(ball, bricks);
                ball.handlePaddle(paddle);
            } else {
                ball.setPosition(paddle.getXPos() + paddle.getWidth() / 2, paddle.getYPos() + paddle.getHeight() + 10);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    isPushBall = true;
                }
            }

        }
    }

    private void handleLevelProgression() {
        if (areAllBricksCleared()) {
            gameManager.goToNextLevel();
            initializeLevel(gameManager.getCurrentLevel());
        }
    }

    private void checkBall() {
        if (!ball.getStatus()) {
            gameManager.loseLife();
            resetBallPosition();
        }
    }

    private void handleGameOver() {
        if (gameManager.getGameState() == GameManager.GameState.GAMEOVER) {


            gameManager.startNewGame();
            initializeLevel(gameManager.getCurrentLevel());
        }
    }

    public void shutdown() {
        if (itemManager != null) {
            itemManager.shutdown();
        }

    }


}
