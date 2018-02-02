package me.rayzr522.towerdefense.gui;

import com.sagl.graphics.Texture;
import com.sagl.input.Input;

import java.awt.*;

public abstract class Menu {

    protected Input input;
    protected MenuHandler menuhandler;

    protected int id;
    protected int width;
    protected int height;

    protected Texture img;

    public Menu(Input input, MenuHandler menuhandler, int id, int width, int height) {

        this.input = input;
        this.id = id;
        this.width = width;
        this.height = height;

        img = new Texture(width, height);

    }

    public Menu(Input input, int id, int width, int height) {

        this(input, null, id, width, height);

    }

    public abstract void input();

    public abstract void update();

    public abstract void render();

    public void draw(Graphics2D g) {

        g.drawImage(img, 0, 0, width, height, null);

        g.dispose();

    }

    public MenuHandler getMenuHandler() {

        return menuhandler;

    }

    public void setMenuHandler(MenuHandler menuhandler) {

        this.menuhandler = menuhandler;

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
