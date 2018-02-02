package me.rayzr522.towerdefense.level;

import com.sagl.control.Command;
import com.sagl.graphics.TexLoader;
import com.sagl.graphics.Texture;
import com.sagl.input.Input;
import com.sagl.java2d.location.LocationI;
import me.rayzr522.towerdefense.Constants;
import me.rayzr522.towerdefense.entity.Monster;
import me.rayzr522.towerdefense.entity.Tower;
import me.rayzr522.towerdefense.entity.TowerBlueprints;
import me.rayzr522.towerdefense.gui.IconButton;
import me.rayzr522.towerdefense.gui.PixelButton;
import me.rayzr522.towerdefense.gui.font.PixelFont;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level {

    private static int TILE_SIZE = Constants.TILE_SIZE;

    private Input input;
    private TowerBlueprints towerBlueprints;

    private int time;

    private String location;
    private StringBuffer[] levelFile;
    private StringBuffer[] pathFile;
    private StringBuffer[] wavesFile;

    private int width;
    private int height;

    private int toolbarHeight;

    private int windowHeight;
    private int offsetY;

    private int mapwidth;
    private int mapheight;

    private LocationI start;
    private LocationI finish;
    private int startDirection;

    private Texture img;
    private Texture levelimg;

    private Texture heartIcon;
    private Texture coinIcon;
    private Texture waveIcon;
    private Texture killsIcon;

    private int gameSpeed;

    private int health;
    private int coins;
    private int monsterAmount;
    private int waveAmount;
    private int wave;

    private ArrayList<Monster> monsters;
    private ArrayList<Tower> towers;

    private PixelButton toggleSpeedButton;
    private IconButton deleteTower;
    private IconButton createTower;

    private Tower towerInHand;
    private Tower selectedTower;

    public Level(String location, Input input, int windowHeight) {

        this.input = input;

        this.location = location;

        levelFile = LevelLoader.load(location + ".level");
        pathFile = LevelLoader.load(location + ".path");
        wavesFile = LevelLoader.load(location + ".waves");

        mapwidth = getInt(levelFile, 0);
        mapheight = getInt(levelFile, 1);

        startDirection = getInt(pathFile, 0);

        toolbarHeight = 3 * Constants.TILE_SIZE;

        width = mapwidth * Constants.TILE_SIZE;
        height = mapheight * Constants.TILE_SIZE + toolbarHeight;

        this.windowHeight = windowHeight;
        this.offsetY = windowHeight / 2 - height / 2;

        img = new Texture(width, height, Texture.TYPE_INT_ARGB);
        levelimg = new Texture(width, height, Texture.TYPE_INT_ARGB);

        heartIcon = TexLoader.loadTextureRes("/textures/heart.png");
        coinIcon = TexLoader.loadTextureRes("/textures/coin.png");
        waveIcon = TexLoader.loadTextureRes("/textures/wave.png");
        killsIcon = TexLoader.loadTextureRes("/textures/kills.png");

        toggleSpeedButton = new PixelButton(4, mapheight * Constants.TILE_SIZE + 52, 0, windowHeight / 2 - height / 2, 32, 32, " ", input, Color.black, Color.gray, new Command() {

            @Override
            public void code() {

                toggleSpeedButton.acknowledge();
                toggleSpeed();

            }

        });

        deleteTower = new IconButton(36, mapheight * Constants.TILE_SIZE + 52, 0, windowHeight / 2 - height / 2, 32, 32, TexLoader.loadTextureRes("/textures/trash.png"), input, Color.black,
                Color.gray, new Command() {

            @Override
            public void code() {

                deleteTower.acknowledge();
                grabTower(null);

            }

        });

        createTower = new IconButton(68, mapheight * Constants.TILE_SIZE + 52, 0, windowHeight / 2 - height / 2, 32, 32, TexLoader.loadTextureRes("/textures/towers/tower.png"), input, Color.black,
                Color.gray, new Command() {

            @Override
            public void code() {

                createTower.acknowledge();
                if (coins >= 10) {

                    grabTower(getTower(0, 0));
                }

            }

        });

        getInfo();

        init();

        renderStatic();

    }

    public Tower getTower(int x, int y) {

        return new Tower(null, x, y, 32, 32, 10, 10, 4, 60, "tower");

    }

    private void init() {

        health = getInt(wavesFile, 0);
        coins = getInt(wavesFile, 1);
        monsterAmount = getInt(wavesFile, 2);

        gameSpeed = 2;

        wave = 1;

        toggleSpeed();

        initMonsters();
        initTowers();

    }

    private void initMonsters() {

        monsters = new ArrayList<Monster>();

        for (int i = 0; i < monsterAmount; i++) {

            addMonster(new Monster(this, start.getX() - i * 2 - 1, start.getY(), 32, 32, 20 + wave, 20 + wave, 5 + (int) (0.5 * wave), 2.6 + 0.1 * wave, 2 + wave, startDirection, "goblin"));

        }

    }

    private void initTowers() {

        selectedTower = null;
        towerInHand = null;

        towers = new ArrayList<Tower>();

        // addTower(new Tower(this, 150, 120, 32, 32, 20, 10, 4, 60, "tower"));

    }

    public void reset() {

        init();

    }

    public void grabTower(Tower tower) {

        if (tower != null) {
            // this.towerInHand = new Tower(this, 150, 120, 32, 32, 20, 10, 4,
            // 60, "tower");
            this.towerInHand = tower;
            this.towerInHand.setLevel(this);
            this.towerInHand.setX(input.getMouseX());
            this.towerInHand.setY(input.getMouseY());
        } else {

            this.towerInHand = null;
            if (this.selectedTower != null) {

                coins += (int) (selectedTower.getCost() * 0.9);
                removeTower(selectedTower);
                selectedTower = null;

            }

        }

    }

    public void selectTower() {

        Tower selectedTowerOld = selectedTower;

        for (Tower tower : towers) {

            if (selectedTower != tower) {

                if (tower.checkBounds(input.getMouseX(), input.getMouseY() - offsetY)) {

                    selectedTower = tower;

                    break;

                }

            }

        }

        if (selectedTowerOld == selectedTower) {

            selectedTower = null;

        }

    }

    public void toggleSpeed() {

        gameSpeed++;

        if (gameSpeed > 2) {

            gameSpeed = 0;

        }

        switch (gameSpeed) {

            case (0):

                toggleSpeedButton.setText("||");

                break;

            case (1):

                toggleSpeedButton.setText(">");

                break;

            case (2):

                toggleSpeedButton.setText(">>");

                break;

        }

    }

    public void getInfo() {

        for (int y = 0; y < mapheight; y++) {

            for (int x = 0; x < mapwidth; x++) {

                int number = Integer.parseInt(pathFile[y + 2].substring(x, x + 1).toString());

                if (number == Constants.START) {

                    start = new LocationI(x, y);

                }

                if (number == Constants.FINISH) {

                    finish = new LocationI(x, y);

                }

            }

        }

    }

    public void input() {

        toggleSpeedButton.input();
        deleteTower.input();
        if (input.isKeyDown(KeyEvent.VK_X) && !input.isKeyAcknowledged(KeyEvent.VK_X)) {

            input.acknowledgeKey(KeyEvent.VK_X);
            grabTower(null);

        }

        if (towerInHand != null) {

            towerInHand.setX(input.getMouseX());
            towerInHand.setY(input.getMouseY() - offsetY);

        }

        if (input.LEFT_MOUSE && !input.isMouseAcknowledged(Input.LEFT_MOUSE_BUTTON)) {

            input.acknowledgeMouse(Input.LEFT_MOUSE_BUTTON);

            if (towerInHand == null) {

                selectTower();

            } else {

                int towerTileX = towerInHand.getX() / Constants.TILE_SIZE;
                int towerTileY = towerInHand.getY() / Constants.TILE_SIZE;

                int height = Tile.getByID(String.valueOf(getInt(levelFile, towerTileX, towerTileY + 2))).height;

                if (height > 0) {

                    addTower(towerInHand);

                    coins -= towerInHand.getCost();

                    grabTower(getTower(input.getMouseX(), input.getMouseY() - offsetY));

                }

            }

        }

        createTower.input();

    }

    public void update() {

        if (coins < 10) {

            createTower.setHoverColor(Color.red);

        } else {

            createTower.setHoverColor(Color.gray);

        }

        for (int i = 0; i < gameSpeed; i++) {

            _update();

        }

    }

    private void _update() {

        time++;

        if (monsters.size() == 0) {

            // TODO: Wave system
            wave++;
            initMonsters();
            gameSpeed = 2;
            toggleSpeed();
            return;

        }

        for (Monster monster : monsters) {

            monster.update();

        }

        for (Tower tower : towers) {

            tower.update();

        }

    }

    public void renderStatic() {

        Graphics2D g = levelimg.getGraphics();

        try {

            for (int y = 0; y < mapheight; y++) {

                for (int x = 0; x < mapwidth; x++) {

                    Tile tile = Tile.getByID(levelFile[y + 2].substring(x, x + 1));
                    Texture texture = TexLoader.loadTextureRes(tile.texture);
                    g.drawImage(texture, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                    // g.drawRect(x * 32, y * 32, 32, 32);

                }

            }

            g.setColor(Color.DARK_GRAY);

            g.fillRect(0, mapheight * Constants.TILE_SIZE, mapwidth * Constants.TILE_SIZE, 3 * Constants.TILE_SIZE);

        } catch (Exception e) {

            System.err.println("Couldn't render level! Error:\n");
            e.printStackTrace();

        }

    }

    public void render() {
        Graphics2D g = img.getGraphics();

        g.drawImage(levelimg, 0, 0, width, height, null);

        for (Monster monster : monsters) {
            monster.draw(g);
        }

        for (Tower tower : towers) {
            tower.draw(g, gameSpeed);
        }

        g.drawImage(heartIcon, 4, mapheight * Constants.TILE_SIZE + 4, 8, 8, null);
        PixelFont.drawString(String.valueOf(health), 16, mapheight * Constants.TILE_SIZE + 4, 8, g);
        g.drawImage(coinIcon, 4, mapheight * Constants.TILE_SIZE + 16, 8, 8, null);
        PixelFont.drawString(String.valueOf(coins), 16, mapheight * Constants.TILE_SIZE + 16, 8, g);
        g.drawImage(waveIcon, 4, mapheight * Constants.TILE_SIZE + 28, 8, 8, null);
        PixelFont.drawString("wave " + wave, 16, mapheight * Constants.TILE_SIZE + 28, 8, g);

        toggleSpeedButton.render();
        toggleSpeedButton.draw(g);

        deleteTower.render();
        deleteTower.draw(g);

        createTower.render();
        createTower.draw(g);

        if (selectedTower != null) {
            g.drawImage(killsIcon, 4, mapheight * Constants.TILE_SIZE + 40, 8, 8, null);
            PixelFont.drawString(selectedTower.getKills() + (selectedTower.getKills() == 1 ? " kill" : " kills"), 16, mapheight * Constants.TILE_SIZE + 40, 8, g);

            g.setColor(new Color(150, 150, 150, 100));
            g.fillOval(selectedTower.getX() - selectedTower.getRange(), selectedTower.getY() - selectedTower.getRange(), selectedTower.getRange() * 2, selectedTower.getRange() * 2);
        }

        if (towerInHand != null) {
            towerInHand.draw(g, gameSpeed);

            g.setColor(new Color(150, 150, 150, 100));
            g.fillOval(towerInHand.getX() - towerInHand.getRange(), towerInHand.getY() - towerInHand.getRange(), towerInHand.getRange() * 2, towerInHand.getRange() * 2);
        }

    }

    public void draw(Graphics2D g, int x, int y) {

        g.drawImage(img, x, y, width, height, null);

    }

    public Tile getTileRelative(int x, int y, int side) {

        Tile tile = null;

        try {

            if (side == Constants.NORTH) {

                if (y > 0) {

                    tile = Tile.getByID(levelFile[y + 1].substring(x, x + 1));

                }

            } else if (side == Constants.SOUTH) {

                if (y < mapheight) {

                    tile = Tile.getByID(levelFile[y + 3].substring(x, x + 1));

                }

            } else if (side == Constants.WEST) {

                if (x > 0) {

                    tile = Tile.getByID(levelFile[y + 2].substring(x - 1, x));

                }

            } else if (side == Constants.EAST) {

                if (x < mapwidth) {

                    tile = Tile.getByID(levelFile[y + 2].substring(x + 1, x + 2));

                }

            }

        } catch (Exception e) {

        }

        return tile;

    }

    public Texture blend(Tile t1, Tile t2, int side) {

        return blend(TexLoader.loadTextureRes(t1.texture), TexLoader.loadTextureRes(t1.texture), side);

    }

    public Texture blend(Texture t1, Texture t2, int side) {

        Texture t1s = null;
        Texture t2s = null;
        Texture t3s = null;

        if (side == Constants.NORTH) {

            t1s = t1.getSubimage(0, 0, t1.getWidth(), 1);
            t2s = t2.getSubimage(0, 0, t2.getWidth(), 1);
            t3s = new Texture(t1.getWidth(), 1, Texture.TYPE_INT_RGB);

        } else if (side == Constants.SOUTH) {

            t1s = t1.getSubimage(0, t1.getHeight() - 1, t1.getWidth(), 1);
            t2s = t2.getSubimage(0, t2.getHeight() - 1, t2.getWidth(), 1);
            t3s = new Texture(t1.getWidth(), 1, Texture.TYPE_INT_RGB);

        } else if (side == Constants.WEST) {

            t1s = t1.getSubimage(0, 0, 1, t1.getHeight());
            t2s = t2.getSubimage(0, 0, 1, t2.getHeight());
            t3s = new Texture(1, t1.getHeight(), Texture.TYPE_INT_RGB);

        } else if (side == Constants.EAST) {

            t1s = t1.getSubimage(t1.getWidth() - 1, 0, 1, t1.getHeight());
            t2s = t2.getSubimage(t2.getWidth() - 1, 0, 1, t2.getHeight());
            t3s = new Texture(1, t1.getHeight(), Texture.TYPE_INT_RGB);

        }

        Graphics2D g = t3s.getGraphics();

        if (side == Constants.NORTH || side == Constants.SOUTH) {

            for (int i = 0; i < t3s.getWidth(); i++) {

                Color c1 = new Color(t1s.getPixel(i, 0));
                Color c2 = new Color(t2s.getPixel(i, 0));
                Color c3 = new Color(c1.getRed() / 2 + c2.getRed() / 2, c1.getGreen() / 2 + c2.getGreen() / 2, c1.getBlue() / 2 + c2.getBlue() / 2);

                g.setColor(c3);
                g.fillRect(i, 0, 1, 1);

            }

        } else if (side == Constants.WEST || side == Constants.EAST) {

            for (int i = 0; i < t3s.getHeight(); i++) {

                Color c1 = new Color(t1s.getPixel(0, i));
                Color c2 = new Color(t2s.getPixel(0, i));
                Color c3 = new Color(c1.getRed() / 2 + c2.getRed() / 2, c1.getGreen() / 2 + c2.getGreen() / 2, c1.getBlue() / 2 + c2.getBlue() / 2);

                g.setColor(c3);
                g.fillRect(0, i, 1, 1);

            }

        }

        return t3s;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StringBuffer[] getContent() {
        return levelFile;
    }

    public StringBuffer[] getPath() {
        return pathFile;
    }

    public StringBuffer[] getWaves() {
        return wavesFile;
    }

    public void setLevelFile(StringBuffer[] content) {
        this.levelFile = levelFile;
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

    public int getMapwidth() {
        return mapwidth;
    }

    public void setMapwidth(int mapwidth) {
        this.mapwidth = mapwidth;
    }

    public int getMapheight() {
        return mapheight;
    }

    public void setMapheight(int mapheight) {
        this.mapheight = mapheight;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public int getInt(StringBuffer[] array, int pos) {

        try {

            return Integer.parseInt(array[pos].toString());

        } catch (Exception e) {

            System.err.println("Couldn't get int!");

            return 0;

        }

    }

    public int getInt(StringBuffer[] array, int x, int y) {

        try {

            // System.out.println(array[y].substring(x, x + 1) + " " +
            // array[y].substring(x, x + 1).toString() + " " +
            // Integer.parseInt(array[y].substring(x, x + 1).toString()));

            return Integer.parseInt(array[y].substring(x, x + 1).toString());

        } catch (Exception e) {

            return -1;

        }

    }

    public int getInt(String string) {

        try {

            return Integer.parseInt(string);

        } catch (Exception e) {

            System.err.println("Couldn't get int!");

            return 0;

        }

    }

    public void damage(int amount) {

        health -= amount;

    }

    public LocationI getStart() {

        return start;

    }

    public LocationI getFinish() {

        return finish;

    }

    public void addMonster(Monster monster) {

        monsters.add(monster);

    }

    public void removeMonster(Monster monster) {

        if (monsters.contains(monster)) {

            monsters.remove(monster);

        }

    }

    public void addTower(Tower tower) {

        tower.setId(towers.size());

        tower.setPlaced(true);

        towers.add(tower);

    }

    public void removeTower(Tower tower) {

        if (towers.contains(tower)) {

            towers.remove(tower);

        }

    }

    public ArrayList<Monster> getMonsters() {

        return monsters;

    }

    public void rewardCoins(int amount) {

        coins += amount;

    }

}
