package com.funnyscope.testding;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 5892235465921L;

    public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private GameStates gameState = GameStates.Stopped;

    private Handler handler;
    private HUD hud;
    private LevelRegulator spawn;

    private Random random = new Random();

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseListener(handler));
        new Window(WIDTH, HEIGHT, "My First Game!!!1!", this);

        hud = new HUD();
        spawn = new LevelRegulator(handler, hud);

        handler.addObject(new Player(30, 30, WIDTH / 2 - 15, HEIGHT / 2 - 15, handler, ID.Player));
        handler.addObject(new BasicEnemy(20, 20, random.nextInt(WIDTH - 200) + 100, random.nextInt(HEIGHT - 200) + 100, handler, ID.BasicEnemy));
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //Why use an enum when you could use a boolean? Don't know. Doing it anyway.
        while(gameState == GameStates.Running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(gameState == GameStates.Running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawn.tick();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameState = GameStates.Running;
    }
    public synchronized void stop() {
        try {
            thread.join();
            gameState = GameStates.Stopped;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int clamp(int var, int min, int max) {
        if(var < min)
            return min;
        else return Math.min(var, max);
    }
}
