package me.rayzr522.towerdefense.gui;

import com.sagl.control.Command;
import com.sagl.graphics.Texture;
import com.sagl.input.Input;

import java.awt.*;

public class IconButton {

    private int x;
    private int y;
    private int offsetX;
    private int offsetY;
    private int xa;
    private int ya;
    private int width;
    private int height;

    private Input input;
    private Color color;
    private Color hoverColor;
    private boolean hovering;
    private boolean acknowledged;
    private Command command;

    private Texture icon;
    private Texture img;

    public IconButton(int x, int y, int width, int height, Texture icon, Input input, Color color, Color hoverColor, Command command) {
        this(x, y, 0, 0, width, height, icon, input, color, hoverColor, command);

    }

    public IconButton(int x, int y, int offsetX, int offsetY, int width, int height, Texture icon, Input input, Color color, Color hoverColor, Command command) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = icon;
        this.input = input;
        this.color = color;
        this.hoverColor = hoverColor;
        this.command = command;

        img = new Texture(width, height);

        xa = x + offsetX;
        ya = y + offsetY;

        render();

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

        g.drawImage(icon, width / 2 - icon.getWidth() / 2, height / 2 - icon.getHeight() / 2, icon.getWidth(), icon.getHeight(), null);

    }

    public void draw(Graphics2D g) {

        g.drawImage(img, x, y, width, height, null);

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
