package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.GdxRuntimeException;


public class FontManager {
    private BitmapFont bitmapFont;
    int size;

    public FontManager(String path, int size) {
        this.size = size;
        loadFont(path);
    }

    /**
     * Load font.
     * If fail then throw exception.
     *
     * @param path is path
     */
    public void loadFont(String path) {
        try {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = this.size;
            bitmapFont = generator.generateFont(parameter);
            generator.dispose();

        } catch (GdxRuntimeException e) {
            System.err.println("Load fail.");
            return;
        }
    }

    public void changeColor(Color color) {
        bitmapFont.setColor(color);
    }
}
