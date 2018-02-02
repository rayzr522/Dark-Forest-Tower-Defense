package me.rayzr522.towerdefense.entity;

import com.sagl.graphics.TexLoader;
import com.sagl.graphics.Texture;
import com.sagl.java2d.entity.BoxD;
import me.rayzr522.towerdefense.level.Level;

import java.awt.*;

public class Tower {

    private int kills;
    private int id;
    private int time;

    private int damage;
    private int range;
    private int cost;
    private int reloadTime;

    private int x;
    private int y;
    private int width;
    private int height;

    private boolean placed;

    private Texture img;
    private Level level;

    private BoxD box;

    public Tower(Level level, int x, int y, int cost, int reloadTime, int damage, int range, String texture) {
        this(level, x, y, 0, 0, cost, reloadTime, damage, range, texture);
        this.width = img.getWidth();
        this.height = img.getHeight();
        box = new BoxD(x - this.width / 2, y - this.height / 2, this.width, this.height);

    }

    public Tower(Level level, int x, int y, int width, int height, int cost, int reloadTime, int damage, int range, String texture) {

        img = TexLoader.loadTextureRes("/textures/towers/" + texture + ".png");

        this.level = level;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.reloadTime = reloadTime;
        this.damage = damage;
        this.range = range;
        this.cost = cost;

        box = new BoxD(x - this.width / 2, y - this.height / 2, this.width, this.height);

    }

    public void update() {

        time++;

        if (time % reloadTime == 0) {

            fire();

        }

    }

    public void fire() {

        for (Monster monster : level.getMonsters()) {

            if (inRange(monster)) {

                boolean killed = monster.damage(damage);
                if (killed) {

                    kills++;

                }
                return;

            }

        }

    }

    public boolean inRange(Monster monster) {

        int mx = (int) monster.getX() + monster.getWidth() / 2;
        int my = (int) monster.getY() + monster.getHeight() / 2;

        int distx = (int) Math.abs(mx - x);
        int disty = (int) Math.abs(my - y);

        int distance = (int) Math.sqrt(distx * distx + disty * disty);

        return distance <= range;

    }

    public void render() {

    }

    public void draw(Graphics2D g, int gameSpeed) {

        g.drawImage(img, x - width / 2, y - height / 2, width, height, null);

        if (!placed) {

            return;

        }

        for (Monster monster : level.getMonsters()) {

            if (inRange(monster) && gameSpeed > 0) {

                g.setColor(Color.white);
                g.drawLine(this.x, this.y, (int) monster.getX() + monster.getWidth() / 2, (int) monster.getY() + monster.getHeight() / 2);
                // g.fillRect((int) monster.getX(), (int) monster.getY(),
                // monster.getWidth(), monster.getHeight());
                break;

            }

        }

    }

    public boolean checkBounds(int mouseX, int mouseY) {

        return box.checkPoint(mouseX, mouseY);

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        box.setX(x - width / 2);
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        box.setY(y - height / 2);
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        box.setWidth(width);
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        box.setHeight(height);
        this.height = height;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public BoxD getBox() {
        return box;
    }

    public void setBox(BoxD box) {
        this.box = box;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;

    }

    public Tower cloneTower() {

        try {
            return (Tower) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void setPlaced(boolean placed) {

        this.placed = placed;

    }

    public int getKills() {

        return kills;

    }

}
