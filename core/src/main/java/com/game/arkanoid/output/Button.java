package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button {
    private Skin skin;
    private TextureAtlas atlas;
    private ImageButton button;

    public Button(String atlasPath, String skinPath, String style) {
        loadTextureAtlas(atlasPath);
        loadSkin(skinPath);
        createButton(style);
    }

    private void loadTextureAtlas(String path) {
        this.atlas = new TextureAtlas(Gdx.files.internal(path));
    }

    private void loadSkin(String path) {
        this.skin = new Skin(Gdx.files.internal(path), this.atlas);
    }

    private void createButton(String style) {
        this.button = new ImageButton(this.skin, style);
    }

    public void addClickListener(Runnable action) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });
    }


    public ImageButton getButton() {
        return button;
    }

    public void dispose() {
        skin.dispose();
        atlas.dispose();
    }
}
