package me.rayzr522.towerdefense.gui;

import com.sagl.input.Input;
import me.rayzr522.towerdefense.gui.font.PixelFont;

public class IntermissionMenu extends Menu {

    private String message;
    private int fontSize;

    public IntermissionMenu(Input input, int id, int width, int height, String message) {
        super(input, id, width, height);

        this.message = message;

        fontSize = (int) (width / message.length() * 0.9);

    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        PixelFont.drawCenteredString(message, width, height / 2 - fontSize / 2, fontSize, img.getGraphics());

    }

}
