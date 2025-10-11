package com.game.arkanoid.model;


public class StrongBrick extends Brick {

    public StrongBrick(float x, float y, int width, int height, String textureID, int hitPoints) {
        super(x, y, width, height, hitPoints, textureID);
    }

    @Override
    public void update() {
        if (hitPoints == 1) {
            setTextureID("strong_brick_damaged");
        }
    }

    @Override
    protected void onDestroyed() {
        System.out.println("Strong brick destroyed!");
    }
}

