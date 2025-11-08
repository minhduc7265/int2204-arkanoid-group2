package com.game.arkanoid.model;

import com.badlogic.gdx.math.Circle;

/**
 * Ball: represents the ball in the game Arkanoid.
 * <p>
 * Main features:
 * Inherits from { MovableObject}, has velocity, position, state.
 * Moves on the screen and automatically bounces back when touching the edge.
 * Has a target speed (targetSpeed), maintained after collision.
 * Collision with Paddle will change the ball's flight angle, affected
 * by the collision position and velocity of Paddle.
 * <p>
 * <p>
 * Usage:
 * - Call {update(float)} in the game loop to update the movement and handle edge collisions.
 * - Call {handlePaddle(Paddle)} when checking and handling collisions with Paddle.
 */

public class Ball extends MovableObject {
    private float targetSpeed;  // the velocity we want to assign to the ball
    private final float initialSpeed;
    private Circle boundCircle;
    private int flag;


    public Ball(float x, float y, int weight, int height, float initialSpeed) {
        super(x, y, weight, height);
        this.boundCircle = new Circle(x, y, 11f);
        this.initialSpeed = initialSpeed;
        setSpeed(initialSpeed);
        enableAcceleration(false);
        this.flag = 0;
    }

    // --- update ---
    @Override
    public void update(float dt) {
        super.update(dt);

        if (getXPos() < 0) {
            setPosition(0, getYPos());
            bounceX();
        } else if (getXPos() + getWidth() > getScreenWidth()) {
            setPosition(getScreenWidth() - getWidth(), getYPos());
            bounceX();
        }

        if (getYPos() < 0) {
            setStatus(false);
        } else if (getYPos() + getHeight() > getScreenHeight()) {
            setPosition(getXPos(), getScreenHeight() - getHeight());
            bounceY();
        }

    }

    // --- handle paddle ---
    public void handlePaddle(Paddle paddle) {
        // Handling collisions with the paddle at an angle
        if (collidesWith(paddle)) {
            float ballCenterX = getXPos() + getWidth() / 2f;
            float paddleCenterX = paddle.getXPos() + paddle.getWidth() / 2f;

            // take the ratio between the center of the ball and the paddle
            float offset = (ballCenterX - paddleCenterX) / (paddle.getWidth() / 2f);
            //influence of paddle speed
            float influence = 0.25f * (paddle.getVelocityX() / Math.max(1f, paddle.getTargetSpeed()));
            offset += influence;
            offset = Math.max(-1f, Math.min(1f, offset)); // limit values ​​in the range [-1, 1]

            float maxAngle = (float) Math.toRadians(60);
            float angle = offset * maxAngle;

            float DirX = (float) Math.sin(angle);
            float DirY = (float) Math.abs(Math.cos(angle));

            setDirection(DirX, DirY);
        }
    }

    // --- target speed ---
    public void setTargetSpeed(float targetSpeed) {
        this.targetSpeed = targetSpeed;
        setSpeed(this.targetSpeed);
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public float getTargetSpeed() {
        return targetSpeed;
    }

    public void resetSpeed() {
        setSpeed(this.initialSpeed);
    }


    @Override
    public float getXPos() {
        return this.boundCircle.x;
    }

    @Override
    public float getYPos() {
        return this.boundCircle.y;
    }

    @Override
    public void setPosition(float x_pos, float y_pos) {
        super.setPosition(x_pos, y_pos);
        this.boundCircle.x = x_pos;
        this.boundCircle.y = y_pos;
    }


    public Circle getBoundCircle() {
        return this.boundCircle;
    }
}
