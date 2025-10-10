package com.game.arkanoid.output;

public class Score {
    private int score;


    public Score(int score) {
        this.score = score;
    }

    public void addScore(int i) {
        this.score += i;
    }

    public void setScore(int score) {
        if (score < 0) {
            System.err.println("Score can't be negative.");
            return;
        }
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
