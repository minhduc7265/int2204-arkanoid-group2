package com.game.arkanoid.model;

public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected boolean destroyed;

    public Brick(float x, float y, int width, int height, int hitPoints, String textureID) {
        super();
        setPosition(x, y);
        setArea(width, height);
        setTextureID(textureID);
        setStatus(true);

        this.hitPoints = hitPoints;
        this.destroyed = false;
    }
    public void hit() {
        if (!destroyed) {
            hitPoints--;
            if (hitPoints <= 0) {
                destroyed = true;
                setStatus(false);
                onDestroyed();
            }
        }
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    protected void onDestroyed() {}
    public abstract void update();
}

