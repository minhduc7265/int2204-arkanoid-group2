package com.game.arkanoid.output;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.arkanoid.model.GameObject;

public class Renderer {
    private SpriteBatch renderer;

    /**
     * Contructor
     */
    public Renderer() {
        renderer = new SpriteBatch();
    }

    /**
     * beginRender.<br>
     * Call this first.
     */
    public void beginRender() {
        renderer.begin();
    }

    /**
     * endRender.<br>
     * Call this third.
     */
    public void endRender() {
        renderer.end();
    }

    /**
     * This function is for drawing texture.<br>
     * Call this second.
     *
     * @param obj     is GameObject
     * @param texture is Texture
     */

    public void render(GameObject obj, Texture texture) {
        renderer.draw(texture, obj.getXPos(), obj.getYPos(), obj.getWidth(), obj.getHeight());
    }

    /**
     * This function is for drawing text.<br>
     * Call this second.
     *
     * @param font is Font you want to use
     * @param text is TextObject
     */
    public void render(BitmapFont font, TextObject text) {
        font.setColor(text.getColor());
        font.draw(renderer, text.getContent(), text.getX(), text.getY());
    }

    /**
     * This function disposes of the SpriteBatch.
     */
    public void dispose() {
        renderer.dispose();
    }

}
