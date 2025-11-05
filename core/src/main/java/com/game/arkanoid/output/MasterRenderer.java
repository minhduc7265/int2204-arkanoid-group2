package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.arkanoid.engine.GameManager;
import com.game.arkanoid.model.*;
import com.game.arkanoid.world.GameWorld;
import java.util.Iterator;
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
    //Music--> Chuyển thành private khi tổ chức lại dữ liệu
    public static long hMusic;

    public static boolean musicFlag;


    public int timeChangeColor;


    public MasterRenderer() {
        timeChangeColor = 0;
        MasterRenderer.musicFlag = false;
        GameObject.setScreenWidth(800);
        GameObject.setScreenHeight(800);
        textureManager = TextureManager.getInstance();
        soundManager = SoundManager.getInstance();

        loadBass();
        AnimationSpriteSheet.loadAnim();


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
            Bass.INSTANCE.BASS_ChannelSetPosition(hMusic, 0xC | (0 << 8), Bass.BASS_POS_MUSIC_ORDER);
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
            Bass.INSTANCE.BASS_ChannelSetPosition(hMusic, 0x6 | (0 << 8), Bass.BASS_POS_MUSIC_ORDER);
            Bass.INSTANCE.BASS_ChannelPlay(hMusic, false);
            MasterRenderer.musicFlag = true;
        }
        List<Brick> bricks = world.getBricks();
        List<AnimationRender> anims = AnimationRender.getListAnimationRender();
        List<PowerUp> powers = world.getPowers();
        Ball ball = world.getBalls();
        Paddle paddle = world.getPaddle();
        renderer.beginRender();
        renderer.render(0, 0, 800, 800, textureManager.getTexture("CHALLENGE_CHAOS_BG"));
        for (Brick brick : bricks) {
            if(brick.getFlag() >= 1 && brick.getFlag() <= 6){
                String texture = String.format("BRICK_%d", brick.getFlag());
                renderer.render(brick, textureManager.getTexture(texture));
            }

        }
        for(PowerUp power : powers) {
            if(!power.getStatus()) continue;
            if(!power.isPickedUp()) {
                if(power.getType() == PowerUp.PowerUpType.FAST_BALL){
                    renderer.render(power, textureManager.getTexture("BOOST_SPEED"));
                }
                else if(power.getType() == PowerUp.PowerUpType.EXPAND_PADDLE) {
                    renderer.render(power, textureManager.getTexture("BOOST_PADDLE"));
                }

            }
        }
        renderer.renderCircle(ball, textureManager.getTexture("BALL_1"));
        renderer.render(paddle, textureManager.getTexture("PADDLE_NORMAL"));





        Iterator<AnimationRender> it = anims.iterator();
        while (it.hasNext()) {
            AnimationRender currentAnim = it.next();
            if (currentAnim.isFinished()) {
                it.remove();
            } else {
                currentAnim.renderAnimation(renderer);
            }
        }
        // TODO: Vẽ paddle, ball, bricks từ world
        // Lấy texture từ một TextureManager tập trung (nếu có)
        renderer.endRender();
    }

    public void renderHUD(GameManager gameManager) {
        int score = gameManager.getScore();
        String scoreString = String.format("Score: %d", score);
        currentScore.setContent(scoreString);

        int heart = gameManager.getLives();
        String heartString = String.format("Heart: %d", heart);
        currentHeart.setContent(heartString);

        int level = gameManager.getCurrentLevel();
        String levelString = String.format("Level %d", level);
        currentLevel.setContent(levelString);

        if (timeChangeColor++ > 100) {
            currentLevel.setColor((float) Math.random(),
                (float) Math.random(),
                (float) Math.random(),
                1f);
            timeChangeColor = 0;
        }

        renderer.beginRender();
        renderer.render(font.getBitmapFont(), currentScore);
        renderer.render(font.getBitmapFont(), currentHeart);
        renderer.setColor(currentLevel.getColor());
        renderer.render(font.getBitmapFont(), currentLevel);
        renderer.setColor(Color.WHITE);
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
