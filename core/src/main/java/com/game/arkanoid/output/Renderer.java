package com.game.arkanoid.output;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public void renderIncludeColor(GameObject obj, Texture texture) {
        setColor(obj.getColor());
        renderer.draw(texture, obj.getXPos(), obj.getYPos(), obj.getWidth(), obj.getHeight());
        setColor(Color.WHITE);
    }

    public void renderCircle(GameObject obj, Texture texture) {
        renderer.draw(texture, obj.getXPos() - obj.getWidth() / 2f, obj.getYPos() - obj.getHeight() / 2f);
    }

    public void renderCircleIncludeColor(GameObject obj, Texture texture) {
        setColor(obj.getColor());
        renderer.draw(texture, obj.getXPos() - obj.getWidth() / 2f, obj.getYPos() - obj.getHeight() / 2f);
        setColor(Color.WHITE);
    }


    /**
     * This function is for drawing texture.<br>
     * Call this second.
     *
     * @param ox      is posX
     * @param oy      is posY
     * @param width   is width
     * @param height  is height
     * @param texture is Texture
     */

    public void render(float ox, float oy, float width, float height, Texture texture) {
        renderer.draw(texture, ox, oy, width, height);
    }


    public void render(float ox, float oy, TextureRegion texture, AnimationSpriteSheet animationSpriteSheet) {
        renderer.draw(texture, ox, oy, animationSpriteSheet.getWidth(), animationSpriteSheet.getHeight());
    }

    public void setColor(Color color) {
        renderer.setColor(color);
    }

    public void setColor(float r, float g, float b, float a) {
        renderer.setColor(r, g, b, a);
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
        font.setColor(Color.WHITE);
    }

    /**
     * This function disposes of the SpriteBatch.
     */
    public void dispose() {
        renderer.dispose();
    }

}
