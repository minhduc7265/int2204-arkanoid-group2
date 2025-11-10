package com.game.arkanoid.leveldata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
* This class handles reading and writing the PlayerData.json file.
* It uses the same Json library you are using for the level.
*/

public class GameDataManager {

    private Json json;
    private PlayerData playerData;
    private static final String SAVE_FILE = "player.json"; // Save file name

    public GameDataManager() {
    // Initialize the Json library
        json = new Json();
    }

    /**
    * Load player data from file.
    * If file does not exist, it will create new data.
    */
    public void loadDataAndPrint() {
        FileHandle file = Gdx.files.local(SAVE_FILE); //find player.json file

        if (file.exists()) {
            // File exists -> Read file
            try {
                playerData = json.fromJson(PlayerData.class, file);
                System.out.println("Loaded Data!");
            } catch (Exception e) {
                // File is corrupted
                System.out.println("Save file corrupted! Create default data.");
                playerData = new PlayerData(); // Create new
                saveData(); // Save new file
            }
        } else {
            // File does not exist (first time playing game)
            System.out.println("Save file not found. Creating default data.");
            playerData = new PlayerData(); // Create new
            saveData(); // Save new file
        }

        // Print information to console
        System.out.println("--- CURRENT PLAYER INFORMATION ---");
        System.out.println("   Highest score: " + playerData.highScore);
        System.out.println("   Highest Level: " + playerData.maxLevel);
        System.out.println("-------------------------------------");
    }

    /**
    * Write current data (in playerData) to file
    */
    public void saveData() {
        FileHandle file = Gdx.files.local(SAVE_FILE);
        //Overwrite that JSON string into the player.json file
        file.writeString(json.prettyPrint(playerData), false);
        System.out.println("Saved data!");
    }

    // Function to get data
    public PlayerData getPlayerData() {
        return playerData;
    }
}
