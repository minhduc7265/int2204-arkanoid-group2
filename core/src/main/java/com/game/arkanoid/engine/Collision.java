package com.game.arkanoid.engine;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Collision {
    //Lưu ý: Mã nguồn tham khảo từ cuốn Real Time Collision Detection của Christer Ericson
    public static final float EPSILON = 1e-6f;
    public static final Vector2 RIGHT = new Vector2(1f,0f);
    public static final Vector2 LEFT = new Vector2(-1f, 0f);
    public static final Vector2 UP = new Vector2(0f,1f);
    public static final Vector2 DOWN = new Vector2(0f, -1f);

    public static Point Corner(AABB b, int n) {
        Point p = new Point();
        p.x = ((n & 1) != 0 ? b.max.x : b.min.x);
        p.y = ((n & 2) != 0 ? b.max.y : b.min.y);
        return p;
    }


    public static int IntersectRayAABB(Point p, Vector2 d, AABB a, CollisionResult collisionResult, Point q) {
        float tMIN = 0.0f;
        float tMAX = Float.MAX_VALUE;


        if (Math.abs(d.x) < EPSILON) {//Nếu vận tốc x ~ 0
            if (p.x < a.min.x || p.x > a.max.x) return 0;// Nếu nằm ngoài thì thôi
        } else {
            float oneOverDx = 1.0f / d.x;
            //Tính toán t1 và t2 -> t vào và t ra
            float t1 = (a.min.x - p.x) * oneOverDx;
            float t2 = (a.max.x - p.x) * oneOverDx;
            //Chuẩn hóa t1, t2
            if (t1 > t2) {
                float ttemp = t1;
                t1 = t2;
                t2 = ttemp;
            }
            tMIN = Math.max(tMIN, t1);
            tMAX = Math.min(tMAX, t2);

            //Nếu tMIN > tMAX -> vô lí nên dẹp
            if (tMIN > tMAX) return 0;
        }

        if (Math.abs(d.y) < EPSILON) {//Nếu vận tốc y ~ 0
            if (p.y < a.min.y || p.y > a.max.y) return 0;// Nếu nằm ngoài thì thôi
        } else {
            float oneOverDy = 1.0f / d.y;
            //Tính toán t1 và t2 -> t vào và t ra
            float t1 = (a.min.y - p.y) * oneOverDy;
            float t2 = (a.max.y - p.y) * oneOverDy;
            //Chuẩn hóa t1, t2
            if (t1 > t2) {
                float ttemp = t1;
                t1 = t2;
                t2 = ttemp;
            }
            tMIN = Math.max(tMIN, t1);
            tMAX = Math.min(tMAX, t2);

            //Nếu tMIN > tMAX -> vô lí nên dẹp
            if (tMIN > tMAX) return 0;
        }

        collisionResult.timeCollision = tMIN;

        q.x = p.x + tMIN * d.x;
        q.y = p.y + tMIN * d.y;

        return 1;


    }


    public static int IntersectMovingSphereAABB(Circle c, Vector2 d, Rectangle rec, CollisionResult collisionResult) {
        //c là cái quả bóng
        //rec là viên gạch
        //d là vector di chuyển tức là hướng quả bóng nó sẽ di chuyển
        //Convert rec thành AABB
        AABB b = new AABB(0f, 0f, 0f, 0f);
        b.min.x = rec.x;
        b.min.y = rec.y;
        b.max.x = rec.x + rec.width;
        b.max.y = rec.y + rec.height;
        //Tạo hình mở rộng[minkow]
        AABB e = new AABB(0f, 0f, 0f, 0f);
        e.min.x = b.min.x - c.radius;
        e.min.y = b.min.y - c.radius;
        e.max.x = b.max.x + c.radius;
        e.max.y = b.max.y + c.radius;


        Point p = new Point();

        //Tức là không va
        if (IntersectRayAABB(new Point(c.x, c.y), d, e, collisionResult, p) == 0 || collisionResult.timeCollision > 1f) {
            return 0;
        }

        int u = 0;
        int v = 0;

        //Bây giờ xem nó đang va ở chỗ nào
        if (p.x < b.min.x) u |= 1;
        if (p.x > b.max.x) v |= 1;
        if (p.y < b.min.y) u |= 2;
        if (p.y > b.max.y) v |= 2;

        //Lấy kết quả sau
        int m = u + v;

        if (m == 0) {
            collisionResult.typeCollision = CollisionResult.TypeCollision.INSIDE;
            return 1;
        }

        //Tạo đường di chuyển
        //Kết quả sẽ là 1 đường thằng với điểm đầu và điểm cuối
        Segment seg = new Segment(new Point(c.x, c.y), new Point(c.x + d.x, c.y + d.y));

        //Lây kết quả sau
        boolean isEgde = (m & (m - 1)) == 0;

        if (isEgde) {
            if (m == 1) {
                collisionResult.normal = (u == 1 ? new Vector2(RIGHT) : new Vector2(LEFT));
                collisionResult.typeCollision = CollisionResult.TypeCollision.VERTICAL_EDGE;
            } else if (m == 2) {
                collisionResult.normal = (u == 2 ? new Vector2(UP) : new Vector2(DOWN));
                collisionResult.typeCollision = CollisionResult.TypeCollision.HORIZONTAL_EDGE;
            }

            return 1;
        }

        //Nếu m == 3 thì sử dụng hàm sau

        collisionResult.timeCollision = Float.MAX_VALUE;

        Point corner = Collision.Corner(b, v);
        return IntersectSegmentCircle(seg, corner, c.radius, collisionResult);


    }

    private static int IntersectSegmentCircle(Segment seg, Point p, float r, CollisionResult collisionResult) {
        Vector2 d = new Vector2(seg.p1.x - seg.p0.x, seg.p1.y - seg.p0.y);
        Vector2 m = new Vector2(seg.p0.x - p.x, seg.p0.y - p.y);
        float pow2r = r * r;
        //Tính 3 hệ số sau
        float a = d.dot(d);
        float b = 2f * m.dot(d);
        float c = m.dot(m) - pow2r;

        if (c < 0.0f) {
            collisionResult.timeCollision = 0.0f;
            return 1;
        }
        float delta = b * b - 4f * a * c;
        //Delta < 0 thì vô nghiệm tức không va gì hết
        if (delta < 0.0f) {
            return 0;
        }


        float t0 = (-b + (float) Math.sqrt(delta)) / (2.0f * a);
        float t1 = (-b - (float) Math.sqrt(delta)) / (2.0f * a);

        boolean ifChangedTime = false;

        if (t0 >= 0.0f && t0 <= 1.0f && t0 < collisionResult.timeCollision) {
            collisionResult.timeCollision = t0;
            ifChangedTime = true;
        }
        if (t1 >= 0.0f && t1 <= 1.0f && t1 < collisionResult.timeCollision) {
            collisionResult.timeCollision = t1;
            ifChangedTime = true;
        }
        if (ifChangedTime) {
            collisionResult.typeCollision = CollisionResult.TypeCollision.VERTEX;
            float impactX = seg.p0.x + d.x * collisionResult.timeCollision;
            float impactY = seg.p0.y + d.y * collisionResult.timeCollision;
            Vector2 normal = new Vector2(impactX - p.x, impactY - p.y);
            normal.nor();
            collisionResult.normal.set(normal);
            return 1;
        }
        return 0;

    }


}
