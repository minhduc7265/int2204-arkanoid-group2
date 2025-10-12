package com.game.arkanoid.model;

/**
 * PowerUp làm tăng tốc độ di chuyển của Ball.
 * Hiệu ứng là tạm thời và được hoàn tác sau khi hết thời gian.
 */
public class FastBallPowerUp extends PowerUp { 
    private static final float SPEED_MULTIPLIER = 1.5f;
    private static final int DEFAULT_DURATION = 480;

    /**
     * Khởi tạo FastBallPowerUp.
     * @param x Tọa độ x ban đầu.
     * @param y Tọa độ y ban đầu.
     */
    public FastBallPowerUp(float x, float y) {
        super(x, y, "Fast", DEFAULT_DURATION);
    }

    /**
     * Áp dụng hiệu ứng lên Ball. (Paddle không bị ảnh hưởng)
     * @param paddle Đối tượng Paddle 
     * @param ball Đối tượng Ball cần thay đổi.
     */
    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        Ball ball = context.getBall();

        if (!isPickedUp()) {
            ball.setOriginalSpeed(ball.getCurrentSpeed());
        }

        float newSpeed = ball.getCurrentSpeed() * SPEED_MULTIPLIER;
        ball.applySpeed(newSpeed);

        setPickedUp(true);
        System.out.println(ball.getCurrentSpeed());
    }

    /**
     * Hoàn tác hiệu ứng cho Ball.
     * @param paddle Đối tượng Paddle 
     * @param ball Đối tượng Ball cần hoàn tác.
     */
    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        Ball ball = context.getBall();

        ball.applySpeed(ball.getOriginalSpeed());
        System.out.println(ball.getCurrentSpeed());
    }
}
