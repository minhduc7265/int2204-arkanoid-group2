package com.game.arkanoid.model;


public class IndestructibleStrongBrick extends StrongBrick {

    public IndestructibleStrongBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, textureID, Integer.MAX_VALUE);
    }

    /**
     * Integer.MAX_VALUE : máu cực lớn
     */
    @Override
    public void hit() {
        System.out.println("Indestructible brick hit, but not destroyed!");
    }

    @Override
    protected void onDestroyed() {
        System.out.println("This brick cannot be destroyed!");
    }

    @Override
    public void update() {
    }
}

