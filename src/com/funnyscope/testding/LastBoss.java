package com.funnyscope.testding;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.*;

public class LastBoss extends GameObject {

    private final Font font = new Font("Arial", Font.BOLD, 20);
    private final Random random = new Random();
    private BufferedImage img = null;

    private int stage = 0;

    public LastBoss(int x, int y, Handler handler, ID id) {
        super(30, 30, x, y, handler, id);
        velX = 5;
        velY = -5;
        try {
            img = ImageIO.read(new File("src/resources/FirstGame_LastBoss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        if (x <= 0 || x >= Game.WIDTH - (int) (width * 1.5))
            velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - height * 2 - 9)
            velY *= -1;

        x = Game.clamp(x + velX, 0, Game.WIDTH - (int) (width * 1.5));
        y = Game.clamp(y + velY, 0, Game.HEIGHT - height * 2 - 9);
    }

    @Override
    public void render(Graphics g) {
        if (img != null) {
            g.drawImage(img, x, y, null);
        } else {
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);
        }
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("Stage: " + stage, Game.WIDTH - 100, 15);
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
