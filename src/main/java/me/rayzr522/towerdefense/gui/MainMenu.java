package me.rayzr522.towerdefense.gui;

import com.sagl.control.Command;
import com.sagl.input.Input;
import me.rayzr522.towerdefense.gui.font.PixelFont;

import java.awt.*;

public class MainMenu extends Menu {

    private Command startButtonCommand;
    private PixelButton startButton;

    private Command testButtonCommand;
    private PixelButton testButton;

    private Command exitButtonCommand;
    private PixelButton exitButton;

    private int buttonWidth = 600;
    private int buttonHeight = 75;

    public MainMenu(Input input, int id, int width, int height) {
        super(input, id, width, height);

        startButtonCommand = new Command() {

            @Override
            public void code() {

                startGame();

            }

        };

        testButtonCommand = new Command() {

            @Override
            public void code() {

                gotoTestArea();

            }

        };

        exitButtonCommand = new Command() {

            @Override
            public void code() {

                System.exit(0);

            }

        };

        startButton = new PixelButton(width / 2 - buttonWidth / 2, 300, buttonWidth, buttonHeight, "start game", input, Color.DARK_GRAY, Color.gray, startButtonCommand);

        testButton = new PixelButton(width / 2 - buttonWidth / 2, 400, buttonWidth, buttonHeight, "test area", input, Color.DARK_GRAY, Color.gray, testButtonCommand);

        exitButton = new PixelButton(width / 2 - buttonWidth / 2, 500, buttonWidth, buttonHeight, "exit", input, Color.DARK_GRAY, Color.gray, exitButtonCommand);

    }

    public void gotoTestArea() {

        menuhandler.setCurrentMenuId(10);

    }

    public void startGame() {

        menuhandler.setCurrentMenuId(1);

    }

    @Override
    public void input() {

        startButton.input();
        testButton.input();
        exitButton.input();

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        Graphics2D g = img.getGraphics();

        startButton.render();
        testButton.render();
        exitButton.render();

        startButton.draw(g);
        testButton.draw(g);
        exitButton.draw(g);

        PixelFont.drawCenteredString("dark forest tower defense", width, 40, 30, g);

        PixelFont.drawCenteredString("a work in progress tower defense game", width, 90, 8, g);

    }

}
