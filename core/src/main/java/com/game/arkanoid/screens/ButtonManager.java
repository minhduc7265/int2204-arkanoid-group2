package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.arkanoid.Main;
import com.game.arkanoid.output.Button;
import com.game.arkanoid.output.MusicManager;
import com.game.arkanoid.output.SoundManager;

public class ButtonManager {
    private Button playButton;
    private Button creditButton;
    private Button exitButton;
    private Button restartButton;
    private Button mainMenuButton;
    private Stage stage;
    private Table table;
    private final Main game;

    public ButtonManager(Main game) {
        this.stage = new Stage(new ScreenViewport());
        this.playButton = new Button("skin/star_pack.atlas", "skin/gameskin.json");
        this.creditButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json");
        this.exitButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json");
        this.restartButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json");
        this.mainMenuButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json");
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(table);
        this.game = game;
    }


    public void showMenu() {
        Gdx.input.setInputProcessor(stage);
        ImageButton playButtonImage = playButton.getButton();
        ImageButton creditButtonImage = creditButton.getButton();
        playButton.addClickListener(() -> {
            Screen oldScreen = game.getScreen();
            game.setScreen(new GameScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
            MusicManager.getInstance().setmFlag(false);
        });
        creditButton.addClickListener(() -> {
            Screen oldScreen = game.getScreen();
            game.setScreen(new CreditScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });
        table.bottom().right().padRight(25);
        table.add(creditButtonImage).size(75, 75).padBottom(12);
        table.add(playButtonImage).size(75, 75).padBottom(12).padLeft(25);

    }

    public void showCredit() {
        Gdx.input.setInputProcessor(stage);
        ImageButton exitButtonImage = exitButton.getButton();
        exitButton.addClickListener(() -> {
            Screen oldScreen = game.getScreen();
            game.setScreen(new MainMenuScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });
        table.bottom().right().padRight(25);
        table.add(exitButtonImage).size(75, 75).padBottom(12);

    }

    public void showPauseMenu(){

    }

    public void showGameOver() {
        Gdx.input.setInputProcessor(stage);
        ImageButton restartButtonImage = restartButton.getButton();
        ImageButton mainMenuButtonImage = mainMenuButton.getButton();
        restartButton.addClickListener(() -> {
            Screen oldScreen = game.getScreen();
            game.setScreen(new GameScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            MusicManager.getInstance().setmFlag(false);
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });

        mainMenuButton.addClickListener(() -> {
            Screen oldScreen = game.getScreen();
            game.setScreen(new MainMenuScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            MusicManager.getInstance().setmFlag(false);
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });
        table.bottom().right().padRight(25);
        table.add(restartButtonImage).size(75, 75).padBottom(12);
        table.add(mainMenuButtonImage).size(75, 75).padBottom(12).padLeft(25);
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
        playButton.dispose();
        creditButton.dispose();
        exitButton.dispose();

    }
}
