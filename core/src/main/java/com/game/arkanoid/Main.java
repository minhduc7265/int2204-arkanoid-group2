package com.game.arkanoid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.arkanoid.output.MasterRenderer;
import com.game.arkanoid.screens.MainMenuScreen;

public class Main extends Game {
    private MasterRenderer masterRenderer;

    @Override
    public void create() {
        masterRenderer = new MasterRenderer();
        // Bắt đầu game với màn hình Menuo
        setScreen(new MainMenuScreen(this));
    }

    public MasterRenderer getMasterRenderer() {
        return this.masterRenderer;
    }

    @Override
    public void dispose() {
        // Dọn dẹp renderer khi game tắt
        masterRenderer.dispose();
        super.dispose();
    }

    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Arkanoid Game");
        config.setWindowedMode(800, 800);
        config.setForegroundFPS(60);
        config.setResizable(false);
        // Khởi chạy chính lớp Main, không phải GameManager nữa
        new Lwjgl3Application(new Main(), config);
    }
}