package com.game.arkanoid.engine;

public class Segment {
    public Point p0;
    public Point p1;

    public Segment(float x0, float y0, float x1, float y1) {
        this.p0 = new Point();
        this.p1 = new Point();
        this.p0.x = x0;
        this.p0.y = y0;
        this.p1.x = x1;
        this.p1.y = y1;
    }

    public Segment(Point p0, Point p1) {
        this.p0 = p0;
        this.p1 = p1;
    }
}
