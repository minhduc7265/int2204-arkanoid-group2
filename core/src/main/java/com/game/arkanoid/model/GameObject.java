package com.game.arkanoid.model;

import com.badlogic.gdx.math.Rectangle;

/**
* Base class for all game objects.
* Stores position, size, state, and texture information.
*/

public class GameObject {
    private Rectangle bounds; // rectangle stores the coordinates and dimensions of the object
    private boolean status;
    private String textureID;  // used to upload texture
    public static int screenWidth; // Common screen width for all objects inherited from GameObject
    public static int screenHeight; //Common screen height for all objects inherited from GameObject

    public GameObject() {
        this.bounds.x = 0f;
        this.bounds.y = 0f;
        this.bounds.width = 0;
        this.bounds.height = 0;
        this.status = true;
        this.textureID = "";
        this.bounds = new Rectangle();
    }

    public GameObject(float x, float y, int width, int height, String textureID) {
        this.bounds.x = x;
        this.bounds.y = y;
        this.bounds.width = width;
        this.bounds.height = height;
        this.textureID = textureID;
    }

     public void setArea(int width, int height) {
        this.bounds.setSize(width, height);
    } 

    public void setPosition(float x_pos, float y_pos) {
        this.bounds.x = x_pos;
        this.bounds.y = y_pos;
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

    public boolean getStatus() { return status; }
    public float getXPos() { return this.bounds.x; }
    public float getYPos() { return this.bounds.y; }
    public int getWidth() { return (int) this.bounds.width; }
    public int getHeight() { return (int) this.bounds.height; }
    
    public void setTextureID(String id) {
        this.textureID = id;
    }

    public String getTextureID() {
        return textureID;
    }

    public void update() {

    }

    public void setBounds(float x, float y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }
    public Rectangle getBounds() { return this.bounds; }

    public static int getScreenWidth() { return screenWidth; }
    public static int getScreenHeight() { return screenHeight; }
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
