package com.game.arkanoid;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.arkanoid.engine.Collision;
import com.game.arkanoid.engine.CollisionResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionTest {

    @Before
    public void setUp() {
    }
    //Test này test xem có var nhau không giữa hình tròn và hình chữ nhật
    //Tham số sẽ có move là vector di chuyển và một con trỏ để lưu kê quả
    @Test
    public void testIntersectMovingSphereAABB() {
        Circle ball = new Circle(5f, 5f, 1f);
        Rectangle rect = new Rectangle(8f, 4f, 4f, 4f);
        Vector2 move = new Vector2(5f, 0f);
        CollisionResult result = new CollisionResult();

        int hit = Collision.IntersectMovingSphereAABB(ball, move, rect, result);
        assertEquals(1, hit);
        assertTrue(result.timeCollision > 0f && result.timeCollision <= 1f);
        assertNotNull(result.normal);
        assertNotNull(result.typeCollision);
    }


}
