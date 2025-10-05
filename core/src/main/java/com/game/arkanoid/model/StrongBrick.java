package com.game.arkanoid.model;
public class StrongBrick extends Brick {
    public StrongBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, 2, textureID);
    }
    @Override
    public void update() {
        if (hitPoints == 1) {
        }
    }
    @Override
    protected void onDestroyed() {
        System.out.println("Strong brick destroyed!");
    }
}

