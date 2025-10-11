package com.game.arkanoid.model;


public class NormalBrick extends Brick {

    public NormalBrick(float x, float y, int width, int height, String textureID) {
        super(x, y, width, height, 1, textureID);
    }

    @Override
    public void update(){
    }

    @Override
    protected void onDestroyed() {
        System.out.println("Normal brick destroyed!");
    }
}
