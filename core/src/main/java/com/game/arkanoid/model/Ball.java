package com.game.arkanoid.model;

/**
* Ball: represents the ball in the game Arkanoid.
*
* Main features:
* Inherits from { MovableObject}, has velocity, position, state.
* Moves on the screen and automatically bounces back when touching the edge.
* Has a target speed (targetSpeed), maintained after collision.
* Collision with Paddle will change the ball's flight angle, affected
* by the collision position and velocity of Paddle.
* 
*
* Usage:
* - Call {update(float)} in the game loop to update the movement and handle edge collisions.
* - Call {handlePaddle(Paddle)} when checking and handling collisions with Paddle.
*/

public class Ball extends MovableObject {
    private float targetSpeed;  // the velocity we want to assign to the ball

    public Ball(float targetSpeed) {
        super();
        this.targetSpeed = targetSpeed;
        setSpeed(this.targetSpeed);
        enableAcceleration(false);
    }

    // --- update ---
    @Override
    public void update(float dt) {
        super.update(dt);

        if(getXPos() < 0) {
            setPosition(0, getYPos());
            bounceX();
        } else if(getXPos() + getWidth() > getScreenWidth()) {
            setPosition(getScreenWidth() - getWidth(), getYPos());
            bounceX();
        }

        if(getYPos() < 0) {
            setStatus(false);
        } else if(getYPos() + getHeight() > getScreenHeight()) {
            setPosition(getXPos(), getScreenHeight() - getHeight());
            bounceY();
        }

    }

    // --- handle paddle ---
    public void handlePaddle(Paddle paddle) {
        // Handling collisions with the paddle at an angle
        if(getbounds().overlaps(paddle.getbounds())) { 
            float ballCenterX = getXPos() + getWidth() / 2f;
            float paddleCenterX = paddle.getXPos() + paddle.getWidth() / 2f;

            // take the ratio between the center of the ball and the paddle
            float offset = (ballCenterX - paddleCenterX) / (paddle.getWidth() / 2f); 
            //influence of paddle speed
            float influence = 0.25f * (paddle.getVelocityX() / Math.max(1f, paddle.getTargetSpeed())); 
            offset += influence;
            offset = Math.max(-1f, Math.min(1f, offset)); // limit values ​​in the range [-1, 1]

            float maxAngle = (float)Math.toRadians(60);
            float angle = offset * maxAngle;

            float DirX = (float)Math.sin(angle);
            float DirY = (float)Math.abs(Math.cos(angle));

            setDirection(DirX, DirY);
        }
    }

    // --- target speed ---
    public void setTargetSpeed(float targetSpeed)
    {
        this.targetSpeed = targetSpeed;
        setSpeed(this.targetSpeed);
    }

    public float getTargetSpeed() { return targetSpeed; }
}
