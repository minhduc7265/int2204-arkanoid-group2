package com.game.arkanoid.engine;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Collision {
    //Lưu ý: Mã nguồn tham khảo từ cuốn Real Time Collision Detection của Christer Ericson
    public static final float EPSILON = 1e-6f;


    /**
     * @param center     là tọa độ tâm HIỆN TẠI
     * @param vectorMove là vector di chuyển tức cháu nó đi được bao xa theo hai hướng
     * @param minkow     là cái hình to để so sánh
     * @return chạm hay không chạm
     */
    public static boolean IntersectRayAABB(Vector2 center, Vector2 vectorMove, Rectangle minkow, float[] timeInfo, Vector2 point) {
        float t_min = 0f;
        float t_max = 1f;
        int hit = -1;

        /// Trục OX
        // Nếu vận tốc Trục OX = 0 Hay vectorMove.x == 0
        if (Math.abs(vectorMove.x) < Collision.EPSILON) {
            //Nếu tia bắn vào nằm ngoài thì không chạm
            if (center.x < minkow.x || center.x > minkow.x + minkow.width) {//    .   [      ]   .
                return false;
            }
        } else {
            float opVectorMoveX = 1f / vectorMove.x;//One per VectorMoveX
            float t1_X = (minkow.x - center.x) * opVectorMoveX;
            float t2_X = (minkow.x + minkow.width - center.x) * opVectorMoveX;
            if (t1_X > t2_X) {
                float temp = t2_X;
                t2_X = t1_X;
                t1_X = temp;
            }
            if (t1_X > t_min) hit = 0;

            t_min = Math.max(t_min, t1_X);
            t_max = Math.min(t_max, t2_X);

            if (t_min > t_max) return false;


        }

        if (Math.abs(vectorMove.y) < Collision.EPSILON) {
            //Nếu tia bắn vào nằm ngoài thì không chạm
            if (center.y < minkow.y || center.y > minkow.y + minkow.height) {//    .   [      ]   .
                return false;
            }
        } else {
            float opVectorMoveY = 1f / vectorMove.y;//One per VectorMoveY
            float t1_Y = (minkow.y - center.y) * opVectorMoveY;
            float t2_Y = (minkow.y + minkow.height - center.y) * opVectorMoveY;
            if (t1_Y > t2_Y) {
                float temp = t2_Y;
                t2_Y = t1_Y;
                t1_Y = temp;
            }
            if (t1_Y > t_min) hit = 1;

            t_min = Math.max(t_min, t1_Y);
            t_max = Math.min(t_max, t2_Y);

            if (t_min > t_max) return false;


        }

        if (timeInfo != null) {
            timeInfo[0] = t_min;
            timeInfo[1] = (float) hit;
        }
        if (point != null) {
            point.x = center.x + vectorMove.x * t_min;
            point.y = center.y + vectorMove.y * t_min;
        }

        return true;


    }


    public static int IntersectMovingSphereAABB(Circle cir, Vector2 vectorMove, Rectangle reg, float[] timeInfo) {
        //Bước 1, tạo tổng Minkowski cho [cir] và [reg]
        Rectangle minkowReg = new Rectangle(0f, 0f, 0f, 0f);
        minkowReg.x = reg.x - cir.radius;
        minkowReg.y = reg.y - cir.radius;
        minkowReg.width = reg.width + 2 * cir.radius;
        minkowReg.height = reg.height + 2 * cir.radius;


        Vector2 point = new Vector2(0f, 0f);

        //Tính toán chỗ va chạm
        if (!IntersectRayAABB(new Vector2(cir.x, cir.y), vectorMove, minkowReg, timeInfo, point) || timeInfo[0] > 1f) {
            return 0;//Không chạm thì dừng luôn khỏi làm gì
        }


        int u = 0;
        int v = 0;

        //Tìm xem nó sẽ va chỗ nào.
        if (point.x < reg.x) u = u | 1;
        if (point.x > reg.x + reg.width) v = v | 1;
        if (point.y < reg.y) u = u | 2;
        if (point.y > reg.y + reg.height) v = v | 2;


        int m = u + v;
        boolean isEdgeRegion = (m & (m - 1)) == 0;

        if (isEdgeRegion) {
            return 1;
        } else {//Trường hợp va chạm đỉnh sẽ xử lí sau
            return 0;

        }

    }
}
