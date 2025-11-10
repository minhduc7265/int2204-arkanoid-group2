package com.game.arkanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.arkanoid.Main;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.output.Button;
import com.game.arkanoid.output.MusicManager;
import com.game.arkanoid.output.SoundManager;
//Class này sẽ xử lí toàn bộ nút bấm trong game
public class ButtonManager {
    private static ButtonManager buttonManager;
    private Button playButton;
    private Button creditButton;
    private Button exitButton;
    private Button restartButton;
    private Button mainMenuButton;
    private Button mainMenuButtonPause;
    private Button continueButton;
    private Button restartButtonPause;
    private Button volumeButton;
    private Stage stage;
    private Table table;
    private final Main game;

    private ButtonManager(Main game) {
        this.stage = new Stage(new ScreenViewport());
        this.playButton = new Button("skin/main_menu_skin.atlas", "skin/main_menu_skin.json", "default");
        this.creditButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json", "default");
        this.exitButton = new Button("skin/credit_pack.atlas", "skin/creditskin.json", "default");

        this.restartButton = new Button("skin/gameover_skin.atlas", "skin/gameover_skin.json", "restart-style");
        this.mainMenuButton = new Button("skin/gameover_skin.atlas", "skin/gameover_skin.json", "mainmenu-style");

        this.continueButton = new Button("skin/pause_skin.atlas", "skin/pause_skin.json", "continue-style");
        this.restartButtonPause = new Button("skin/pause_skin.atlas", "skin/pause_skin.json", "restart-style");
        this.mainMenuButtonPause = new Button("skin/gameover_skin.atlas", "skin/gameover_skin.json", "mainmenu-style");
        this.volumeButton = new Button("skin/pause_skin.atlas", "skin/pause_skin.json", "volume-style");
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(table);
        this.game = game;
    }

    public static ButtonManager getInstance(Main game) {
        if(buttonManager == null) {
            buttonManager = new ButtonManager(game);
        }
        return buttonManager;
    }


    public void showMenu() {
        Gdx.input.setInputProcessor(stage);
        ImageButton playButtonImage = playButton.getButton();
        ImageButton creditButtonImage = creditButton.getButton();
        playButton.addClickListener(() -> {
            deleteButton();
            Gdx.input.setInputProcessor(null);
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
            deleteButton();
            Gdx.input.setInputProcessor(null);
            Screen oldScreen = game.getScreen();
            game.setScreen(new CreditScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });
        table.bottom().right().padRight(25f);
        table.add(creditButtonImage).size(75, 75).padBottom(12f);
        table.add(playButtonImage).size(75, 75).padBottom(12f).padLeft(25f);

    }

    public void showCredit() {
        Gdx.input.setInputProcessor(stage);
        ImageButton exitButtonImage = exitButton.getButton();
        exitButton.addClickListener(() -> {
            deleteButton();
            Gdx.input.setInputProcessor(null);
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

    public void showPauseMenu() {
        Gdx.input.setInputProcessor(stage);
        ImageButton continueButtonImage = continueButton.getButton();
        ImageButton restartButtonImage = restartButtonPause.getButton();
        ImageButton volumeButtonImage = volumeButton.getButton();
        ImageButton mainMenuButtonImage = mainMenuButtonPause.getButton();

        mainMenuButtonPause.addClickListener(() -> {
            deleteButton();
            Gdx.input.setInputProcessor(null);
            Screen oldScreen = game.getScreen();
            game.setScreen(new MainMenuScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            MusicManager.getInstance().setmFlag(false);
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });


        continueButton.addClickListener(() -> {
            deleteButton();
            GameManager.getGameManager().setGameState(GameManager.GameState.RUNNING);
            MusicManager.getInstance().BASS_playMusic();
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });

        restartButtonPause.addClickListener(() -> {
            deleteButton();
            Gdx.input.setInputProcessor(null);
            Screen oldScreen = game.getScreen();
            game.setScreen(new GameScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });

        volumeButton.addClickListener(() -> {
            boolean isMuted = MusicManager.getInstance().getIsMuted();
            if (!isMuted) {
                MusicManager.getInstance().BASS_setVolume(0.0f);
                MusicManager.getInstance().setMuted(true);
            } else {
                MusicManager.getInstance().BASS_setVolume(1.0f);
                MusicManager.getInstance().setMuted(false);
            }
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });


        table.padTop(260f);
        table.add(continueButtonImage).size(300, 75).padLeft(20f);
        table.row();
        table.add(restartButtonImage).size(300, 75).padLeft(20f);
        table.row();
        table.add(volumeButtonImage).size(300, 75).padLeft(20f);
        table.row();
        table.add(mainMenuButtonImage).size(300, 75).padLeft(20f);

    }

    public void showGameOver() {
        Gdx.input.setInputProcessor(stage);
        ImageButton restartButtonImage = restartButton.getButton();
        ImageButton mainMenuButtonImage = mainMenuButton.getButton();
        restartButton.addClickListener(() -> {
            deleteButton();
            Gdx.input.setInputProcessor(null);
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
            deleteButton();
            Gdx.input.setInputProcessor(null);
            Screen oldScreen = game.getScreen();
            game.setScreen(new MainMenuScreen(game));
            if (oldScreen != null) {
                oldScreen.dispose();
                oldScreen = null;
            }
            MusicManager.getInstance().setmFlag(false);
            SoundManager.getInstance().playSound("BUTTON_CLICK");
        });
        table.padTop(220f);
        table.add(restartButtonImage).size(300, 75).padLeft(20f);
        table.row();
        table.add(mainMenuButtonImage).size(300, 75).padLeft(20f);
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
        playButton.dispose();
        creditButton.dispose();
        exitButton.dispose();
        continueButton.dispose();
        restartButtonPause.dispose();
        volumeButton.dispose();

    }


    public void deleteButton() {
        stage.clear();
        for (Actor actor : table.getChildren()) {
            actor.clearListeners();
            actor.remove();
        }
        table.clearChildren();
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(table);


    }
}
