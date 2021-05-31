package com.funnyscope.testding;

import java.awt.*;

public class Laser extends GameObject {
    private int beamRemaining;
    public Laser(int width, int height, int x, int y, int beamRemaining, Handler handler, ID id) {
        super(width, height, x, y, handler, id);
        this.beamRemaining = beamRemaining;
    }

    @Override
    public void tick() {
        height++;
        beamRemaining--;
        if(beamRemaining == 0)
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.fillRect(x + 10, y, width - 20, height);
    }
}
