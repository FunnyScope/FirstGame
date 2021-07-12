package com.funnyscope.testding;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 5892235465921L;

    public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private GameStates gameState = GameStates.Stopped;
    private boolean running = false;

    private final Handler handler;
    private final HUD hud;
    private final LevelRegulator spawn;
    private final Menu menu;

    private final Random random = new Random();

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        menu = new Menu();
        this.addMouseListener(new MouseListener(handler, this, menu));

        hud = new HUD();
        spawn = new LevelRegulator(handler, hud, menu, this);


        new Window(WIDTH, HEIGHT, "My First Game!!!1!", this);
        if (gameState == GameStates.Running) {
            handler.addObject(new Player(30, 30, WIDTH / 2 - 15, HEIGHT / 2 - 15, handler, ID.Player));
            handler.addObject(new BasicEnemy(20, 20, random.nextInt(WIDTH - 200) + 100, random.nextInt(HEIGHT - 200) + 100, handler, ID.BasicEnemy));
        }
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
        try {
            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running)
                    render();
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    //System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong lmao fix it");
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        if (gameState == GameStates.Running)
            hud.render(g);
        else
            menu.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        if (gameState == GameStates.Running) {
            hud.tick();
            spawn.tick();
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        //gameState = GameStates.Running;
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            //gameState = GameStates.Stopped;
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int clamp(int var, int min, int max) {
        if (var < min)
            return min;
        else return Math.min(var, max);
    }

    //Because I was stupid enough to make most variables into integers, I now have to make a separate double clamp
    //because I'm too lazy to typecast everything. Even typing this out takes less effort then that.
    public static double doubleClamp(double var, double min, double max) {
        if (var < min)
            return min;
        else return Math.min(var, max);
    }

    public GameStates getGameState() {
        return gameState;
    }
    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }
}
