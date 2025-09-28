package com.gruop2.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

//TextureManager is A Singleton
public class TextureManager {

    private static TextureManager textureManager;

    private Map<String, Texture> listTextures;

    private TextureManager() {
        listTextures = new HashMap<>();
        loadTextures();
    }

    private void loadTextures() {
        //load into map
        //Ex: listTextures.put("name", new Texture(Gdx.files.internal("link")));
        //Must use Try-Catch when can't load texture
    }

    public static TextureManager getInstance() {
        if(textureManager == null) {
            textureManager = new TextureManager();
        }
        return textureManager;
    }

    public Texture getTexture(String name) {
        return listTextures.get(name);
    }

    public void dispose() {
        for(Texture texture : listTextures.values()) {
            texture.dispose();
        }
        listTextures.clear();
    }
}
