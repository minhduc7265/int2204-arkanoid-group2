package com.game.arkanoid.model;

public class FireBallItem extends Item {
    private static final int DEFAULT_DURATION = 0;


    public FireBallItem(float x, float y) {
        super(x, y, ItemType.FIRE_BALL, DEFAULT_DURATION);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        System.out.println("Ball turned into a fireball!");
        ball.setFlag(1);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        System.out.println("Ball return to normal!");
    }
}
