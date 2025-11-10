package com.game.arkanoid.engine;

import com.game.arkanoid.leveldata.GameDataManager;

/**
 * Manages the global state of the game session (score, lives, level).
 * This is a Singleton class, meaning only one instance of it can exist.
 */
public class GameManager {
    private static GameManager gameManager;
    private GameDataManager gameDataManager;

    private int score;
    private int lives;
    private int currentLevel;
    private int highScore;
    private int maxLevel;

    public enum GameState { RUNNING, PAUSED, GAMEOVER }
    private GameState gameState;

    private GameManager() {
        gameDataManager = new GameDataManager();
    }


    public static GameManager getGameManager() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    /**
     * Resets all game statistics to their starting values for a new game.
     */
    public void startNewGame() {
        this.score = 0;
        this.lives = 3;
        this.currentLevel = 1;
        this.gameState = GameState.RUNNING;
    }


    public void setLives(int lives) {
        this.lives = lives;
    }

    public void goToNextLevel() {
        this.currentLevel++;
        // Thêm logic reset vị trí bóng/paddle ở GameWorld
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void readData() {
        gameDataManager.loadDataAndPrint();
        highScore = gameDataManager.getPlayerData().highScore;
        maxLevel = gameDataManager.getPlayerData().maxLevel;
    }

    public void writeData() {
        if(score >= highScore) {
            gameDataManager.getPlayerData().highScore = score;
        }
        gameDataManager.saveData();
    }

    //extra points
    public void addScore(int points) { this.score += points; }

    /**
     * Decrements the player's life count by one and checks for game over.
     */
    public void loseLife() {
        this.lives--;
        if (this.lives <= 0) {
            this.gameState = GameState.GAMEOVER;
            writeData();
            readData();
        }
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getCurrentLevel() { return currentLevel; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState state) { this.gameState = state; }
    public boolean isPaused() { return this.gameState == GameState.PAUSED; }
}
