package com.game.arkanoid.model;

/**
* PowerUp increases the width of the Paddle.
* The effect is temporary and is undone after the timeout.
*/
public class ExpandPaddlePowerUp extends PowerUp { 
    private static final int EXPAND_AMOUNT = 30;
    private static final int DEFAULT_DURATION = 600;
   
    /**
    * Initialize ExpandPaddlePowerUp.
    * x Initial x coordinate.
    * y Initial y coordinate.
    */
    public ExpandPaddlePowerUp(float x, float y) {
        super(x, y, PowerUpType.EXPAND_PADDLE , DEFAULT_DURATION);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        paddle.setWidth(paddle.getWidth() + EXPAND_AMOUNT);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        paddle.resetWidth();
    }
}