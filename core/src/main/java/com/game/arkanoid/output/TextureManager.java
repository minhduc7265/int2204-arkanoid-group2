package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

//TextureManager is A Singleton
public class TextureManager {

    private static TextureManager textureManager;

    private final Map<String, Texture> listTextures;

    private TextureManager() {
        listTextures = new HashMap<>();
    }

    /**
     * Function to load texture.<br>
     * Example Commmand {@code listTexture.put(name, new Texture(Gdx.files.interal(path)))}.<br>
     * If load texture fail the game will warn.
     *
     * @param name: texture name
     * @param path: texture path
     */

    public void loadTextures(String name, String path) {
        if (listTextures.containsKey(name)) {
            System.out.println(name + " is already exist.");
            return;
        }
        try {
            Texture newTexture;
            newTexture = new Texture(Gdx.files.internal(path));
            listTextures.put(name, newTexture);
        } catch (GdxRuntimeException e) {
            System.err.println(name + " Not found.");
            return;
        }

    }

    /**
     * Get {@code textureManager}
     */

    public static TextureManager getInstance() {
        if (textureManager == null) {
            textureManager = new TextureManager();
        }
        return textureManager;
    }

    /**
     * Get {@code Texture}.<br>
     * If Texture is null, log an error and the game will exit. <br>
     *
     * @param name
     * @return {@code Texture}
     */

    public Texture getTexture(String name) {
        Texture texture = listTextures.get(name);
        if (texture == null) {
            System.err.println(name + " does not exist.");
            Gdx.app.exit();
        }
        return texture;
    }

    /**
     * Free {@code Texture} Function.
     */

    public void dispose() {
        for (Texture texture : listTextures.values()) {
            texture.dispose();
        }
        listTextures.clear();
    }
}
