package com.game.arkanoid;

import com.game.arkanoid.model.Brick;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BrickTest {

    private Brick brick;
    @Before
    public void setUp() {

    }
    //Xem có đúng texture không?
    @Test
    public void testI() {
        brick = new Brick(0,0,0,0,2,2,2);
        assertEquals("BRICK_2", brick.getTexture());
    }
    //Xem có đúng texture không?
    @Test
    public void testII() {
        brick = new Brick(0,0,0,0,5,2,2);
        assertEquals("BRICK_5", brick.getTexture());
    }
    //Xem có đúng texture không?
    @Test
    public void testIII() {
        brick = new Brick(0,0,0,0,5,2,1);
        assertEquals("BRICK_5", brick.getTexture());
    }
    //Nếu flag ra ngoài khoảng thì có trả về TEXTURE mặc định không
    @Test
    public void testIV() {
        brick = new Brick(0,0,0,0,9990,2,2);
        assertEquals("DEFAULT_TEXTURE", brick.getTexture());
    }
}
