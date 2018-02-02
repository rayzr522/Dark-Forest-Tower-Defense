package me.rayzr522.towerdefense.gui;

import com.sagl.input.Input;
import me.rayzr522.towerdefense.Game;
import me.rayzr522.towerdefense.gui.font.PixelFont;
import me.rayzr522.towerdefense.level.Level;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameMenu extends Menu {

    private Level level;
    private int levelNumber;
    private Game game;

    public GameMenu(Game game, Input input, int id, int width, int height) {
        super(input, id, width, height);
        this.game = game;
        changeLevel(1);

    }

    @Override
    public void input() {
        if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
            level.reset();
            menuhandler.setCurrentMenuId(0);
        } else {
            level.input();
        }
    }

    @Override
    public void update() {
        level.update();
    }

    @Override
    public void render() {
        img.clear();

        Graphics2D g = img.getGraphics();

        PixelFont.drawCenteredString(game.FPS + " FPS   Dark Forest Tower Defense", width, 20, 20, img.getGraphics());

        level.render();
        level.draw(g, width / 2 - level.getWidth() / 2, height / 2 - level.getHeight() / 2);
    }

    private void changeLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        level = new Level("/levels/level" + levelNumber, input, height);
    }

}
