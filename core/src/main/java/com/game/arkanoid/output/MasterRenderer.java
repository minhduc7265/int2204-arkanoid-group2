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
        MusicManager.getInstance();
        AnimationSpriteSheet.loadAnim();


        renderer = new Renderer();
        font = new FontManager("font/font_a.ttf", 24);
        highScore = new TextObject("", 50f, 50f);
        currentScore = new TextObject("", 50f, 764f);
        currentHeart = new TextObject("", 50f, 728f);
        currentLevel = new TextObject("", 0f, 24f);

        GameManager.getGameManager().readData();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }



    public void renderMainMenu(Texture background, Stage stage) {
        playMainMenuMusic();
        drawBackground(background);
        drawStage(stage);
    }




    public void renderGameWorld(GameWorld world) {
        playGameWorldMusic();
        renderer.beginRender();
        renderBackgound();
        renderBrick(world);
        renderItem(world);
        renderAnimation();
        renderBall(world);
        renderPaddle(world);
        renderer.endRender();
    }




    public void renderHUD(GameManager gameManager) {
        setScoreString(gameManager);
        setHeartString(gameManager);
        setLevelString(gameManager);
        //Render Text
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
        int score = gameManager.getHighScore();
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
        soundManager.dispose();
        MusicManager.getInstance().dispose();
        // font.dispose();
    }


    private void setScoreString(GameManager gameManager) {
        int score = gameManager.getScore();
        String scoreString = String.format("Score: %d", score);
        currentScore.setContent(scoreString);
    }

    private void setHeartString(GameManager gameManager){
        int heart = gameManager.getLives();
        String heartString = String.format("Heart: %d", heart);
        currentHeart.setContent(heartString);
    }
    private void setLevelString(GameManager gameManager){
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
    }

    private void playGameWorldMusic() {
        if(MusicManager.getInstance().getmFlag() != true) {
            MusicManager.getInstance().BASS_setPattern(0x6 | (0 << 8));
            MusicManager.getInstance().BASS_playMusic();
            MusicManager.getInstance().setmFlag(true);
        }
    }

    private void renderBrick(GameWorld world) {
        List<Brick> bricks = world.getBricks();
        for (Brick brick : bricks) {
            renderer.render(brick, textureManager.getTexture(brick.getTexture()));
        }
    }

    private void renderItem(GameWorld world) {
        List<Item> powers = world.getPowers();
        for(Item power : powers) {
            if(!power.getStatus()) continue;
            if(!power.isPickedUp()) {
                renderer.render(power, textureManager.getTexture(power.getTexture()));
            }
        }
    }

    private void renderAnimation(){
        List<AnimationRender> anims = AnimationRender.getListAnimationRender();
        Iterator<AnimationRender> it = anims.iterator();
        while (it.hasNext()) {
            AnimationRender currentAnim = it.next();
            if (currentAnim.isFinished()) {
                it.remove();
            } else {
                currentAnim.renderAnimation(renderer);
            }
        }
    }

    private void renderBall(GameWorld world) {
        Ball ball = world.getBalls();
        if(ball.getFlag() == 0) {
            renderer.renderCircleIncludeColor(ball, textureManager.getTexture("BALL_1"));
        }
        else{
            renderer.renderCircleIncludeColor(ball, textureManager.getTexture("BALL_2"));
        }

    }

    private void renderPaddle(GameWorld world) {
        Paddle paddle = world.getPaddle();
        renderer.render(paddle, textureManager.getTexture("PADDLE_NORMAL"));
    }

    private void renderBackgound() {
        renderer.render(0, 0, 800, 800, textureManager.getTexture("CHALLENGE_CHAOS_BG"));
    }

    private void playMainMenuMusic() {
        if(MusicManager.getInstance().getmFlag() != true) {
            MusicManager.getInstance().BASS_setPattern(0xC | (0 << 8));
            MusicManager.getInstance().BASS_playMusic();
            MusicManager.getInstance().setmFlag(true);
        }
    }

    private void drawBackground(Texture background) {
        renderer.beginRender();
        renderer.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), background);
        renderer.endRender();
    }

    private void drawStage(Stage stage) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }



}
