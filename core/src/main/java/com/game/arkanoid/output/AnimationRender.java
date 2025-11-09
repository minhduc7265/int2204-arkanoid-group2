package com.game.arkanoid.output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class AnimationRender {
    private static final List<AnimationRender> listAnimationRender = new ArrayList<>();
    private float stateTime;
    private float posX;
    private float posY;
    private boolean finished;
    private String name;


    public AnimationRender(String nameAnimation, float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.stateTime = 0f;
        this.finished = false;
        this.name = nameAnimation;
    }


    public void renderAnimation(Renderer renderer) {
        if (isFinished()) return;
        stateTime += Gdx.graphics.getDeltaTime();

        AnimationSpriteSheet current = AnimationSpriteSheet.getAnimationSpriteSheet(name);
        Animation<TextureRegion> animation = AnimationSpriteSheet.getAnimation(name);

        TextureRegion currentframe = animation.getKeyFrame(stateTime);
        //renderer.beginRender();
        renderer.render(posX - current.getWidth() / 2, posY - current.getHeight() / 2, currentframe, current);
        //renderer.endRender();

        if (animation.getPlayMode() == Animation.PlayMode.NORMAL &&
            animation.isAnimationFinished(stateTime)) {
            finished = true;
        }


    }

    public boolean isFinished() {
        return this.finished;
    }

    public static List<AnimationRender> getListAnimationRender() {
        return listAnimationRender;
    }

}
