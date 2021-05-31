package com.funnyscope.testding;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    public void tick() {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        /*
        You'll see that I sometimes use "regular" for-loops in stead of foreach loops. I do this because it avoids
        the ConcurrentModificationException. I try to use foreach loops when possible, but sometimes I can't.
        I should probably use the CopyOnWriteArrayList, and I very well might.
        */
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);
        }
    }

    public synchronized void addObject(GameObject object) {
        gameObjects.add(object);
    }

    public synchronized void removeObject(GameObject object) {
        gameObjects.remove(object);
    }

}
