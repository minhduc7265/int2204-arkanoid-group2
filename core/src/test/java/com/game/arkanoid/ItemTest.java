package com.game.arkanoid;

import com.game.arkanoid.model.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    private Item item;
    @Before
    public void setUp() {
    }
    //Xem type giống nhau không thôi
    @Test
    public void testI() {
        item = new Item(0,0, Item.ItemType.BALL_INVISIBLE, 0);
        assertEquals(Item.ItemType.BALL_INVISIBLE, item.getType());
    }
    //Nếu type bằng null thì
    //Check xem nó có ném ngoại lệ không
    @Test(expected = IllegalArgumentException.class)
    public void testII() {
        item = new Item(0,0, null, 0);
        String texture = item.getTexture();
    }
}
