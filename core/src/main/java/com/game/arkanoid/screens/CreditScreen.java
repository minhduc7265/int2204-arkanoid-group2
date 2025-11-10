package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.game.arkanoid.Main;

public class CreditScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private ButtonManager buttonManager;

    public CreditScreen(Main game) {
        this.game = game;
        buttonManager = ButtonManager.getInstance(game);
        this.backgroundTexture = new Texture(Gdx.files.internal("backgrounds/background.png"));
    }

    @Override
    public void show() {
        buttonManager.showCredit();
    }

    @Override
    public void render(float delta) {
        game.getMasterRenderer().clearScreen();
        game.getMasterRenderer().renderCredit(backgroundTexture, buttonManager.getStage());
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
