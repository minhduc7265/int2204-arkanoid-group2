package com.game.arkanoid.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 * Base class for all game objects.
 * Stores position, size, state, and texture information.
 */

public abstract class GameObject {
    private Rectangle bounds; // rectangle stores the coordinates and dimensions of the object
    private boolean status;
    public static int screenWidth; // Common screen width for all objects inherited from GameObject
    public static int screenHeight; //Common screen height for all objects inherited from GameObject
    public Color color;

    public GameObject() {
        this.bounds = new Rectangle();
        this.bounds.x = 0f;
        this.bounds.y = 0f;
        this.bounds.width = 0;
        this.bounds.height = 0;
        this.status = true;
        this.color = Color.WHITE;
    }

    public GameObject(float x, float y, int width, int height) {
        this.bounds = new Rectangle();
        this.bounds.x = x;
        this.bounds.y = y;
        this.bounds.width = width;
        this.bounds.height = height;
        this.status = true;
        this.color = Color.WHITE;
    }

    public void setArea(int width, int height) {
        this.bounds.setSize(width, height);
    }

    public void setPosition(float x_pos, float y_pos) {
        this.bounds.x = x_pos;
        this.bounds.y = y_pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(float r, float g, float b, float a) {
        this.color = new Color(r, g, b, a);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setWidth(int width) {
        this.bounds.width = width;
    }

    public void setHeight(int height) {
        this.bounds.height = height;
    }

    public boolean getStatus() {
        return status;
    }

    public float getXPos() {
        return this.bounds.x;
    }

    public float getYPos() {
        return this.bounds.y;
    }

    public int getWidth() {
        return (int) this.bounds.width;
    }

    public int getHeight() {
        return (int) this.bounds.height;
    }


    public void update() {

    }

    public void setBounds(float x, float y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenWidth(int ScreenWidth) {
        screenWidth = ScreenWidth;
    }

    public static void setScreenHeight(int ScreenHeight) {
        screenHeight = ScreenHeight;
    }

    public boolean collidesWith(GameObject other) {
        return this.bounds.overlaps(other.getBounds());
    }


}
