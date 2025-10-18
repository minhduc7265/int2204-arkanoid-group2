package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.arkanoid.Main;

public class MainMenuScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Skin skin;
    private Texture backgroundTexture;

    public MainMenuScreen(Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        // Load các tài nguyên cần thiết
        this.backgroundTexture = new Texture(Gdx.files.internal("backgrounds/menu_background.png"));
        
        // Load file .json, và chỉ định file .atlas tương ứng của nó
        TextureAtlas PlayAtlas = new TextureAtlas(Gdx.files.internal("skin/star_pack.atlas"));
        this.skin = new Skin(Gdx.files.internal("skin/gameskin.json"), PlayAtlas);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Tạo ImageButton bằng cách chỉ định style "default" từ file skin của bạn
        ImageButton playButton = new ImageButton(skin, "default"); 

        // Gắn sự kiện "click" cho nút Play
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        // Thêm nút Play vào giữa màn hình
        table.bottom().right();
        table.add(playButton).size(250, 75).pad(10);

        // LƯU Ý: Để tạo các nút khác (How to Play, Exit...), bạn cần phải
        // định nghĩa thêm các style cho chúng trong file gameskin.json trước.
    }

    @Override
    public void render(float delta) {
        // Giao việc vẽ cho MasterRenderer
        game.getMasterRenderer().clearScreen();
        game.getMasterRenderer().renderMainMenu(backgroundTexture, stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
    }

    // Các hàm khác không đổi
    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}