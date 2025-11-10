package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.sun.jna.Native;

//MusicManager is a Singleton
public class MusicManager {
    private static MusicManager musicManager;

    private boolean mFlag;

    private long hMusic;

    private Bass bassInstance;

    private boolean isMuted;

    private MusicManager() {
        this.mFlag = false;
        this.isMuted = false;
        this.bassInstance = Native.load(Gdx.files.internal("dll/bass.dll").path(), Bass.class);
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
            musicManager.initBass();
            //Khi build dùng nó
            //String filePath = "sound/mainmusic.mo3";
            String filePath = "assets/sound/mainmusic.mo3";
            musicManager.hMusic = musicManager.bassInstance.BASS_MusicLoad(false, filePath, 0, 0, 0, 0);
            if (musicManager.hMusic == 0) {
                System.err.println("Unable to load music file: " + filePath);
            }
        }
        return musicManager;
    }


    public void dispose() {
        this.bassInstance.BASS_MusicFree(this.hMusic);
        this.bassInstance.BASS_Free();
    }

    public boolean BASS_setPattern(int pattern) {
        return this.bassInstance.BASS_ChannelSetPosition(hMusic, pattern, Bass.BASS_POS_MUSIC_ORDER);
    }

    public boolean BASS_playMusic() {
        return this.bassInstance.BASS_ChannelPlay(hMusic, false);
    }


    public boolean BASS_pauseMusic() {
        return this.bassInstance.BASS_ChannelPause(hMusic);
    }

    public boolean BASS_stopMusic() {
        return this.bassInstance.BASS_ChannelStop(hMusic);
    }

    public boolean BASS_setVolume(float volume) {
        return this.bassInstance.BASS_ChannelSetAttribute(hMusic, Bass.BASS_ATTRIB_VOL, volume);
    }


    public void initBass() {
        System.out.println("Trying to initialize BASS using JNA...");
        System.out.println(System.getProperty("java.library.path"));
        boolean isInitialized = this.bassInstance.BASS_Init(Bass.BASS_DEVICE_DEFAULT, 44100, 0, 0, 0);
        if (!isInitialized) {
            System.err.println("BASS initialization failed!");
            return;
        }
        System.out.println("BASS initialized successfully!");
    }


    public long getHandleMusic() {
        return this.hMusic;
    }

    public boolean getmFlag() {
        return this.mFlag;
    }

    public void setmFlag(boolean mFlag) {
        this.mFlag = mFlag;
    }

    public boolean getIsMuted() {
        return this.isMuted;
    }

    public void setMuted(boolean muted) {
        this.isMuted = muted;
    }
}
