package com.game.arkanoid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;

/**
* Manages the entire lifecycle of PowerUp objects in the game.
* This class is responsible for creating, updating state, checking for collisions,
* and applying effects of PowerUps. It plays a central role in keeping the logic related to power-ups
* clean and focused on the main game coordination.
*/

public class PowerUpManager {
    // List contains only active Power-Ups
    private List<PowerUp> activePowerUps;

    public PowerUpManager() {
        this.activePowerUps = new ArrayList<>();
    }

    /**
    * Factory method to create a Power-Up from a brick.
    */
        public void createPowerUpFromBrick(Brick brick) {
        int flag = brick.getFlag();
        if (flag == 0) return; // Factory method to create a Power-Up from a brick.

        float x = brick.getXPos();
        float y = brick.getYPos();
        PowerUp newPowerUp = null;

        switch (flag) {
            case 1:
                newPowerUp = new ExpandPaddlePowerUp(x, y);
                break;
            case 2:
                newPowerUp = new FastBallPowerUp(x, y);
                break;
        }

        if (newPowerUp != null) {
            activePowerUps.add(newPowerUp);
        }
    }

    /**
    * Update all Power-Ups (called in game loop).
    */
    public void updateAll() {
        Iterator<PowerUp> iterator = activePowerUps.iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();
            powerUp.update();
            // If the effect expires, remove it from the list
            if (!powerUp.getStatus()) {
                iterator.remove();
            }
        }
    }

    /**
    * Helper function: Find an activated Power-Up by type.
    * type The type of Power-Up to search for.
    * returns a PowerUp object if found, otherwise returns an empty (Optional.empty).
    */
    private Optional<PowerUp> findActivePowerUpByType(PowerUp.PowerUpType type) {
        for (PowerUp powerUp : activePowerUps) {
            // Điều kiện: Cùng loại VÀ đã được nhặt (đang có hiệu lực)
            if (powerUp.getType() == type && powerUp.isPickedUp()) {
                return Optional.of(powerUp);
            }
        }
        return Optional.empty(); 
    }

    /**
    * Check for collision with the paddle and apply effects.
    */
    public void checkCollisionsAndApplyEffects(Paddle paddle, Ball ball) {
        Iterator<PowerUp> iterator = activePowerUps.iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();

            // Only consider Power-Ups that are falling and colliding with the paddle
            if (!powerUp.isPickedUp() && paddle.collidesWith(powerUp)) {

                // Check if there is an effect of the same type running
                Optional<PowerUp> existingPowerUpOpt = findActivePowerUpByType(powerUp.getType());

                if (existingPowerUpOpt.isPresent()) {
                    //IF YES: Reset the time of the old effect
                    PowerUp existingPowerUp = existingPowerUpOpt.get();
                    existingPowerUp.resetDuration();
                        
                    // Delete the Power-Up you just picked up because it is only used to reset the time
                    iterator.remove();
                        
                } else {
                    //IF NOT: Apply the new effect as usual
                    powerUp.applyEffect(paddle, ball);
                    powerUp.setPickedUp(true);

                    // If the effect has no duration (instant), remove it
                    if (powerUp.isFinished()) {
                        iterator.remove();
                    }
                }
            }
        }
    }   

    public List<PowerUp> getActivePowerUps() {
        return this.activePowerUps;
    }
}