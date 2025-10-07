package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

//SoundManager is a Singleton
public class SoundManager {
    private static SoundManager soundManager;

    private final Map<String, Sound> listSounds;

    private SoundManager() {
        listSounds = new HashMap<>();
    }

    /**
     * Function to load sound.<br>
     * Example Commmand {@code listSound.put(name, Gdx.audio.newSound(Gdx.files.internal(path)))}.<br>
     * If load sound fail the game will warn.
     *
     * @param name: sound name
     * @param path: sound path
     */
    public void loadSound(String name, String path) {
        if (listSounds.containsKey(name)) {
            System.out.println(name + " is already exist.");
            return;
        }

        try {
            Sound newSound;
            newSound = Gdx.audio.newSound(Gdx.files.internal(path));
            listSounds.put(name, newSound);
        } catch (GdxRuntimeException e) {
            System.err.println(name + " Not found.");
            return;
        }
    }

    /**
     * Get {@code soundManager}
     */
    public static SoundManager getInstance() {
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    /**
     * Play sound.<br>
     * If sound is null, print an error.
     *
     * @param name
     */
    public void playSound(String name) {
        Sound sound = listSounds.get(name);
        if (sound == null) {
            System.err.println(name + " does not exist.");
            System.err.println("Play fail.");
            return;
        }
        sound.play();
    }

    /**
     * Free {@code Sound} Function.
     */
    public void dispose() {
        for (Sound sound : listSounds.values()) {
            sound.dispose();
        }
        listSounds.clear();
    }


}
