package com.game.arkanoid.model;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import java.awt.Color;

public class FastBallPowerUp extends PowerUp {
    private double speedMultiplier;

    public FastBallPowerUp(double startX, double startY) {
        super(startX, startY, 32, 16, 200);
        this.color = Color.RED;
        this.speedMultiplier = 1.5;
    }

    @Override
    public void apply(Ball ball, Paddle paddle) {
        if (!used) {
            ball.setSpeed(ball.getSpeed() * speedMultiplier);
            used = true;
            deactive();
        }
    }
}
