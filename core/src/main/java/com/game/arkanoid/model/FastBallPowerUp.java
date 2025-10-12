package com.game.arkanoid.model;

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
     * Áp dụng hiệu ứng: Tăng tốc độ hiện tại của Ball mà vẫn giữ nguyên hướng.
     * <p>Nó cũng lưu lại tốc độ gốc của Ball trước khi áp dụng hiệu ứng.</p>
     * * @param context Đối tượng GameContext.
     */
    @Override
    public void applyEffect(GameContext context) {
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
     * Hoàn tác hiệu ứng: Đặt tốc độ Ball trở lại tốc độ gốc đã lưu.
     * * @param context Đối tượng GameContext.
     */
    @Override
    public void removeEffect(GameContext context) {
        Ball ball = context.getBall();

        ball.applySpeed(ball.getOriginalSpeed());
        System.out.println(ball.getCurrentSpeed());
    }
}
