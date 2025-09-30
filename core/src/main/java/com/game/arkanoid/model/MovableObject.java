package com.game.arkanoid.model;

import java.util.*;

public class MovableObject extends GameObject {
    private float vx;
    private float vy;

    private final float maxSpeed = 400;

    public MovableObject() {
        super();
        this.vx = 0f;
        this.vy = 0f;
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

    public float getVX() { return this.vx; }
    public float getVY() { return this.vy; }

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

    
    // --- Private utilities ---
    private float[] clampToMaxSpeed(float vx, float vy) {
        float speed = (float) Math.sqrt(vx*vx + vy*vy);
        if(speed <= maxSpeed) return new float[]{vx , vy};
        float scale = maxSpeed/speed;
        return new float[]{vx*scale , vy*scale};
    }
}
