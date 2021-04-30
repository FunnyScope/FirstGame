package com.funnyscope.testding;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        for(boolean i : keyDown)
            i = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        for(GameObject tempObject : handler.gameObjects) {
            if(tempObject.getId() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_W -> {
                        tempObject.setVelY(-5);
                        keyDown[0] = true;
                    }
                    case KeyEvent.VK_S -> {
                        tempObject.setVelY(5);
                        keyDown[1] = true;
                    }
                    case KeyEvent.VK_A -> {
                        tempObject.setVelX(-5);
                        keyDown[2] = true;
                    }
                    case KeyEvent.VK_D -> {
                        tempObject.setVelX(5);
                        keyDown[3] = true;
                    }
                }
                break;
            }
        }
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        /*
        If you're wondering for why I'm not just putting it in a variable:
        If I do that I might mess up the code somewhere.
        And I really don't want to fix it.
        */
        for(GameObject tempObject : handler.gameObjects) {
            if(tempObject.getId() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_W -> keyDown[0] = false;
                    case KeyEvent.VK_S -> keyDown[1] = false;
                    case KeyEvent.VK_A -> keyDown[2] = false;
                    case KeyEvent.VK_D -> keyDown[3] = false;
                }
                //Vertical movement
                if(!keyDown[0] && !keyDown[1])
                    tempObject.setVelY(0);
                //Horizontal movement
                if(!keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0);
                break;
            }
        }


    }

}
