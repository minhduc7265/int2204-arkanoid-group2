package com.game.arkanoid.model;

/**
 * PowerUp làm tăng kích thước chiều rộng của Paddle.
 * Hiệu ứng là tạm thời và được hoàn tác sau khi hết thời gian.
 */
public class ExpandPaddlePowerUp extends PowerUp { 
    private static final int EXPAND_AMOUNT = 30;
    private static final int DEFAULT_DURATION = 600;

    /**
     * Khởi tạo ExpandPaddlePowerUp.
     * @param x Tọa độ x ban đầu.
     * @param y Tọa độ y ban đầu.
     */
    public ExpandPaddlePowerUp(float x, float y) {
        super(x, y, "Expand", DEFAULT_DURATION);
    }

    /**
     * Áp dụng hiệu ứng lên Paddle. (Ball không bị ảnh hưởng)
     * @param paddle Đối tượng Paddle cần thay đổi.
     * @param ball Đối tượng Ball
     */
    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        Paddle paddle = context.getPaddle();

        if (!isPickedUp()) {
            paddle.setOriginalWidth(paddle.getCurrentWidth());
        }

        paddle.setCurrentWidth(paddle.getCurrentWidth() + EXPAND_AMOUNT);
        setPickedUp(true);
        System.out.println(paddle.getCurrentWidth());
    }

    /**
     * Hoàn tác hiệu ứng cho Paddle.
     * @param paddle Đối tượng Paddle cần hoàn tác.
     * @param ball Đối tượng Ball 
     */
    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        Paddle paddle = context.getPaddle();

        paddle.setCurrentWidth(paddle.getOriginalWidth());
        System.out.println(paddle.getCurrentWidth());
    }
}


