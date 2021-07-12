package com.funnyscope.testding;

import java.awt.*;

public abstract class GameObject {

    protected int width, height, x, y, velX, velY;
    protected ID id;
    protected Handler handler;


    public GameObject(int width, int height, int x, int y, Handler handler, ID id) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public ID getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}
