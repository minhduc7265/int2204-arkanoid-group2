package com.game.arkanoid;

import com.game.arkanoid.output.TextureManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TextureTest {
    private TextureManager textureManager;

    @Before
    public void setUp() {
        textureManager = TextureManager.getInstance();

    }
    //Đương nhiên là fail vì Texture phụ thuộc OpenGL
    //Tức là OpenGL chưa chạy thì Texture vô dụng
    @Test
    public void testI() {
        textureManager.loadTextures("BRICK_k", "textures/brick_1.png");
        assertNotNull(textureManager.getTexture("BRICK_k"));

    }
}
