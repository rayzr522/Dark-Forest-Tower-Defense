package me.rayzr522.towerdefense;

import com.sagl.CanvasGame;
import com.sagl.graphics.TexLoader;
import me.rayzr522.towerdefense.gui.GameMenu;
import me.rayzr522.towerdefense.gui.MainMenu;
import me.rayzr522.towerdefense.gui.MenuHandler;
import me.rayzr522.towerdefense.gui.TestMenu;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @see MenuHandler
 * @see GameMenu
 */

public class Game extends CanvasGame {

    /**
     *
     */
    private static final long serialVersionUID = -2519482097782107061L;

    // public static double size_multiplier = 4.5;

    private MenuHandler menuHandler;

    public static void main(String[] args) {
        final Game game = new Game();

        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setTitle(TITLE);
        frame.setIconImage(TexLoader.loadTextureRes("/textures/icon.png"));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.stop();
                System.exit(0);
            }
        });

        frame.setVisible(true);

        System.out.println("Running...");

        game.start();

    }

    @Override
    public void init() {
        WIDTH = 1024;
        HEIGHT = 768;
        TITLE = "Dark Forest Tower Defense";
        BUFFERS = 3;
        UPS = 20;
        FRAME_COUNTER = false;

        menuHandler = new MenuHandler();

        menuHandler.addMenu(new MainMenu(input, 0, WIDTH, HEIGHT));
        menuHandler.addMenu(new GameMenu(this, input, 1, WIDTH, HEIGHT));
        menuHandler.addMenu(new TestMenu(input, 10, WIDTH, HEIGHT));

        menuHandler.setCurrentMenuId(0);
    }

    @Override
    public void stop() {
    }

    @Override
    public void input() {
        menuHandler.input();
    }

    @Override
    public void update() {
        menuHandler.update();
    }

    @Override
    public void render() {
        img.clear();
        menuHandler.render();
        menuHandler.draw(img.getGraphics());
    }

    @Override
    public void run() {
        super.run();
    }

}
