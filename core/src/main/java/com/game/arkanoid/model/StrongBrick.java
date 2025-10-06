package com.game.arkanoid.model;
public class StrongBrick extends Brick {
    public StrongBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, hitPoints, textureID);}
    @Override
    public void update() {
        if (hitPoints == 1) {}
    }
}

