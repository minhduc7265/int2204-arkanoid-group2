package com.game.arkanoid.world;

import com.game.arkanoid.engine.GameManager;

public class LevelManager {
    private final GameManager gameManager;

    private final LevelLoader levelLoader;


    public LevelManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.levelLoader = new LevelLoader();

    }

}
