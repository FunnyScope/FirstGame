package com.funnyscope.testding;

import java.awt.*;

public class FastEnemy extends GameObject {

    public FastEnemy(int width, int height, int x, int y, int velX, int velY, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        //In hopes of being able to make it harder to stay in one place.
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick() {

        if(x <= 0 || x >= Game.WIDTH - (int) (width * 1.5))
            velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            velY *= -1;

        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(10, 40, 200));
        g.fillRect(x, y, width, height);
    }
}
