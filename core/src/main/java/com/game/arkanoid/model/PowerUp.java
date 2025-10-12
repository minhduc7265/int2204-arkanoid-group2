package com.game.arkanoid.model;

import com.game.arkanoid.model.Paddle;
import com.game.arkanoid.model.Ball;

public class PowerUp extends GameObject { 
    private final String type;
    private int duration;
    private boolean isPickedUp = false;
    private final float FALL_SPEED = 2.0f;

    /**
     * Khởi tạo PowerUp.
     * @param x Tọa độ x ban đầu.
     * @param y Tọa độ y ban đầu.
     * @param type Loại PowerUp.
     * @param duration Thời gian hiệu lực ban đầu.
     */
    public PowerUp(float x, float y, String type, int duration) {
        super();
        setPosition(x, y);
        setArea(30, 15);
        setTextureID("PowerUp_" + type);
        setStatus(true);

        this.type = type;
        this.duration = duration;
    }

    /**
     * Cập nhật trạng thái của PowerUp: rơi nếu chưa nhặt, hoặc đếm ngược thời gian nếu đã nhặt.
     * <p>PowerUp sẽ tự đặt status=false khi hết thời gian hiệu lực để báo hiệu cần removeEffect.</p>
     */
    @Override
    public void update() {
        if (getStatus() && !isPickedUp) {
            setPosition(getXPos(), getYPos() - FALL_SPEED);
        } else if (isPickedUp) {
            if (duration > 0) {
                duration--;
            } else {
                setStatus(false);
            }
        }
    }

    public abstract void applyEffect(GameContext context);

    public abstract void removeEffect(GameContext context);

    /**
     * Lấy loại PowerUp.
     * @return Chuỗi đại diện cho loại PowerUp.
     */
    public String getType() { return type; }

    /**
     * Lấy thời gian hiệu lực còn lại.
     * @return Số nguyên thời gian còn lại.
     */
    public int getDuration() { return duration; }

    /**
     * Kiểm tra xem PowerUp đã được nhặt hay chưa.
     * @return true nếu đã nhặt, false nếu đang rơi.
     */
    public boolean isPickedUp() { return isPickedUp; }

    /**
     * Đặt trạng thái đã được nhặt.
     * @param pickedUp true nếu PowerUp vừa được nhặt.
     */
    public void setPickedUp(boolean pickedUp) {
        this.isPickedUp = pickedUp;
    }

    /**
     * Giảm thời gian hiệu lực còn lại.
     * <p>Thường được gọi bởi phương thức update() của PowerUp hoặc bởi logic timer bên ngoài.</p>
     * @param amount Lượng thời gian cần giảm.
     */
    public void decreaseDuration(int amount) {
        this.duration -= amount;
    }

    /**
     * Kiểm tra xem PowerUp đã hết thời gian hiệu lực chưa.
     * @return true nếu đã nhặt VÀ thời gian hiệu lực <= 0.
     */
    public boolean isFinished() {
        return isPickedUp && duration <= 0;
    }
}
}
