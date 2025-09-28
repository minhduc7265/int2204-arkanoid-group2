package com.game.arkanoid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.arkanoid.engine.GameManager;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }

    public static void main (String[] arg) {
        GameManager gameManager = GameManager.getGameManager();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Arkanoid Game");
        config.setWindowedMode(800, 600);
        config.setForegroundFPS(60); // Limit FPS: 60fps
        config.setResizable(false);

        System.out.println("GameRunning Success");
        new Lwjgl3Application(gameManager, config);
    }
}
