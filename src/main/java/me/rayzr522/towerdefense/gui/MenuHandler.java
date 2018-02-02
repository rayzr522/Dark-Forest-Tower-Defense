package me.rayzr522.towerdefense.gui;

import java.awt.*;
import java.util.ArrayList;

public class MenuHandler {

    ArrayList<Menu> menus;

    int currentMenuId;

    public MenuHandler() {

        menus = new ArrayList<Menu>();

    }

    public Menu getMenuById(int id) {

        for (Menu menu : menus) {

            if (menu.getId() == id) {

                return menu;

            }

        }

        return null;

    }

    public void switchMenu(int newMenuId) {

    }

    public int getCurrentMenuId() {

        return currentMenuId;

    }

    public void setCurrentMenuId(int currentMenuId) {

        this.currentMenuId = currentMenuId;

    }

    public void addMenu(Menu menu) {

        menu.setMenuHandler(this);
        menus.add(menu);

    }

    public void removeMenuById(int id) {

        for (int i = 0; i < menus.size(); i++) {

            if (menus.get(i).getId() == id) {

                menus.remove(i);
                return;

            }

        }

        System.err.println("Coudln't remove menu!");

    }

    public void input() {

        getMenuById(currentMenuId).input();

    }

    public void update() {

        getMenuById(currentMenuId).update();

    }

    public void render() {

        getMenuById(currentMenuId).render();

    }

    public void draw(Graphics2D g) {

        getMenuById(currentMenuId).draw(g);

    }

}
