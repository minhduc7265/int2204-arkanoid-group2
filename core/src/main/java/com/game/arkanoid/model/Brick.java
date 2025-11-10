package com.game.arkanoid.model;

public class Brick extends GameObject {
    private int flag;
    private int item;
    private int blood;

    public static final int BRICK_WID = 80;
    public static final int BRICK_HEI = 32;


    public Brick(float x, float y, int w, int h, int flag, int item, int blood) {
        super(x, y, w, h);
        this.flag = flag;
        this.item = item;
        this.blood = blood;

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

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public String getTexture() {
        switch (flag) {
            case 1, 2, 3, 4, 6 -> {
                return String.format("BRICK_%d", flag);
            }
            case 5 -> {
                return (this.blood == 2 ? "BRICK_5" : "BRICK_5_BREAK");
            }
            default -> {
                System.err.println("Invalid texture, use default!");
                return "DEFAULT_TEXTURE";
            }
        }
    }
}
