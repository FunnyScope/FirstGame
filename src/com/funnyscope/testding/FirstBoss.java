package com.funnyscope.testding;

import java.awt.*;
import java.util.Random;


public class FirstBoss extends GameObject {

    private Font font = new Font("Arial", Font.BOLD, 20);
    private Random random = new Random();

    private int stage = 0;

    public FirstBoss(int width, int height, int x, int y, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        if (x <= 0 || x >= Game.WIDTH - (int) (width * 1.5))
            velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            velY *= -1;

        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, width, height);
        g.setFont(font);
        g.drawString("Stage " + stage, Game.WIDTH - 100, 15);
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }


}
