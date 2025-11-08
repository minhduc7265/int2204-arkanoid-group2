package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.*;
import com.game.arkanoid.output.AnimationFactory;
import com.game.arkanoid.output.Score;
import com.game.arkanoid.output.SoundManager;

import java.util.*;

public class GameWorld {
    private GameManager gameManager;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private LevelLoader levelLoader;
    private ItemManager itemManager;
    private final Map<Integer, Integer> Score;
    private boolean isPushBall;



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

        paddle = new Paddle(0, 0, 122, 44);
        paddle.setyFinal(100f);
        ball = null;
        bricks = new ArrayList<>();


        initializeLevel(gameManager.getCurrentLevel());
    }

    public void resetBallPosition() {
        isPushBall = false;
        ball = new Ball(-100f, -100f, 20, 19, 500f);
    }

    public void initializeLevel(int level) {
        bricks = levelLoader.loadLevel(level);
        resetBallPosition();
        itemManager = new ItemManager();
        collisionManager.setItemManager(itemManager);
        gameManager.writeData();
    }



    //Main update
    public void update(float delta) {
        handleInput();
        updateEntities(delta);
        //if (!ball.getStatus()) ball = null;
        handleLevelProgression();
        handleGameOver();
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


    public List<Item> getPowers() {
        return itemManager.getActivePowerUps();
    }

    private void handleInput() {
        paddle.updateUseMouse(getMouseX());
    }

    private void updateEntities(float delta) {
        handleBall(delta);
        itemManager.checkCollisionsAndApplyEffects(paddle, ball);
        itemManager.updateAll(paddle, ball);
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
            if(isPushBall) {
                collisionManager.checkCollision(ball, bricks);
                ball.handlePaddle(paddle);
                ball.update(delta);
            }
            else{
                ball.setPosition(paddle.getXPos() + paddle.getWidth() / 2, paddle.getYPos() + paddle.getHeight() + 10);
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
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


}
