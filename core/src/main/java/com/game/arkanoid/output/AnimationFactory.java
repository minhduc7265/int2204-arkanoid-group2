package com.game.arkanoid.output;

public class AnimationFactory {
    public static enum TypeAnimation {
        CLOUD_ANIM,
        GAS_ANIM,
        FIRE_ANIM,
        BREAK_ANIM

    }
    public static void call(TypeAnimation type, int posX, int posY){
        switch (type) {
            case CLOUD_ANIM -> AnimationRender.getListAnimationRender().add(new AnimationRender("CLOUD_ANIM", posX, posY));
            case GAS_ANIM -> AnimationRender.getListAnimationRender().add(new AnimationRender("GAS_ANIM", posX, posY));
            case FIRE_ANIM -> AnimationRender.getListAnimationRender().add(new AnimationRender("FIRE_ANIM", posX, posY));
            case BREAK_ANIM -> AnimationRender.getListAnimationRender().add(new AnimationRender("BREAK_ANIM", posX, posY));
            default -> {
                AnimationRender.getListAnimationRender().add(new AnimationRender("CLOUD_ANIM", posX, posY));
                System.err.println("Lỗi Animation, render mặc định");
            }

        }
    }
}
