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
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.output.Bass;
import com.game.arkanoid.output.MasterRenderer;
import com.game.arkanoid.output.SoundManager;

public class PauseScreen implements Screen {
    private final Main game;
    private final GameScreen gameScreen;
    private final GameManager gameManager;
    private Stage stage;
    private Skin skin;
    private TextureAtlas pauseAtlas;
    private Texture pauseBackgroundTexture;

    public PauseScreen(Main game, GameScreen gameScreen, GameManager gameManager) {
        this.game = game;
        this.gameScreen = gameScreen;
        this.gameManager = gameManager;
        this.stage = new Stage(new ScreenViewport());
        this.pauseBackgroundTexture = new Texture(Gdx.files.internal("backgrounds/pause_background.png"));

        this.pauseAtlas = new TextureAtlas(Gdx.files.internal("skin/pause_skin.atlas"));
        this.skin = new Skin(Gdx.files.internal("skin/pause_skin.json"), pauseAtlas);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        table.center();
        stage.addActor(table);

        ImageButton continueButton = new ImageButton(skin, "continue-style");
        ImageButton restartButton = new ImageButton(skin, "restart-style");
        ImageButton volumeButton = new ImageButton(skin, "volume-style");

        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameManager.setGameState(GameManager.GameState.RUNNING);
                Bass.INSTANCE.BASS_ChannelPlay(MasterRenderer.hMusic, false);
                game.setScreen(gameScreen);
            }
        });

        // Gắn sự kiện cho nút Restart
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameManager.startNewGame();
                Bass.INSTANCE.BASS_ChannelPlay(MasterRenderer.hMusic, false);
                game.setScreen(new GameScreen(game)); // Tạo GameScreen mới
            }
        });

        // Gắn sự kiện cho nút Volume
        volumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Đảo ngược trạng thái Mute (trong SoundManager)
                SoundManager.getInstance().toggleMute();

                // Lấy trạng thái Mute mới
                boolean isMuted = SoundManager.getInstance().isMuted();
                if (isMuted) {
                    // Tắt nhạc (đặt âm lượng chung của Bass về 0)
                    Bass.INSTANCE.BASS_SetVolume(0.0f);
                } else {
                    // Bật lại nhạc (đặt âm lượng chung của Bass về 1.0)
                    Bass.INSTANCE.BASS_SetVolume(1.0f);
                }
            }
        });

        // Thêm nút vào bảng (giữ nguyên)
        table.add(continueButton).size(300, 75).padTop(200);
        table.row();
        table.add(restartButton).size(300, 75).padTop(10);
        table.row();
        table.add(volumeButton).size(300, 75).padTop(10);
    }

    @Override
    public void render(float delta) {
        game.getMasterRenderer().clearScreen();
        // Tái sử dụng hàm render của MainMenu (giờ sẽ chuẩn vì background đã đúng)
        game.getMasterRenderer().renderMainMenu(pauseBackgroundTexture, stage);
    }

    @Override
    public void dispose() {
        // Dọn dẹp tài nguyên CỦA RIÊNG MÀN HÌNH NÀY
        stage.dispose();
        skin.dispose();
        pauseAtlas.dispose();
        pauseBackgroundTexture.dispose();
    }


    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

}
