package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.game.arkanoid.Main;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.world.GameWorld;

public class GameScreen implements Screen {
    private Main game;
    private GameWorld world;
    private GameManager gameManager;

    public GameScreen(Main game) {
        this.game = game;
        this.world = new GameWorld();
        this.gameManager = GameManager.getGameManager();
    }

    @Override
    public void render(float delta) {
        // Xử lý input Pause
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (gameManager.isPaused()) {
                gameManager.setGameState(GameManager.GameState.RUNNING);
            } else {
                gameManager.setGameState(GameManager.GameState.PAUSED);
            }
        }

        // Chỉ cập nhật logic nếu game không bị pause
        if (!gameManager.isPaused()) {
            world.update(delta);
        }

        // Giao việc vẽ cho MasterRenderer
        game.getMasterRenderer().clearScreen();
        game.getMasterRenderer().renderGameWorld(world);
        game.getMasterRenderer().renderHUD(gameManager);

        if (gameManager.isPaused()) {
            game.getMasterRenderer().renderPauseText();
        }
    }

    // ... các hàm còn lại của Screen (show, hide, dispose...)
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}