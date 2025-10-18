package com.game.arkanoid.model;

import com.game.arkanoid.model.PowerUp.PowerUpType;

/**
* Base class for all PowerUp items in Arkanoid.
* PowerUp inherits from GameObject and adds properties that manage temporary effects.
*/
public class PowerUp extends GameObject { 
     public enum PowerUpType {
        EXPAND_PADDLE,
        FAST_BALL
    }

    private final PowerUpType type;
    private int duration;
    private boolean isPickedUp = false;
    private final float FALL_SPEED = 2.0f;
    private final int initialDuration;

   

    public PowerUp(float x, float y, PowerUpType type, int duration) {
        super();
        setPosition(x, y);
        setArea(30, 15);
        setTextureID("PowerUp_" + type);
        setStatus(true);
        this.type = type;
        this.duration = duration;
        this.initialDuration = duration;
    }

   /**
    * Update the status of the PowerUp: dropped if not picked up, or countdown if picked up.
    * <p>The PowerUp will automatically set status=false when the validity period expires 
         to signal that the Effect needs to be removed.</p>
    */
    @Override
    public void update() {
        if (getStatus() && !isPickedUp) {
            setPosition(getXPos(), getYPos() - FALL_SPEED);
        } else if (isPickedUp) {
            if (duration > 0) {
                duration--;
            } else {
                setStatus(false);
            }
        }
    }

    // Apply the PowerUp effect to the Paddle and Ball.
    public void applyEffect(Paddle paddle, Ball ball){}
     
    // Undo the PowerUp effect.
    public void removeEffect(Paddle paddle, Ball ball){}

    public PowerUpType getType() {
        return this.type;
    }

    public int getDuration() { return duration; }

    // Check if PowerUp has been picked up.
    public boolean isPickedUp() { return isPickedUp; }

    public void setPickedUp(boolean pickedUp) {
        this.isPickedUp = pickedUp;
    }

    //Reduces remaining validity time.
    public void decreaseDuration(int amount) {
        this.duration -= amount;
    }

    public void resetDuration() { 
        this.duration = this.initialDuration;
    }

    public boolean isFinished() {
        return isPickedUp && duration <= 0;
    }
}