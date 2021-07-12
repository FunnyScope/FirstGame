package com.funnyscope.testding;

import java.awt.*;

public class LaserEnemy extends GameObject {
    private int waitTime = 120;

    public LaserEnemy(int width, int height, int x, int y, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        velX = 10;
        velY = 0;
    }

    @Override
    public void tick() {
        waitTime--;
        if (waitTime <= 0) {
            for (GameObject tempObject : handler.gameObjects) {
                if (tempObject.getId() == ID.Player && tempObject.getBounds().intersects(new Rectangle(x, y, width, Game.HEIGHT))) {
                    waitTime = 120;
                    handler.addObject(new Laser(50, 60, x - 10, y, 300, handler, ID.Laser));
                    break;
                }
            }
        }
        if (x <= 0 || x >= Game.WIDTH - (int) (width * 1.5))
            velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            velY *= -1;
        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}
