package com.game.arkanoid.engine;

public class AABB {
    public Point min;
    public Point max;

    public AABB(float xMin, float yMin, float xMax, float yMax) {
        this.min = new Point();
        this.max = new Point();
        this.min.x = xMin;
        this.min.y = yMin;
        this.max.x = xMax;
        this.max.y = yMax;
    }

    public AABB(Point min, Point max) {
        this.min = min;
        this.max = max;
    }
}
