package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button {
    //private Stage stage;//Lưu ý truyền tham số không tạo đối tượng mới
    private Skin skin;
    private TextureAtlas atlas;
    private ImageButton button;

    public Button(/**Stage stage,*/ String atlasPath, String skinPath) {
        //this.stage = stage;
        loadTextureAtlas(atlasPath);
        loadSkin(skinPath);
        createButton();
    }

    private void loadTextureAtlas(String path) {
        this.atlas = new TextureAtlas(Gdx.files.internal(path));
    }

    private void loadSkin(String path) {
        this.skin = new Skin(Gdx.files.internal(path), this.atlas);
    }

    private void createButton() {
        this.button = new ImageButton(this.skin, "default");
    }

    public void addClickListener(Runnable action) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run(); // Khi click thì chạy logic bạn truyền vào
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
