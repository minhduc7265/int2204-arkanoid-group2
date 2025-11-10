package com.game.arkanoid;

import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.BrickFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BrickFactoryTest {
    private Brick brick;

    @Before
    public void setUp() {

    }
    //Kiểm tra xem có tạo đúng loại gạch có flag == 1 không
    @Test
    public void testBrickI() {
        brick = BrickFactory.call(5,0,0,0,0,0);
        assertNotNull(brick);
        assertEquals(5, brick.getFlag());

    }
    //Nếu như flag nằm ngoài khoảng giá trị thì nó có trả ngoại lệ không?
    @Test(expected = IllegalArgumentException.class)
    public void testBrickII() {
        brick = BrickFactory.call(-1,0,0,0,0,0);

    }

}
