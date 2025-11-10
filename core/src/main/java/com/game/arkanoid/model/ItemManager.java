package com.game.arkanoid.model;

import com.game.arkanoid.output.AnimationFactory;
import com.game.arkanoid.output.SoundManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages the entire lifecycle of PowerUp objects in the game.
 * This class is responsible for creating, updating state, checking for collisions,
 * and applying effects of PowerUps. It plays a central role in keeping the logic related to power-ups
 * clean and focused on the main game coordination.
 */

public class ItemManager {
    // List contains only active Power-Ups
    private List<Item> activeItems;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public ItemManager() {
        this.activeItems = new ArrayList<>();
    }

    /**
     * Factory method to create a Power-Up from a brick.
     */
    public void createPowerUpFromBrick(Brick brick) {
        if (brick == null) return;
        int item = brick.getItem();
        if (item == 0) return; // Factory method to create a Power-Up from a brick.

        float x = brick.getXPos();
        float y = brick.getYPos();
        Item newItem = null;

        switch (item) {
            case 1:
                newItem = new ExpandPaddleItem(x, y);
                break;
            case 2:
                newItem = new FastBallItem(x, y);
                break;
            case 3:
                newItem = new LifeItem(x, y);
                break;
            case 4:
                newItem = new BallBreakItem(x,y);
                break;
            case 5:
                newItem = new BallInvisibleItem(x,y);
                break;
            case 6:
                newItem = new FireBallItem(x,y);
                break;
        }

        if (newItem != null) {
            activeItems.add(newItem);
        }
    }

    /**
     * Update all Power-Ups (called in game loop).
     */
    public void updateAllAsync() {
        executor.submit(() -> {
            for (Item item : activeItems) {
                item.update();
            }
        });
    }

    public void removeExpiredItems(Paddle paddle, Ball ball) {
        Iterator<Item> iterator = activeItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (!item.getStatus()) {
                if (item.isPickedUp()) {
                    item.removeEffect(paddle, ball); // main thread
                }
                iterator.remove();
            }
        }
    }
    /*public void updateAll(Paddle paddle, Ball ball) {
        if(!ball.getStatus()) return;
        Iterator<Item> iterator = activeItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            item.update();
            // If the effect expires, remove it from the list
            if (!item.getStatus()) {
                if (item.isPickedUp()) {
                    item.removeEffect(paddle, ball);
                }
                iterator.remove();
            }
        }
    }*/

    /**
     * Helper function: Find an activated Power-Up by type.
     * type The type of Power-Up to search for.
     * returns a PowerUp object if found, otherwise returns an empty (Optional.empty).
     */
    private Optional<Item> findActivePowerUpByType(Item.ItemType type) {
        for (Item item : activeItems) {
            // Điều kiện: Cùng loại VÀ đã được nhặt (đang có hiệu lực)
            if (item.getType() == type && item.isPickedUp()) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    /**
     * Check for collision with the paddle and apply effects.
     */
    public void checkCollisionsAndApplyEffects(Paddle paddle, Ball ball) {
        if(!ball.getStatus()) return;
        Iterator<Item> iterator = activeItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();

            // Only consider Power-Ups that are falling and colliding with the paddle
            if (!item.isPickedUp() && paddle.collidesWith(item)) {

                // Check if there is an effect of the same type running
                Optional<Item> existingPowerUpOpt = findActivePowerUpByType(item.getType());

                if (existingPowerUpOpt.isPresent()) {
                    //IF YES: Reset the time of the old effect
                    Item existingItem = existingPowerUpOpt.get();
                    existingItem.resetDuration();

                    // Delete the Power-Up you just picked up because it is only used to reset the time
                    iterator.remove();

                } else {
                    //IF NOT: Apply the new effect as usual
                    item.applyEffect(paddle, ball);
                    item.setPickedUp(true);
                    ItemManager.soundEffect(item.getType());
                    AnimationFactory.call(AnimationFactory.TypeAnimation.GAS_ANIM,
                        (int) paddle.getXPos() + paddle.getWidth() / 2,
                        (int) paddle.getYPos() + 95);

                    // If the effect has no duration (instant), remove it
                    if (item.isFinished()) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public List<Item> getActivePowerUps() {
        return this.activeItems;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void soundEffect(Item.ItemType type) {
        switch (type) {
            case ADD_LIFE, FAST_BALL, EXPAND_PADDLE -> {
                SoundManager.getInstance().playSound("APPLY_EFFECT");
            }
            case BALL_BREAK, FIRE_BALL, BALL_INVISIBLE -> {
                SoundManager.getInstance().playSound("BALL_BREAK");
            }
            default -> {
                System.err.println("Item invalid");
            }
        }
    }
}
