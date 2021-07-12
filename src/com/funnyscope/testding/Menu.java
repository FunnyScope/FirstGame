package com.funnyscope.testding;

import java.awt.*;

public class Menu {

    private final Font font = new Font("Arial", Font.BOLD, 30);
    private final Font smallFont = new Font("Arial", Font.PLAIN, 15);
    private final Rectangle playButton;
    private final Rectangle controlButton;
    private final Rectangle creditsButton;
    private final Rectangle backButton;
    private int menuState;

    public Menu() {
        playButton = new Rectangle(200, Game.HEIGHT / 5, 200, Game.HEIGHT / 5);
        controlButton = new Rectangle(200, Game.HEIGHT / 5 * 2 + 20, 200, Game.HEIGHT / 5);
        creditsButton = new Rectangle(200, Game.HEIGHT / 5 * 3 + 40, 200, Game.HEIGHT / 5);
        menuState = 1;
        backButton = new Rectangle(150, 300, 200, Game.HEIGHT / 5);
    }

    public void render(Graphics g) {
        //This is quite a sizeable one, so I expect there to be a lot of inefficiency. Ah well
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setFont(font);
        //I know there's something to do your UI with, some interface, but I can't be bothered to look it up
        switch(menuState) {
            case 1 -> {
                //Main menu
                g2d.draw(playButton);
                g2d.draw(controlButton);
                g2d.draw(creditsButton);
                g2d.drawString("Menu", 260, 50);
                g2d.drawString("Play", 270, Game.HEIGHT / 5 + 60);
                g2d.drawString("Controls", 240, Game.HEIGHT / 5 * 2 + 75);
                g2d.drawString("Credits", 250, Game.HEIGHT / 5 * 3 + 95);
            }
            case 2 -> {
                //Controls
                g2d.drawString("Controls", 240, 75);
                g2d.draw(backButton);
                g2d.drawString("Back", 215, 355);
                g2d.setFont(smallFont);
                g2d.drawString("The controls:", 175, 125);
                g2d.drawString("W: Up", 200, 150);
                g2d.drawString("A: Left", 200, 175);
                g2d.drawString("S: Down", 200, 200);
                g2d.drawString("D: Right", 200, 225);
                g2d.drawString("ESC: Close game", 200, 250);
            }
            case 3 -> {
                //Credits, of which there aren't many because I did almost all the work myself, but I'm crediting anyway.
                g2d.drawString("Credits", 250, 75);
                g2d.draw(backButton);
                g2d.drawString("Back", 215, 355);
                g2d.setFont(smallFont);
                g2d.drawString("Game testing: J3ltR", 175, 125);
                g2d.drawString("Rest: FunnyScope (hey that's me!!!1!)", 175, 150);
                g2d.drawString("Honestly, this game isn't anything advanced.", 175, 175);
                g2d.drawString("But I had fun making it and I learned a lot,", 175, 200);
                g2d.drawString("and that's the most important thing, right?", 175, 225);
            }
            case 4 -> {
                g2d.drawString("You have lost! :(", 175, Game.HEIGHT / 5 * 2);
                g2d.setFont(smallFont);
                g2d.drawString("Click anywhere to continue", 200, Game.HEIGHT / 5 * 2 + 50);
            }
            case 5 -> {
                g2d.drawString("VICTORY! or something", 175, Game.HEIGHT / 5 * 2);
                g2d.setFont(smallFont);
                g2d.drawString("Click anywhere to continue", 200, Game.HEIGHT / 5 * 2 + 50);
            }

        }

    }

    public Rectangle getPlayButton() {
        return playButton;
    }

    public Rectangle getControlButton() {
        return controlButton;
    }

    public Rectangle getCreditsButton() {
        return creditsButton;
    }

    public Rectangle getBackButton() {
        return backButton;
    }

    public int getMenuState() {
        return menuState;
    }

    public void setMenuState(int menuState) {
        this.menuState = menuState;
    }
}
