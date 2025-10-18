package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.world.GameWorld;

public class MasterRenderer {
    private SpriteBatch batch;
    // TODO: Thêm FontManager để vẽ chữ
    // private FontManager font;

    public MasterRenderer() {
        batch = new SpriteBatch();
        // font = new FontManager("path/to/font.ttf", 24);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void renderMainMenu(Texture background, Stage stage) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void renderGameWorld(GameWorld world) {
        batch.begin();
        // TODO: Vẽ paddle, ball, bricks từ world
        // Lấy texture từ một TextureManager tập trung (nếu có)
        batch.end();
    }
    
    public void renderHUD(GameManager gameManager) {
        batch.begin();
        // TODO: Dùng font để vẽ điểm và mạng sống
        batch.end();
    }
    
    public void renderPauseText() {
        batch.begin();
        // TODO: Dùng font để vẽ chữ "PAUSED"
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        // font.dispose();
    }
}