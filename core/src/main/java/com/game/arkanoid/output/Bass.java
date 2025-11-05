package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface Bass extends Library {
    Bass INSTANCE = (Bass) Native.load(
        Gdx.files.internal("dll/bass.dll").path(),
        Bass.class
    );

    int BASS_DEVICE_DEFAULT    = 0xFFFFFFFF;
    int BASS_MUSIC_RAMP        = 0x200;
    int BASS_MUSIC_LOOP        = 0x4;
    int BASS_POS_MUSIC_ORDER   = 0x1;
    int BASS_DEVICE_ENABLED	   = 0x1;
    int BASS_DEVICE_INIT	   = 0x4;
    int BASS_DEVICE_LOOPBACK   = 0x8;
    int BASS_DEVICE_DEFAULTCOM = 0x80;




    boolean BASS_Init(int device, int freq, int flags, long win, long cl);

    boolean BASS_Free();

    long BASS_MusicLoad(boolean mem, String file, long offset, int length, int flags, int freq);

    boolean BASS_MusicFree(long handle);

    boolean BASS_ChannelPlay(long handle, boolean restart);

    boolean BASS_ChannelSetPosition(long handle, long pos, int mode);

    boolean BASS_ChannelPause(long handle);

    boolean BASS_ChannelStop(long handle);

    boolean BASS_SetVolume(float volume);


}
