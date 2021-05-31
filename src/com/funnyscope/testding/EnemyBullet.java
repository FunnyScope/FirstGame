package com.funnyscope.testding;

import java.awt.*;

public class EnemyBullet extends GameObject {

    public EnemyBullet(int width, int height, int x, int y, int velX, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        this.velX = velX;
        velY = 0;
    }

    @Override
    public void tick() {
        if(x <= 0 || x >= Game.WIDTH - (int) (width * 1.5))
            handler.removeObject(this);
        if(y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            handler.removeObject(this);

        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, height);
    }
}
