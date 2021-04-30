package com.funnyscope.testding;

import java.awt.*;

public class CrushingEnemy extends GameObject {

    private boolean abovePlayer = false;

    public CrushingEnemy(int width, int height, int x, int y, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
    }

    @Override
    public void tick() {

        if(y != 0 && !abovePlayer) {
            velX = 0;
            velY = -10;
        } else if(!abovePlayer) {
            for(GameObject tempObject : handler.gameObjects) {
                if(tempObject.getId() == ID.Player) {
                    if(tempObject.getBounds().intersects(new Rectangle(x, tempObject.getY(), width, height))) {
                        abovePlayer = true;
                        velX = 0;
                        velY = 10;
                    } else if(tempObject.getX() > x) {
                        velX = 10;
                        velY = 0;
                    } else {
                        velX = -10;
                        velY = 0;
                    }
                    break;
                }
            }
        }

        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);
        if(y + height * 2 + 9 == Game.HEIGHT)
            abovePlayer = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x, y, width, height);
    }
}
