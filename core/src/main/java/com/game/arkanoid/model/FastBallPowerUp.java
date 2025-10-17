package com.game.arkanoid.model;

/**
* PowerUp increases Ball's movement speed.
* The effect is temporary and is undone after the duration expires.
*/
public class FastBallPowerUp extends PowerUp { 
    private static final float SPEED_MULTIPLIER = 1.5f;
    private static final int DEFAULT_DURATION = 480;

    /**
    * Initialize FastBallPowerUp.
    * x Initial x coordinate.
    * y Initial y coordinate.
    */
    public FastBallPowerUp(float x, float y) {
        super(x, y, PowerUpType.FAST_BALL, DEFAULT_DURATION);
    }

   @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        float newSpeed = ball.getSpeed() * SPEED_MULTIPLIER;
        ball.setSpeed(newSpeed);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        ball.resetSpeed();
    }
}