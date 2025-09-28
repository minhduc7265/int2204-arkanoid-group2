package com.game.arkanoid.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager implements ApplicationListener {
    private static GameManager gameManager;
    private SpriteBatch batch;
    private Texture background;
    private GameManager() {

    }

    public static GameManager getGameManager() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }


    @Override
    public void create() {
        // Khởi tạo SpriteBatch
        batch = new SpriteBatch();
        // Tải hình ảnh từ thư mục assets
        background = new Texture(Gdx.files.internal("loadgame.png"));

    }

    @Override
    public void render() {
        batch.begin();
        // Vẽ hình ảnh lên toàn màn hình
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Kết thúc vẽ
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();

    }
}
