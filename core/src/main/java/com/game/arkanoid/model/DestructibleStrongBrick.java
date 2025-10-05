package com.game.arkanoid.model;

public class DestructibleStrongBrick extends StrongBrick {

    public DestructibleStrongBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, textureID, 3);
    }

    @Override
    protected void onDestroyed() {
        System.out.println("Destructible strong brick destroyed!");
    }
}

