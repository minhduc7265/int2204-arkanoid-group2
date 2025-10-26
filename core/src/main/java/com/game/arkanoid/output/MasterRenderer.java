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
    private SoundManager soundManager;
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
    //Music
    private long hMusic;

    public static boolean musicFlag;


    public MasterRenderer() {
        MasterRenderer.musicFlag = false;
        GameObject.setScreenWidth(800);
        GameObject.setScreenHeight(800);
        textureManager = TextureManager.getInstance();
        soundManager = SoundManager.getInstance();

        loadBass();

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
        if (MasterRenderer.musicFlag != true) {
            Bass.INSTANCE.BASS_ChannelSetPosition(hMusic, 48 | (0 << 8), Bass.BASS_POS_MUSIC_ORDER);
            Bass.INSTANCE.BASS_ChannelPlay(hMusic, false);
            MasterRenderer.musicFlag = true;
        }
        renderer.beginRender();
        renderer.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), background);
        renderer.endRender();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void renderGameWorld(GameWorld world) {
        if (MasterRenderer.musicFlag != true) {
            Bass.INSTANCE.BASS_ChannelSetPosition(hMusic, 0 | (0 << 8), Bass.BASS_POS_MUSIC_ORDER);
            Bass.INSTANCE.BASS_ChannelPlay(hMusic, false);


            MasterRenderer.musicFlag = true;
        }
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


        Bass.INSTANCE.BASS_MusicFree(hMusic);
        Bass.INSTANCE.BASS_Free();
    }


    public void loadBass() {
        System.out.println("Trying to initialize BASS using JNA...");
        System.out.println(System.getProperty("java.library.path"));
        boolean isInitialized = Bass.INSTANCE.BASS_Init(Bass.BASS_DEVICE_DEFAULT, 44100, 0, 0, 0);
        if (!isInitialized) {
            System.err.println("BASS initialization failed!");
            return;
        }
        System.out.println("BASS initialized successfully!");
        //Khi build dùng nó
        //String filePath = "sound/mainmusic.mo3";
        String filePath = "assets/sound/mainmusic.mo3";
        hMusic = Bass.INSTANCE.BASS_MusicLoad(false, filePath, 0, 0, 0, 0);
        if (hMusic == 0) {
            System.err.println("Unable to load music file: " + filePath);
            Bass.INSTANCE.BASS_Free();
            return;
        }
    }
}
