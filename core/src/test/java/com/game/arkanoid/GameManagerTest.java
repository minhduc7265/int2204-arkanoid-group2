package com.game.arkanoid;

import com.game.arkanoid.engine.GameManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameManagerTest {

    private GameManager gameManager;
    @Before
    public void setUp() {
        gameManager = GameManager.getGameManager();
        gameManager.setScore(0);
    }
    //Kiểm tra xem add điểm thì nó có ra điểm mong muốn không
    @Test
    public void testScoreI() {
        assertEquals(0, gameManager.getScore());
        gameManager.addScore(9877);
        assertEquals(9877, gameManager.getScore());
    }

    //Tương tự nhưng sai
    @Test
    public void testScoreII() {
        assertEquals(0, gameManager.getScore());
        gameManager.addScore(98);
        assertEquals(989, gameManager.getScore());
    }
    //Kiểm tra xem mạng sống có hoạt động đúng không
    @Test
    public void testLifeI() {
        gameManager.setLives(0);
        gameManager.loseLife();
        assertEquals(-1, gameManager.getLives());
        assertEquals(GameManager.GameState.GAMEOVER, gameManager.getGameState());

    }
    //Tương tự test trên
    @Test
    public void testLifeII() {
        gameManager.setLives(9);
        gameManager.loseLife();
        assertEquals(8, gameManager.getLives());
        assertEquals(GameManager.GameState.GAMEOVER, gameManager.getGameState());

    }
}
