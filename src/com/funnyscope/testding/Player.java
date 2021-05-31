package com.funnyscope.testding;

import java.awt.*;

public class Player extends GameObject {

    public Player(int width, int height, int x, int y, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
    }


    @Override
    public void tick() {
        //Don't know why the values are like this, and I don't care, honestly.
        //Actually, it probably has something to do with the borders of the window.
        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(80, 230, 225));
        g.fillRect(x, y, width, height);
    }

    private void collision() {
        for(int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            //collision code
            if (getBounds().intersects(tempObject.getBounds()) && tempObject.getId() != ID.Player) {
                switch (tempObject.getId()) {
                    case EnemyBullet -> {
                        HUD.health -= 15;
                        handler.removeObject(tempObject);
                    }
                    case Laser -> HUD.health -= 3;
                    default -> HUD.health--;
                }
            }

        }

    }


}
