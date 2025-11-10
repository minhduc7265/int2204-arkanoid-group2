package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.game.arkanoid.Main;
import com.game.arkanoid.engine.GameManager;

public class MainMenuScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private GameManager gameManager;
    private ButtonManager buttonManager;

    public MainMenuScreen(Main game) {
        this.game = game;
        buttonManager = ButtonManager.getInstance(game);
        this.gameManager = GameManager.getGameManager();
        this.backgroundTexture = new Texture(Gdx.files.internal("backgrounds/menu_background.png"));
    }

    @Override
    public void show() {
        buttonManager.showMenu();
    }

    @Override
    public void render(float delta) {
        game.getMasterRenderer().clearScreen();
        game.getMasterRenderer().renderMainMenu(backgroundTexture, buttonManager.getStage());
        game.getMasterRenderer().renderHighScore(gameManager);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
        buttonManager.getStage().getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
