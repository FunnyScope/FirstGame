package com.funnyscope.testding;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseListener extends MouseAdapter {

    private final Handler handler;
    private Game game;
    private Menu menu;
    private Random random = new Random();

    public MouseListener(Handler handler, Game game, Menu menu) {
        this.handler = handler;
        this.game = game;
        this.menu = menu;
    }

    //To make the menu work and stuff like that
    public void mouseClicked(MouseEvent e) {
        //Check if the game is stopped, and thus if it has a menu
        if(game.getGameState() == GameStates.Stopped) {
            //the menu states are 1: main menu. 2: controls. 3: credits. 4: Death. 5: Victory
            switch (menu.getMenuState()) {
                case 1 -> {
                    Point clickPos = e.getPoint();
                    if(menu.getPlayButton().contains(clickPos)) {
                        handler.addObject(new Player(30, 30, Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, handler, ID.Player));
                        handler.addObject(new BasicEnemy(20, 20, random.nextInt(Game.WIDTH - 200) + 100, random.nextInt(Game.HEIGHT - 200) + 100, handler, ID.BasicEnemy));
                        game.setGameState(GameStates.Running);
                    }
                    if(menu.getControlButton().contains(clickPos))
                        menu.setMenuState(2);

                    if(menu.getCreditsButton().contains(clickPos))
                        menu.setMenuState(3);
                }
                case 2,3 -> {
                    Point clickPos = e.getPoint();
                    if(menu.getBackButton().contains(clickPos))
                        menu.setMenuState(1);
                }
                case 4,5 -> {
                    menu.setMenuState(1);
                }
            }
        }

    }

}
