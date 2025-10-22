package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.Ball;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.GameObject;
import com.game.arkanoid.model.Paddle;
import com.game.arkanoid.world.GameWorld;

import java.util.List;

public class MasterRenderer {
    private Renderer renderer;
    private TextureManager textureManager;
    // TODO: Thêm FontManager để vẽ chữ
    private FontManager font;
    // Để render điểm cao nhất
    private TextObject highScore;
    // Để render điểm hiện tại
    private TextObject currentScore;
    //Để render số mạng còn lại
    private TextObject currentHeart;
    //Để render level hiện tại
    private TextObject currentLevel;


    public MasterRenderer() {
        GameObject.setScreenWidth(800);
        GameObject.setScreenHeight(800);
        textureManager = TextureManager.getInstance();
        loadTexture();
        renderer = new Renderer();
        font = new FontManager("font/font_a.ttf", 24);
        highScore = new TextObject("", 50f, 50f);
        currentScore = new TextObject("", 50f, 764f);
        currentHeart = new TextObject("", 50f, 728f);
        currentLevel = new TextObject("", 0f, 24f);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void renderMainMenu(Texture background, Stage stage) {
        renderer.beginRender();
        renderer.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), background);
        renderer.endRender();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void renderGameWorld(GameWorld world) {
        List<Brick> bricks = world.getBricks();
        Ball ball = world.getBalls();
        Paddle paddle = world.getPaddle();
        renderer.beginRender();
        renderer.render(0, 0, 800, 800, textureManager.getTexture("CHALLENGE_CHAOS_BG"));
        for (Brick brick : bricks) {
            renderer.render(brick, textureManager.getTexture("BRICK_1"));
        }

        renderer.render(ball, textureManager.getTexture("BALL_1"));


        renderer.render(paddle, textureManager.getTexture("PADDLE_NORMAL"));
        // TODO: Vẽ paddle, ball, bricks từ world
        // Lấy texture từ một TextureManager tập trung (nếu có)
        renderer.endRender();
    }

    public void renderHUD(GameManager gameManager) {
        int score = gameManager.getScore();
        String scoreString = "Score: " + Integer.toString(score);
        currentScore.setContent(scoreString);

        int heart = gameManager.getLives();
        String heartString = "Heart: " + Integer.toString(heart);
        currentHeart.setContent(heartString);

        int level = gameManager.getCurrentLevel();
        String levelString = "Level " + Integer.toString(level);
        currentLevel.setContent(levelString);

        renderer.beginRender();
        renderer.render(font.getBitmapFont(), currentScore);
        renderer.render(font.getBitmapFont(), currentHeart);
        renderer.render(font.getBitmapFont(), currentLevel);
        renderer.endRender();
    }

    public void renderPauseText() {
        renderer.beginRender();
        // TODO: Dùng font để vẽ chữ "PAUSED"
        renderer.endRender();
    }

    public void renderHighScore(GameManager gameManager) {
        int score = gameManager.getScore();
        String scoreString = "High Score: " + Integer.toString(score);
        highScore.setContent(scoreString);

        renderer.beginRender();
        renderer.render(font.getBitmapFont(), highScore);
        renderer.endRender();
    }

    public void dispose() {
        font.dispose();
        renderer.dispose();
        textureManager.dispose();
        // font.dispose();
    }

    public void loadTexture() {
        textureManager.loadTextures("BRICK_1", "textures/brick_1.png");
        textureManager.loadTextures("BRICK_2", "textures/brick_2.png");
        textureManager.loadTextures("BRICK_3", "textures/brick_3.png");
        textureManager.loadTextures("BALL_1", "textures/ball_1.png");
        textureManager.loadTextures("BALL_2", "textures/ball_2.png");
        textureManager.loadTextures("BALL_3", "textures/ball_3.png");
        textureManager.loadTextures("PADDLE_NORMAL", "textures/paddle.png");
        textureManager.loadTextures("CHALLENGE_CHAOS_BG", "images/challenge_chaos_bg.png");
    }
}
