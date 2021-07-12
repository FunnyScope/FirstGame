package com.funnyscope.testding;

import java.awt.*;

public class HUD {

    private final Font font = new Font("Arial", Font.PLAIN, 10);

    public static int health = 200;

    private int score = 0, level = 1;


    public void tick() {
        health = Game.clamp(health, 0, 200);
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 30);
        g.setColor(Color.green);
        g.fillRect(15, 15, health, 30);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 30);

        g.setFont(font);
        g.drawString("Score: " + score, 15, 60);
        g.drawString("Level: " + level, 15, 75);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
