package com.funnyscope.testding;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    public void tick() {
        for(GameObject tempObject : gameObjects)
            tempObject.tick();
    }

    public void render(Graphics g) {
        for(GameObject tempObject : gameObjects)
            tempObject.render(g);
    }

    public synchronized void addObject(GameObject object) {
        gameObjects.add(object);
    }

    public synchronized void removeObject(GameObject object) {
        gameObjects.remove(object);
    }

}
