package com.game.arkanoid.model;


/**
 * Base class for all PowerUp items in Arkanoid.
 * PowerUp inherits from GameObject and adds properties that manage temporary effects.
 */
public class Item extends GameObject {
    public enum ItemType {
        EXPAND_PADDLE,
        FAST_BALL,
        ADD_LIFE,
        BALL_BREAK,
        BALL_INVISIBLE,
        FIRE_BALL
    }
    //Ball Debuff là ??

    private final ItemType type;
    private int duration;
    private boolean isPickedUp = false;
    private final float FALL_SPEED = 2.0f;
    private final int initialDuration;


    public Item(float x, float y, ItemType type, int duration) {
        super();
        setPosition(x, y);
        setArea(70, 63);
        setStatus(true);
        this.type = type;
        this.duration = duration;
        this.initialDuration = duration;
    }

    /**
     * Update the status of the PowerUp: dropped if not picked up, or countdown if picked up.
     * <p>The PowerUp will automatically set status=false when the validity period expires
     * to signal that the Effect needs to be removed.</p>
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
    public void applyEffect(Paddle paddle, Ball ball) {
    }

    // Undo the PowerUp effect.
    public void removeEffect(Paddle paddle, Ball ball) {
    }

    public ItemType getType() {
        return this.type;
    }

    public int getDuration() {
        return duration;
    }

    // Check if PowerUp has been picked up.
    public boolean isPickedUp() {
        return isPickedUp;
    }

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

    public String getTexture() {
        return switch (type) {
            case FAST_BALL -> "BOOST_SPEED";
            case EXPAND_PADDLE -> "BOOST_PADDLE";
            case ADD_LIFE -> "BOOST_HEART";
            case BALL_BREAK -> "BALL_BREAK";
            case FIRE_BALL -> "FIRE_BALL";
            case BALL_INVISIBLE -> "BALL_INVISIBLE";
            default -> throw new RuntimeException("Item không tồn tại");
        };
    }

}
