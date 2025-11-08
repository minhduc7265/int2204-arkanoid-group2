package com.game.arkanoid.model;

public class BrickFactory {

    public static Brick call(int flag, int item, float x, float y, int w, int h) {
        return switch (flag) {
            case 1,2,3,4 -> new NormalBrick(x, y, w, h, flag, item, 1);
            case 5 -> new StrongBrick(x, y, w, h, flag, item,2);
            case 6 -> new StrongBrick(x, y, w, h, flag, item,1);
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
