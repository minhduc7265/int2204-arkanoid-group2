package com.game.arkanoid.engine;

/**
 * Manages the global state of the game session (score, lives, level).
 * This is a Singleton class, meaning only one instance of it can exist.
 */
public class GameManager {
    private static GameManager gameManager;

    private int score;
    private int lives;
    private int currentLevel;

    public enum GameState { RUNNING, PAUSED, GAMEOVER }
    private GameState gameState;

    private GameManager() {}


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

    public void goToNextLevel() {
        this.currentLevel++;
        // Thêm logic reset vị trí bóng/paddle ở GameWorld
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
        }
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getCurrentLevel() { return currentLevel; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState state) { this.gameState = state; }
    public boolean isPaused() { return this.gameState == GameState.PAUSED; }
}