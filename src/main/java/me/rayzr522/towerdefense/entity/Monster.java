package me.rayzr522.towerdefense.entity;

import com.sagl.graphics.TexLoader;
import com.sagl.graphics.Texture;
import me.rayzr522.towerdefense.Constants;
import me.rayzr522.towerdefense.level.Level;

import java.awt.*;

public class Monster extends Entity {

    private double maxHealth;
    private double health;
    private int money;
    private int damage;

    private double speed;
    private double dy;
    private double dx;

    private int direction;

    private boolean alive;

    private int width;
    private int height;
    private Texture img;
    private Level level;

    public Monster(Level level, int x, int y, int maxHealth, int health, int money, double speed, int damage, int direction, String texture) {
        super(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, 0, 0);

        img = TexLoader.loadTextureRes("/textures/monsters/" + texture + ".png");
        this.level = level;

        this.width = Constants.TILE_SIZE;
        this.height = Constants.TILE_SIZE;

        box.setWidth(width);
        box.setHeight(height);

        this.maxHealth = maxHealth;
        // this.health = maxHealth;
        this.health = health;
        this.money = money;
        this.speed = speed;
        this.damage = damage;

        setDirection(direction);

        alive = true;

    }

    public Monster(Level level, int x, int y, int width, int height, int maxHealth, int health, int money, double speed, int damage, int direction, String texture) {
        super(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, width, height);

        img = TexLoader.loadTextureRes("/textures/monsters/" + texture + ".png", Texture.TYPE_INT_ARGB);
        this.level = level;

        this.width = width;
        this.height = height;

        this.maxHealth = maxHealth;
        // this.health = maxHealth;
        this.health = health;
        this.money = money;
        this.speed = speed;
        this.damage = damage;

        setDirection(direction);

        alive = true;

    }

    public void update() {

        if (!alive) {

            return;

        }

        for (int i = 0; i < Math.abs(dx / 0.1); i++) {

            if (dx > 0) {

                box.setX(box.getX() + 0.1);

            }

            if (dx < 0) {

                box.setX(box.getX() - 0.1);

            }

            // box.setX(box.getX() + ((dx > 0) ? 0.1 : -0.1));

            checkDirectionChange();

        }

        for (int i = 0; i < Math.abs(dy / 0.1); i++) {

            if (dy > 0) {

                box.setY(box.getY() + 0.1);

            }

            if (dy < 0) {

                box.setY(box.getY() - 0.1);

            }

            // box.setY(box.getY() + ((dy > 0) ? 0.1 : -0.1));

            checkDirectionChange();

        }

        // box.setX(box.getX() + dx);
        // box.setY(box.getY() + dy);
        //
        // checkDirectionChange();

        if (getTileX() == level.getFinish().getX() && getTileY() == level.getFinish().getY()) {

            alive = false;
            level.damage(damage);

        }

    }

    public void draw(Graphics2D g) {

        if (!alive) {

            return;

        }

        g.setColor(Color.black);

        g.drawRect((int) getX() + (width / 2 - Constants.HP_BAR_WIDTH / 2) - 1, (int) (getY() - ((Constants.HP_BAR_HEIGHT) * 1.5)) - 1, Constants.HP_BAR_WIDTH + 1, Constants.HP_BAR_HEIGHT + 1);

        g.setColor(Color.red);

        g.fillRect((int) getX() + (width / 2 - Constants.HP_BAR_WIDTH / 2), (int) (getY() - (Constants.HP_BAR_HEIGHT * 1.5)), Constants.HP_BAR_WIDTH, Constants.HP_BAR_HEIGHT);

        g.setColor(Color.green);

        g.fillRect((int) getX() + (width / 2 - Constants.HP_BAR_WIDTH / 2), (int) (getY() - (Constants.HP_BAR_HEIGHT * 1.5)), (int) ((Constants.HP_BAR_WIDTH) * (health / maxHealth)), Constants.HP_BAR_HEIGHT);

        g.drawImage(img, (int) box.getX(), (int) box.getY(), width, height, null);

    }

    public double getX() {

        return box.getX();

    }

    public double getY() {

        return box.getY();

    }

    public int getWidth() {

        return width;

    }

    public int getTileX() {

        if (direction == Constants.NORTH || direction == Constants.WEST) {

            return (int) Math.floor((getX() + width - 0.1) / Constants.TILE_SIZE);

        } else {

            return (int) Math.floor(getX() / Constants.TILE_SIZE);

        }

    }

    public int getTileY() {

        if (direction == Constants.NORTH || direction == Constants.WEST) {

            return (int) Math.floor((getY() + height - 0.1) / Constants.TILE_SIZE);

        } else {

            return (int) Math.floor(getY() / Constants.TILE_SIZE);

        }

    }

    public int getHeight() {

        return height;

    }

    public void setDirection(int direction) {

        this.direction = direction;

        switch (direction) {
            case Constants.NORTH:

                dx = 0.0;
                dy = -speed;

                break;

            case Constants.SOUTH:

                dx = 0.0;
                dy = speed;

                break;

            case Constants.WEST:

                dx = -speed;
                dy = 0.0;

                break;

            case Constants.EAST:

                dx = speed;
                dy = 0.0;

                break;

            default:

                direction = Constants.NORTH;

                dx = 0.0;
                dy = -speed;

                break;
        }

    }

    public void checkDirectionChange() {

        int tileX = getTileX();
        int tileY = getTileY();

        int directionChange = level.getInt(level.getPath(), tileX, tileY + 2);

        if (directionChange == Constants.NORTH || directionChange == Constants.SOUTH || directionChange == Constants.WEST || directionChange == Constants.EAST) {

            setDirection(directionChange);

        }

    }

    public boolean damage(int amount) {

        this.health -= amount;

        if (health <= 0) {

            level.rewardCoins(money);
            level.removeMonster(this);

            return true;

        }

        return false;

    }

    public Monster cloneMonster() {

        try {
            return (Monster) clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();

            return null;
        }

    }

}
