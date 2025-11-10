package com.game.arkanoid;

import com.badlogic.gdx.Game;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.GameObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BallTest {

    private Ball ball;
    @Before
    public void setUp() {
        ball = new Ball(100f, 200f, 20,19,400f);
    }
    //Kiểm tra xem nếu có sẵn vận tốc và tọa độ sẵn thì sau khi update thì có đúng không
    @Test
    public void testMoveI() {
        //Set màn hình để tránh màn hình 0x0
        //Thì lúc đó là bóng sẽ bị bao bọc, hiểu đơn giản là luôn va vào màn hình gây lỗi
        GameObject.setScreenHeight(800);
        GameObject.setScreenWidth(800);

        ball.setVelocity(5, -4);
        ball.update(1f);
        float posX = ball.getXPos();
        float posY = ball.getYPos();

        assertEquals(105f, posX, 0.001f);
        assertEquals(196f, posY, 0.001f);
    }
    //Tương tự test trên nhưng kết quả sai
    @Test
    public void testMoveII() {
        //Set màn hình để tránh màn hình 0x0
        //Thì lúc đó là bóng sẽ bị bao bọc, hiểu đơn giản là luôn va vào màn hình gây lỗi
        GameObject.setScreenHeight(800);
        GameObject.setScreenWidth(800);

        ball.setVelocity(5, 3);
        ball.update(1f);
        float posX = ball.getXPos();
        float posY = ball.getYPos();

        assertEquals(105f, posX, 0.001f);
        assertEquals(197f, posY, 0.001f);
    }


}
