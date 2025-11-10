package com.game.arkanoid;

import com.game.arkanoid.output.TextObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextObjectTest {
    TextObject textObject;
    @Before
    public void setUp() {
        textObject = null;
    }
    //Kiểm tra xem nó có "không" null không
    @Test
    public void testI() {
        assertNotNull(textObject);
    }
    //Kiểm tra xem textObject khi tạo có rỗng không và content có rỗng không khi dùng Contructor mặc định
    @Test
    public void testII() {
        textObject = new TextObject();
        assertNotNull(textObject);
        assertEquals("", textObject.getContent());
    }
    //Kiểm tra với contructor nhiều tham số
    @Test
    public void testIII() {
        textObject = new TextObject("Test", 90f, 100f);
        assertNotNull(textObject);
        assertEquals("Test", textObject.getContent());
        assertEquals(90f, textObject.getX(), 0.001f);
        assertEquals(100f, textObject.getY(),0.001f);
    }
}
