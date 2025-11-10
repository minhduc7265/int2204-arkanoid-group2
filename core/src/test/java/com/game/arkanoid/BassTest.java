package com.game.arkanoid;

import com.game.arkanoid.output.Bass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jna.Native;

import static org.junit.Assert.assertEquals;

public class BassTest {
    private Bass bassi;
    private long hMusic;
    @Before
    public void setUp() {
        bassi = Native.load("C:\\Users\\hminh\\Desktop\\md_banrch\\int2204-arkanoid-group2\\assets\\dll\\bass.dll", Bass.class);
        bassi.BASS_Init(Bass.BASS_DEVICE_DEFAULT, 44100, 0, 0, 0);
    }
    //Tại sao false
    //Tại hMusic đã có gì đâu mà chả false
    @Test
    public void testI() {
        boolean test = bassi.BASS_SetVolume(0.5f);
        assertEquals(false, test);
    }
    //Khởi tạo đầy đủ, hmusic xác định
    //Volume đúng khoảng từ 0->1 nên trả true
    @Test
    public void testII() {
        hMusic = bassi.BASS_MusicLoad(false, "C:\\Users\\hminh\\Desktop\\md_banrch\\int2204-arkanoid-group2\\assets\\sound\\mainmusic.mo3", 0, 0, 0, 0);
        boolean test = bassi.BASS_SetVolume(0.5f);
        assertEquals(true,test);

    }
    //Như trên nhưng volume = 1.5 > 1
    @Test
    public void testIII() {
        hMusic = bassi.BASS_MusicLoad(false, "C:\\Users\\hminh\\Desktop\\md_banrch\\int2204-arkanoid-group2\\assets\\sound\\mainmusic.mo3", 0, 0, 0, 0);
        boolean test = bassi.BASS_SetVolume(1.5f);
        assertEquals(true,test);

    }

    @After
    public void clear() {
        bassi.BASS_MusicFree(hMusic);
        bassi.BASS_Free();
    }
}
