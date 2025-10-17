package com.game.arkanoid.model;

/**
* Paddle: represents the barrier in the game Arkanoid.
*
* Main features:
* Inherits from {MovableObject}, has position, velocity and acceleration.
* Moves along the X axis only at a fixed Y position (yFinal).
* Has a speed limit (targetSpeed) and is controlled by acceleration.
* Keep in screen: automatically stops when touching the left/right border.
* 
*
* Usage:
* - Call {update(float)} in the game loop to update the movement.
* - Call {moveLeft()}, {moveRight()} or {stop()} to control the paddle.
*/

public class Paddle extends MovableObject {
    private float targetSpeed;   // maximum speed
    private float acceleration;  // key press acceleration
    private float yFinal;        // fixed Y position of the paddle
    private final int initialWidth; 
    
    public Paddle(float x, float y, int width, int height) {
        super(x, y, width, height, "PaddleTexture");
        this.acceleration = 600f; 
        this.initialWidth = width;
    }

    @Override
    public void update(float dt) {
        // Update velocity from acceleration
        setVelocity(getVelocityX() + getAccelerationX() * dt, 0);

        // Speed ​​limit in [-targetSpeed, targetSpeed]
        float vx = Math.max(-getMaxSpeed(), Math.min(getVelocityX(), getMaxSpeed()));
        setVelocity(vx, 0);

        // Update location
        float newX = getXPos() + getVelocityX() * dt;
        setPosition(newX, yFinal);

        // Keep the paddle in the screen
        if (getXPos() < 0) {
            setPosition(0, yFinal);
            setVelocity(0, 0);
        } else if (getXPos() + getWidth() > screenWidth) {
            setPosition(screenWidth - getWidth(), yFinal);
            setVelocity(0, 0);
        }
    }

    // --- control ---
    public void moveLeft() {
        setAcceleration(-acceleration, 0);
        if (getVelocityX() < -targetSpeed) {
            setVelocity(-targetSpeed, 0);
        }
    }

    public void moveRight() {
        setAcceleration(acceleration, 0);
        if (getVelocityX() > targetSpeed) {
            setVelocity(targetSpeed, 0);
        }
    }

    public void stop() {
        setVelocity(0, 0);
        setAcceleration(0, 0);
    }

    public float getTargetSpeed() { return targetSpeed; }

    // Method to reset to original size
    public void resetWidth() {
        setWidth(this.initialWidth);
    }
}
