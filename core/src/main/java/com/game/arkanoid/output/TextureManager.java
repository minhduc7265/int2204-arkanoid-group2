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
        loadListTexture();
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

    public void loadListTexture() {
        loadTextures("BRICK_1", "textures/brick_1.png");
        loadTextures("BRICK_2", "textures/brick_2.png");
        loadTextures("BRICK_3", "textures/brick_3.png");
        loadTextures("BRICK_4", "textures/brick_4.png");
        loadTextures("BRICK_5", "textures/brick_5.png");
        loadTextures("BRICK_5_BREAK", "textures/brick_5_break.png");
        loadTextures("BRICK_6", "textures/brick_6.png");
        loadTextures("DEFAULT_TEXTURE", "textures/default_texture.png");

        loadTextures("BOOST_PADDLE","textures/boost_paddle.png");
        loadTextures("BOOST_SPEED","textures/boost_speed.png");
        loadTextures("BOOST_HEART","textures/boost_heart.png");

        loadTextures("BALL_BREAK","textures/ball_break.png");
        loadTextures("FIRE_BALL","textures/fire_ball.png");
        loadTextures("BALL_INVISIBLE","textures/ball_invisible.png");

        loadTextures("BALL_1", "textures/ball_1.png");
        loadTextures("BALL_2", "textures/ball_2.png");
        loadTextures("BALL_3", "textures/ball_3.png");

        loadTextures("PADDLE_NORMAL", "textures/paddle.png");
        loadTextures("CHALLENGE_CHAOS_BG", "images/challenge_chaos_bg.png");

        loadTextures("CLOUD_SHEET","spritesheet/cloud.png");
        loadTextures("FIRE_SHEET", "spritesheet/fire.png");
        loadTextures("GAS_SHEET", "spritesheet/gas.png");
        loadTextures("BREAK_SHEET", "spritesheet/break.png");

    }
}
