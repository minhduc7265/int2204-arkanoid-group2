package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class AnimationSpriteSheet {
    private static final Map<String, AnimationSpriteSheet> listAnimation = new HashMap<>();
    private float width;
    private float height;
    private Animation<TextureRegion> animation;

    public AnimationSpriteSheet(Texture texture, int COLS, int ROWS, float width, float height) {
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / COLS, texture.getHeight() / ROWS);
        TextureRegion[] frames = new TextureRegion[COLS * ROWS];
        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(0.025f, frames);
        this.width = width;
        this.height = height;
    }

    public void setLoop(boolean loop) {
        animation.setPlayMode(loop ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
    }

    public static Animation<TextureRegion> getAnimation(String name) {
        return listAnimation.get(name).animation;
    }


    public static AnimationSpriteSheet getAnimationSpriteSheet(String name) {
        return listAnimation.get(name);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public static void loadAnim() {
        listAnimation.put("CLOUD_ANIM",
            new AnimationSpriteSheet(TextureManager.getInstance().getTexture("CLOUD_SHEET"),
                14,
                1, 180f, 228f));

        listAnimation.put("FIRE_ANIM",
            new AnimationSpriteSheet(TextureManager.getInstance().getTexture("FIRE_SHEET"),
                25,
                1, 491f, 602f));

    }


}
