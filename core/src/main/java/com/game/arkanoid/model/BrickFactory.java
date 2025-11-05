package com.game.arkanoid.model;

public class BrickFactory {

    public static Brick call(int flag, int item, float x, float y, int w, int h) {
        return switch (flag) {
            case 1 -> new NormalBrick(x, y, w, h, "", flag, item);
            case 2 -> new NormalBrick(x, y, w, h, "", flag, item);
            case 3 -> new NormalBrick(x, y, w, h, "", flag, item);
            case 4 -> new NormalBrick(x, y, w, h, "", flag, item);
            case 5 -> new StrongBrick(x, y, w, h, "", flag, item);
            case 6 -> new StrongBrick(x, y, w, h, "", flag, item);
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
