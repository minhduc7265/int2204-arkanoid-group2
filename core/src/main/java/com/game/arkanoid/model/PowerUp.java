package com.game.arkanoid.model;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUp extends GameObject {
    protected double x, y;
    protected double width, height;
    protected double speedY;
    protected boolean active;
    protected boolean used;
    protected Color color;

    public PowerUp(double startX, double startY, double w, double h, double speedY) {
        this.x = startX;
        this.y = startY;
        this.width = w;
        this.height = h;
        this.speedY = speedY;
        this.active = true;
        this.used = false;
        this.color = Color.WHITE;
    }

    public abstract void apply(Ball ball, Paddle paddle);

    public void update(double deltaTime) {
        if (active) {
            y += speedY * deltaTime;
        }
    }

    public void draw(Graphics g) {
        if (active) {
            g.setColor(color);
            g.fillRect((int) x, (int) y, (int) width, (int) height);
        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean isUsed() {
        return used;
    }

    public void deactive() {
        active = false;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public boolean checkCollision(Paddle paddle) {
        return this.getBounds().intersects(paddle.getBounds());
    }
}
