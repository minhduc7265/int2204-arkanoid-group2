package com.game.arkanoid.model;

import com.badlogic.gdx.graphics.Color;

public class BallInvisibleItem extends Item {
    private static final int DEFAULT_DURATION = 180;


    public BallInvisibleItem(float x, float y) {
        super(x, y, ItemType.BALL_INVISIBLE, DEFAULT_DURATION);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        ball.setColor(1f,1f,1f,0.025f);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        ball.setColor(Color.WHITE);
    }
}
