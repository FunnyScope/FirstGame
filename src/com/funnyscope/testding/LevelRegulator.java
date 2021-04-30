package com.funnyscope.testding;

import java.util.Random;

public class LevelRegulator {

    private Handler handler;
    private HUD hud;
    private Random random = new Random();

    public LevelRegulator(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }


    public void tick() {
        if(hud.getScore() % 500 == 0 && hud.getScore() != 0) {
            hud.setLevel(hud.getLevel() + 1);

            switch(hud.getLevel()) {
                case 2, 3 -> handler.addObject(new BasicEnemy(20, 20, random.nextInt(Game.WIDTH - 200) + 100, random.nextInt(Game.HEIGHT - 200) + 100, handler, ID.BasicEnemy));
                case 4 -> handler.addObject(new FastEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, random.nextInt(Game.HEIGHT - 200) + 100, 11, 4, handler, ID.FastEnemy));
                case 5 -> handler.addObject(new FastEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, random.nextInt(Game.HEIGHT - 200) + 100, 4, 11, handler, ID.FastEnemy));
                case 6 -> handler.addObject(new CrushingEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, 0, handler, ID.CrushingEnemy));
                case 7, 8, 9 -> {
                    for(GameObject tempObject : handler.gameObjects) {
                        if(tempObject.getId() == ID.BasicEnemy) {
                            handler.removeObject(tempObject);
                            break;
                        }
                    }
                }
                case 10 -> {
                    for(GameObject tempObject : handler.gameObjects) {
                        if(tempObject.getId() != ID.Player)
                            handler.removeObject(tempObject);
                    }
                    //todo: Create a first boss with three stages.
                }
            }
        }
    }

}
