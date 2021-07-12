package com.funnyscope.testding;

import java.awt.*;
import java.awt.geom.*;

public class VectorEnemy extends GameObject {
    private Vector2D vector2D;
    private int countdown = 0;

    public VectorEnemy(int width, int height, int x, int y, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        vector2D = new Vector2D(x, y, 0, 0, 0);
    }

    @Override
    public void tick() {
        if (vector2D.getLength() <= 0 && countdown == 0) {
            for (GameObject tempObject : handler.gameObjects) {
                if (tempObject.getId() == ID.Player) {
                    double deltaX = tempObject.getX() - vector2D.getX();
                    double deltaY = tempObject.getY() - vector2D.getY();
                    double totalDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                    double requiredTime = totalDistance / 10;
                    double horizontalSpeed = deltaX / requiredTime;
                    double verticalSpeed = deltaY / requiredTime;
                    countdown = 60;
                    vector2D = new Vector2D(vector2D.getX(), vector2D.getY(), horizontalSpeed, verticalSpeed, totalDistance);
                }
            }
        } else if (vector2D.getLength() <= 0) {
            vector2D = new Vector2D(vector2D.getX(), vector2D.getY(), 0, 0, 0);
            countdown--;
        }

        vector2D = vector2D.getNewVector();
        vector2D.setX(Game.doubleClamp(vector2D.getX(), 0, Game.WIDTH - width * 1.5));
        vector2D.setY(Game.doubleClamp(vector2D.getY(), 0, Game.HEIGHT - height * 1.5));
        x = (int) vector2D.getX();
        y = (int) vector2D.getY();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 30, 40, 215));
        Rectangle2D rect = new Rectangle2D.Double(vector2D.getX(), vector2D.getY(), width, height);
        g2d.fill(rect);
    }
}
