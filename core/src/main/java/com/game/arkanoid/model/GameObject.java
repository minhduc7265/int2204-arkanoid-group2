package com.game.arkanoid.model;

/**
* Base class for all game objects.
* Stores position, size, state, and texture information.
*/

public class GameObject {
    private float x;           // position of x
    private float y;           // position of y
    private int width;
    private int height;
    private boolean status;
    private String textureID;  // used to upload texture

    public GameObject() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.status = true;
        this.textureID = "";
    }

     public void setArea(int width, int height) {
        this.width = width;
        this.height = height;
    } 

    public void setPosition(float x_pos, float y_pos) {
        this.x = x_pos;
        this.y = y_pos;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() { return status; }
    public float getXPos() { return x; }
    public float getYPos() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
   

    
    public void setTextureID(String id) {
        this.textureID = id;
    }

    public String getTextureID() {
        return textureID;
    }

    public void update() {

    }

}
