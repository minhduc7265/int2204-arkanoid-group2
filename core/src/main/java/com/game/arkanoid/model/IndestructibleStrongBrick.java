package com.game.arkanoid.model;

public class IndestructibleStrongBrick extends StrongBrick {

    public IndestructibleStrongBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, textureID, 9999);}
    @Override
    public void takeHit() {
        System.out.println("This brick is indestructible!");}
    @Override
    protected void onDestroyed() {}
}

