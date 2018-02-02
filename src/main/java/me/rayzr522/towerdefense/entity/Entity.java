package me.rayzr522.towerdefense.entity;

import com.sagl.java2d.entity.BoxD;

public abstract class Entity {

    protected BoxD box;

    public Entity(double x, double y, double width, double height) {
        box = new BoxD(x, y, width, height);
    }

}
