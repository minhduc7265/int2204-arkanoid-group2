package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.game.arkanoid.Main;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.output.MusicManager;
import com.game.arkanoid.world.GameWorld;

public class GameScreen implements Screen {
    private Main game;
    private GameWorld world;
    private GameManager gameManager;
    private ButtonManager buttonManager;
    private boolean isShowGameOver;
    private boolean isShowPause;
    private Texture pauseBackgroundTexture;
    private Texture gameOverBackgroundTexture;

    public GameScreen(Main game) {
        this.game = game;
        this.world = new GameWorld();
        this.gameManager = GameManager.getGameManager();
        this.isShowGameOver = false;
        this.isShowPause = false;
        buttonManager = ButtonManager.getInstance(game);
        this.pauseBackgroundTexture = new Texture(Gdx.files.internal("backgrounds/pause_background.png"));
        this.gameOverBackgroundTexture = new Texture(Gdx.files.internal("backgrounds/gameover_background.png"));
        MusicManager.getInstance().setmFlag(false);
    }


    @Override
    public void render(float delta) {
        handleInputPause();
        updateLogicGame(delta);
        renderGame();
    }


    private void handleInputPause() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (gameManager.isPaused()) {
                gameManager.setGameState(GameManager.GameState.RUNNING);
                buttonManager.deleteButton();
                MusicManager.getInstance().BASS_playMusic();
            } else {
                gameManager.setGameState(GameManager.GameState.PAUSED);
                buttonManager.deleteButton();
                MusicManager.getInstance().BASS_pauseMusic();
            }
        }
    }

    private void updateLogicGame(float delta) {
        if (gameManager.isPaused() || gameManager.getGameState() == GameManager.GameState.GAMEOVER) {
            return;
        } else {
            world.update(delta);
        }
    }

    private void renderGame() {
        game.getMasterRenderer().clearScreen();
        game.getMasterRenderer().renderGameWorld(world);
        game.getMasterRenderer().renderHUD(gameManager);

        if (gameManager.getGameState() == GameManager.GameState.GAMEOVER) {
            showButtonGameOver();
            game.getMasterRenderer().renderGameOver(gameOverBackgroundTexture,buttonManager.getStage());
        }
        else if(gameManager.getGameState() == GameManager.GameState.PAUSED){
            showButtonPause();
            game.getMasterRenderer().renderPauseText(pauseBackgroundTexture, buttonManager.getStage());

        }
        else if(gameManager.getGameState() == GameManager.GameState.RUNNING){
            isShowPause = false;
        }
    }

    // ... các hàm còn lại của Screen (show, hide, dispose...)
    @Override
    public void show() {

    }

    public void showButtonGameOver() {
        if (isShowGameOver != true) {
            buttonManager.showGameOver();
            isShowGameOver = true;
        }

    }

    public void showButtonPause() {
        if(isShowPause !=  true) {
            buttonManager.showPauseMenu();
            isShowPause = true;
        }

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
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        pauseBackgroundTexture.dispose();
        gameOverBackgroundTexture.dispose();
        world.shutdown();
    }
}
