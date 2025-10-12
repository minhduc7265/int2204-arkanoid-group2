package com.game.arkanoid.model;

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
     * Áp dụng hiệu ứng: Tăng chiều rộng hiện tại của Paddle.
     * <p>Nó cũng lưu lại kích thước gốc của Paddle trước khi áp dụng hiệu ứng.</p>
     * * @param context Đối tượng GameContext.
     */
    @Override
    public void applyEffect(GameContext context) {
        Paddle paddle = context.getPaddle();

        if (!isPickedUp()) {
            paddle.setOriginalWidth(paddle.getCurrentWidth());
        }

        paddle.setCurrentWidth(paddle.getCurrentWidth() + EXPAND_AMOUNT);
        setPickedUp(true);
        System.out.println(paddle.getCurrentWidth());
    }

    /**
     * Hoàn tác hiệu ứng: Đặt chiều rộng Paddle trở lại kích thước gốc đã lưu.
     * * @param context Đối tượng GameContext.
     */
    @Override
    public void removeEffect(GameContext context) {
        Paddle paddle = context.getPaddle();

        paddle.setCurrentWidth(paddle.getOriginalWidth());
        System.out.println(paddle.getCurrentWidth());
    }
}


