package com.game.arkanoid.model;

import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.output.AnimationFactory;

public class BallBreakItem extends Item {
    private static final int DEFAULT_DURATION = 0;

    public BallBreakItem(float x, float y) {
        super(x, y, ItemType.BALL_BREAK, DEFAULT_DURATION);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        System.out.println("Ball is break");
        ball.setStatus(false);
        AnimationFactory.call(AnimationFactory.TypeAnimation.BREAK_ANIM, (int)ball.getXPos(), (int)ball.getYPos());
        //GameManager.getGameManager().setLives(GameManager.getGameManager().getLives() - 1);
    }


    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
    }
}
