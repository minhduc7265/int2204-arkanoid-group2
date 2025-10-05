package com.game.arkanoid.model;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.awt.Color;

public class ExpandPaddlePowerUp extends PowerUp {
    private double expandAmount;

    public ExpandPaddlePowerUp(double startX, double startY) {
        super(startX, startY, 32, 16, 200);
        this.color = Color.GREEN;
        this.expandAmount = 40;
    }

    @Override
    public void apply(Ball ball, Paddle paddle) {
        if (!used) {
            paddle.setWidth(paddle.getWidth() + expandAmount);
            used = true;
            deactive();
        }
    }
}

