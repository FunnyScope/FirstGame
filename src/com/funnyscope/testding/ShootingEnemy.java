package com.funnyscope.testding;

import java.awt.*;

public class ShootingEnemy extends GameObject {
    private final boolean xZero;
    private boolean ammunition = false;
    private int reloading = 0;

    public ShootingEnemy(int width, int height, int x, int y, boolean xZero, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        velX = 0;
        velY = 10;
        this.xZero = xZero;
    }


    private void shoot() {

        if (xZero) {
            handler.addObject(new EnemyBullet(10, 10, x + 10, y + 10, 10, handler, ID.EnemyBullet));
        } else {
            handler.addObject(new EnemyBullet(10, 10, x + 10, y + 10, -10, handler, ID.EnemyBullet));
        }

    }

    @Override
    public void tick() {
        reloading++;
        if (reloading == 15) {
            reloading = 0;
            ammunition = true;
        }

        if (y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            velY *= -1;

        for (GameObject tempObject : handler.gameObjects) {
            if (tempObject.getId() == ID.Player && tempObject.getBounds().intersects(new Rectangle(tempObject.getX(), y, width, height)) && ammunition) {
                shoot();
                ammunition = false;
                break;
            }
        }

        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(75, 83, 32));
        g.fillRect(x, y, width, height);
    }
}
