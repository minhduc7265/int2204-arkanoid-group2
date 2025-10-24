package com.game.arkanoid.leveldata;

/**
* This class contains player information that will be saved to a JSON file.
*/

public class PlayerData {
    
    public int highScore;  // Highest score
    public int maxLevel;   // Highest level unlocked

    public PlayerData() {
        this.highScore = 0;
        this.maxLevel = 1; // New players start at level 1
    }
}