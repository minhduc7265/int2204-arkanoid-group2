package com.game.arkanoid.model;

/**
* MovableObject: base class for movable objects (Ball, Paddle).
* - Stores velocity (vx, vy), (optional) acceleration (ax, ay), speed limit.
* - Updates position in delta time (seconds).
* - Supports bounce (reversal direction) when colliding with edges or other objects.
*/
import java.util.*;

public class MovableObject extends GameObject {
    private float vx;   //Velocity of x
    private float vy;   //Velocity of y

    
    private float ax;   // acceleration x (px/s^2)
    private float ay;   // acceleration y (px/s^2)
    private boolean useAcceleration = false;


    private float maxSpeed = Float.POSITIVE_INFINITY;

    public MovableObject() {
        super();
        this.vx = 0f;
        this.vy = 0f;
        this.ax = 0f;
        this.ay = 0f;
    }

    public MovableObject(float x, float y, int width, int height, String textureID) {
        setBounds(x, y, width, height);
        setTextureID(textureID);        
        this.vx = 0f;
        this.vy = 0f;
        this.ax = 0f;
        this.ay = 0f;
    }
    // --- Velcocity ---
    public void setVelocity(float vx, float vy) {
        float[] clamp = clampToMaxSpeed(vx, vy); 
        this.vx = clamp[0];
        this.vy = clamp[1];
    }

    
    public void addVelocity(float dvx, float dvy) {
        setVelocity(this.vx + dvx, this.vy + dvy);
    }

    public float getVelocityX() { return this.vx; }
    public float getVelocityY() { return this.vy; }

    // --- Speed ---
    public float getSpeed()
    {
        return (float)Math.sqrt(vx*vx + vy*vy);
    }

    /** 
     * Đặt tốc độ theo hướng hiện tại. Nếu hướng chưa có (vx=vy=0),
     * mặc định hướng lên trên.
     */

    public void setSpeed(float speed)
    {
        float len = getSpeed();
        if(len < 1e-6f) {
             // hướng mặc định khi chưa có hướng
            this.vy = speed;
            this.vx = 0f;
        } else {
            float nx = vx / len;
            float ny = vy / len;
            setVelocity(nx * speed, ny * speed);
        }
    }

    // --- Direction ---

    public void setDirection(float DirA, float DirB) {
        float mag = (float)Math.sqrt(DirA * DirA + DirB * DirB);
        if(mag < 1e-6f) return;
        float speed = getSpeed();
        setVelocity((DirA / mag) * speed , (DirB / mag) * speed);
    }

    
    // --- Acceleration ---
    public void enableAcceleration(boolean enable) { this.useAcceleration = enable; }
    public boolean isAccelerationEnabled() { return useAcceleration; }

    public void setAcceleration(float ax, float ay) {
        this.ax = ax;
        this.ay = ay;
    }

    public void addAcceleration(float dax, float day) {
        this.ax += dax;
        this.ay += day;
    }

    public float getAccelerationX() { return ax; }
    public float getAccelerationY() { return ay; }


    // --- Max speed ---
    public void setMaxSpeed(float maxSpeed) { this.maxSpeed = maxSpeed <= 0 ? 0 : maxSpeed; }
    public float getMaxSpeed() { return maxSpeed; }

    // --- update ---
    
    public void update(float dt) {
        if (!getStatus()) return;
        if (dt <= 0f) return;
        if (useAcceleration) {
            float nvx = vx + ax * dt;
            float nvy = vy + ay * dt;
            float[] clamped = clampToMaxSpeed(nvx, nvy);
            vx = clamped[0];
            vy = clamped[1];
        } else {
            float[] clamped = clampToMaxSpeed(vx, vy);
            vx = clamped[0];
            vy = clamped[1];
        }
        float newX = getXPos() + vx * dt;
        float newY = getYPos() + vy * dt;
        setPosition(newX, newY);
    }


    @Override
    public void update() {
        update(1/60f);
    }

    /*change direction along the x */
    public void bounceX() {
        this.vx = -this.vx;
    }
    /*change direction along the y */
    public void bounceY() {
        this.vy = -this.vy;
    }

    // --- Private utilities ---
    private float[] clampToMaxSpeed(float vx, float vy) {
        if (Float.isInfinite(maxSpeed)) return new float[]{vx, vy};
        float speed = (float)Math.sqrt(vx * vx + vy * vy);
        if (speed <= maxSpeed) return new float[]{vx, vy};
        if (speed < 1e-6f) return new float[]{0f, 0f};
        float scale = maxSpeed / speed;
        return new float[]{vx * scale, vy * scale};
    }
}
