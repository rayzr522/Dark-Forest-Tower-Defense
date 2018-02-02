package me.rayzr522.towerdefense.gui;

import com.sagl.input.Input;
import me.rayzr522.towerdefense.gui.font.PixelFont;
import me.rayzr522.towerdefense.level.Level;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestMenu extends Menu {

    Level level;

    public TestMenu(Input input, int id, int width, int height) {
        super(input, id, width, height);

    }

    @Override
    public void input() {

        if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {

            menuhandler.setCurrentMenuId(0);

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        img.clear();

        Graphics2D g = img.getGraphics();

        PixelFont.drawString("test area for my game", 30, 20, 24, img.getGraphics());

        // level.draw(g);

    }

}
