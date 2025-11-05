package com.game.arkanoid.model;

public class Brick extends GameObject {
    private int flag;
    private int item;


    public Brick(float x, float y, int w, int h, String textureID, int flag, int item) {
        super(x, y, w, h, textureID);
        this.flag = flag;
        this.item = item;

    }


    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
