package com.game.arkanoid.output;


import com.badlogic.gdx.graphics.Color;

public class TextObject {
    private String content;
    private float x;
    private float y;
    private Color color;

    public TextObject() {
        this.content = "";
        this.x = 0f;
        this.y = 0f;
        this.color = new Color(255f, 255f, 255f, 255f);
    }

    public TextObject(String content, float x, float y) {
        this.content = content;
        this.x = x;
        this.y = y;
        this.color = new Color(255f, 255f, 255f, 255f);
    }

    public String getContent() {
        return content;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Get Color
     *
     * @return Color
     */
    public Color getColor() {
        Color newColor = new Color();
        newColor.set(this.color);
        return newColor;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Set by Color
     *
     * @param color is Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set by rgba
     *
     * @param r is red
     * @param g is green
     * @param b is blue
     * @param a is alpha
     */
    public void setColor(float r, float g, float b, float a) {
        this.color.r = r;
        this.color.g = g;
        this.color.b = b;
        this.color.a = a;
    }

    /**
     * Set content for render
     *
     * @param content is string
     */
    public void setContent(String content) {
        this.content = content;
    }

}
