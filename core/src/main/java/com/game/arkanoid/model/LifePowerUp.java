package com.game.arkanoid.model;

import com.game.arkanoid.engine.GameManager; 

/**
* PowerUp adds one life to the player.
* This is an instant effect (duration = 0).
*/
public class LifePowerUp extends PowerUp {

    // Set duration = 0 so it is considered an instant effect
    private static final int DURATION = 0;

    public LifePowerUp(float x, float y) {
       // Call super constructor with TYPE and DURATION
        super(x, y, PowerUpType.ADD_LIFE, DURATION);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {

    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        // No need to do anything, because "plus network" is not an effect that needs to be removed.
    }
}