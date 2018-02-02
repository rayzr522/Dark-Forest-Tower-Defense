package me.rayzr522.towerdefense.gui;

import com.sagl.control.Command;
import com.sagl.graphics.Texture;
import com.sagl.input.Input;
import me.rayzr522.towerdefense.gui.font.PixelFont;

import java.awt.*;

public class PixelButton {

    private int x;
    private int y;
    private int offsetX;
    private int offsetY;
    private int xa;
    private int ya;
    private int width;
    private int height;
    private String text;
    private Input input;
    private Color color;
    private Color hoverColor;
    private boolean hovering;
    private boolean acknowledged;
    private Command command;

    private int fontSize;

    private Texture img;

    public PixelButton(int x, int y, int width, int height, String text, Input input, Color color, Color hoverColor, Command command) {

        this(x, y, 0, 0, width, height, text, input, color, hoverColor, command);

    }

    public PixelButton(int x, int y, int width, int height, int fontSize, String text, Input input, Color color, Color hoverColor, Command command) {

        this(x, y, 0, 0, width, height, fontSize, text, input, color, hoverColor, command);

    }

    public PixelButton(int x, int y, int offsetX, int offsetY, int width, int height, String text, Input input, Color color, Color hoverColor, Command command) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.input = input;
        this.color = color;
        this.hoverColor = hoverColor;
        this.command = command;

        calculateFontSize();

        img = new Texture(width, height);

        xa = x + offsetX;
        ya = y + offsetY;

        render();

    }

    public PixelButton(int x, int y, int offsetX, int offsetY, int width, int height, int fontSize, String text, Input input, Color color, Color hoverColor, Command command) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.text = text;
        this.input = input;
        this.color = color;
        this.hoverColor = hoverColor;
        this.command = command;

        calculateFontSize();

        img = new Texture(width, height);

        xa = x + offsetX;
        ya = y + offsetY;

        render();

    }

    private void calculateFontSize() {

        fontSize = width / text.length() - 5;

        if (fontSize > height - 5) {

            fontSize = height - 5;

        }

    }

    public void input() {

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        if (input.LEFT_MOUSE && (mouseX >= xa && mouseX <= xa + width && mouseY >= ya && mouseY <= ya + height)) {

            execute();

        } else {

            acknowledged = false;

        }

        if (mouseX >= xa && mouseX <= xa + width && mouseY >= ya && mouseY <= ya + height) {

            hovering = true;

        } else {

            hovering = false;

        }

    }

    public void execute() {

        if (!acknowledged) {
            command.execute();
        }

    }

    public void render() {

        Graphics2D g = img.getGraphics();

        g.setColor(hovering ? hoverColor : color);
        g.fillRect(0, 0, width, height);

        PixelFont.drawCenteredString(text, width, height / 2 - fontSize / 2 + offsetY, fontSize, g);

    }

    public void draw(Graphics2D g) {

        g.drawImage(img, x, y, width, height, null);

    }

    public void setText(String text) {

        this.text = text;

        calculateFontSize();

    }

    public void acknowledge() {

        acknowledged = true;

    }

    public boolean isAcknowledged() {

        return acknowledged;

    }

    public void setColor(Color color) {

        this.color = color;

    }

    public void setHoverColor(Color hoverColor) {

        this.hoverColor = hoverColor;

    }

}
