package com.game.arkanoid;

import com.game.arkanoid.model.Brick;
import com.game.arkanoid.world.LevelLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class LevelLoaderTest {
    LevelLoader levelLoader;

    @Before
    public void setUp() {
        levelLoader = new LevelLoader();
    }
    //Tại sao fail, vì Gdx chưa được khởi tạo thôi
    @Test
    public void testI() {
        List<Brick> bricks = levelLoader.loadLevel(1);
        assertNotNull(bricks);
    }
    //Tại sao fail, vì Gdx chưa được khởi tạo thôi
    @Test
    public void testII() {
        List<Brick> bricks = levelLoader.loadLevel(9990);
        assertNotNull(bricks);
    }
}
