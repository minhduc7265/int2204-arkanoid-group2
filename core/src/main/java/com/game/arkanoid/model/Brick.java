package com.game.arkanoid.model;

public class Brick extends GameObject {
    private int flag;


    public Brick(float x, float y, int w, int h, String textureID) {
        super(x, y, w, h, textureID);


    }


    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
