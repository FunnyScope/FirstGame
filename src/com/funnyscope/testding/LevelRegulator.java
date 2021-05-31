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
                    for(int i = 0; i < handler.gameObjects.size(); i++) {
                        GameObject tempObject = handler.gameObjects.get(i);
                        if(tempObject.getId() != ID.Player)
                            handler.removeObject(tempObject);
                    }
                    FirstBoss firstBoss = new FirstBoss(30, 30, random.nextInt(Game.WIDTH - 200) + 100, 200, handler, ID.FirstBoss);
                    handler.addObject(firstBoss);
                    firstBoss.setStage(1);
                    for(int i = 0; i < 5; i++)
                        handler.addObject(new BasicEnemy(20, 20, random.nextInt(Game.WIDTH - 200) + 100, (i + 1) * 150, handler, ID.BasicEnemy));
                }
                case 11 -> {
                    while(handler.gameObjects.size() > 2) {
                        for (int i = 0; i < handler.gameObjects.size(); i++) {
                            GameObject tempObject = handler.gameObjects.get(i);
                            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy)
                                handler.removeObject(tempObject);
                        }
                    }
                    for(int i = 0; i < 3; i++)
                        handler.addObject(new FastEnemy(30, 30, 0, i * 200, 14, 1, handler, ID.FastEnemy));

                    for (int i = 0; i < handler.gameObjects.size(); i++) {
                        GameObject tempObject = handler.gameObjects.get(i);
                        if(tempObject.getId() == ID.FirstBoss) {
                            FirstBoss firstBoss = (FirstBoss) tempObject;
                            firstBoss.setStage(firstBoss.getStage() + 1);
                            break;
                        }
                    }
                }
                case 12 -> {
                    handler.addObject(new CrushingEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, 0, handler, ID.CrushingEnemy));

                    for (int i = 0; i < handler.gameObjects.size(); i++) {
                        GameObject tempObject = handler.gameObjects.get(i);
                        if(tempObject.getId() == ID.FirstBoss) {
                            FirstBoss firstBoss = (FirstBoss) tempObject;
                            firstBoss.setStage(firstBoss.getStage() + 1);
                            break;
                        }
                    }
                }
                case 13 -> {
                    /*
                    Why the while-loop?
                    Because it doesn't purge everything otherwise, and I'm not going to find out why.
                    It'd take too much effort.
                     */
                    while(handler.gameObjects.size() > 1) {
                        for (int i = 0; i < handler.gameObjects.size(); i++) {
                            GameObject tempObject = handler.gameObjects.get(i);
                            if (tempObject.getId() != ID.Player)
                                handler.removeObject(tempObject);
                        }
                    }
                    handler.addObject(new FastEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, random.nextInt(Game.HEIGHT - 200) + 100, 4, 11, handler, ID.FastEnemy));
                }
                case 14 -> handler.addObject(new ShootingEnemy(30, 30, 0, 0, true, handler, ID.ShootingEnemy));
                case 15 -> handler.addObject(new ShootingEnemy(30, 30, Game.WIDTH - 45, Game.HEIGHT - 45, false, handler, ID.ShootingEnemy));
                case 16 -> handler.addObject(new LaserEnemy(30, 30, random.nextInt(Game.WIDTH - 200) + 100, 0, handler, ID.LaserEnemy));

            }
        }
    }

}