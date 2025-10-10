package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

//MusicManager is a Singleton
public class MusicManager {
    private static MusicManager musicManager;

    private final Map<String, Music> listMusics;


    private MusicManager() {
        listMusics = new HashMap<>();
    }

    /**
     * Load Music.<br>
     * Throw exception when path is invalid or load fail.
     *
     * @param name is nameMusic
     * @param path is Path
     */
    public void loadMusic(String name, String path) {
        if (listMusics.containsKey(name)) {
            System.out.println(name + " is already exist.");
            return;
        }
        try {
            Music newMusic;
            newMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
            listMusics.put(name, newMusic);
        } catch (GdxRuntimeException e) {
            System.err.println(name + " Not found.");
            return;
        }
    }

    /**
     * Get musicManager.
     *
     * @return musicManager
     */
    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    /**
     * Play Music function.<br>
     * If name is invalid then print an error.
     *
     * @param name   is nameMusic
     * @param isLoop true or false
     */
    public void playMusic(String name, boolean isLoop) {
        Music music = listMusics.get(name);
        if (music == null) {
            System.err.println(name + " does not exist.");
            System.err.println("Play fail.");
            return;
        }
        music.play();
        music.setLooping(isLoop);
    }

    /**
     * setVolume function.<br>
     * If name is invalid or volume is out of range then print an error.
     *
     * @param name   is nameMusic
     * @param volume is volume value you want to set
     */
    public void setVolume(String name, float volume) {
        Music music = listMusics.get(name);
        if (music == null) {
            System.err.println(name + " does not exist.");
            System.err.println("Play fail.");
            return;
        }
        if (volume < 0 || volume > 1) {
            System.err.println("Out of range value.");
            return;
        }

        music.setVolume(volume);
    }

    public void pauseMusic(String name) {
        Music music = listMusics.get(name);
        if (music == null) {
            System.err.println(name + " does not exist.");
            System.err.println("Pause fail.");
            return;
        }
        music.pause();
    }

    public void stopMusic(String name) {
        Music music = listMusics.get(name);
        if (music == null) {
            System.err.println(name + " does not exist.");
            System.err.println("Stop fail.");
            return;
        }
        music.stop();
    }

    public void dispose() {
        for (Music music : listMusics.values()) {
            music.dispose();
        }
        listMusics.clear();
    }


}
