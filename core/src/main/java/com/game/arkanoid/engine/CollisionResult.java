package com.game.arkanoid.engine;

import com.badlogic.gdx.math.Vector2;

public class CollisionResult {
    public float timeCollision;
    public enum TypeCollision{
        INSIDE,/** m = 0*/
        VERTICAL_EDGE,/** m = 1*/
        HORIZONTAL_EDGE,/** m = 2*/
        VERTEX,/** m = 3*/
        INSIDE_X,
        INSIDE_Y

    }
    public TypeCollision typeCollision;
    public Vector2 normal;
    public CollisionResult() {
        normal = new Vector2();
        timeCollision = 0f;
        typeCollision = TypeCollision.INSIDE;
    }
}
