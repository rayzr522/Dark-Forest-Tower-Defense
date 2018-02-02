package me.rayzr522.towerdefense.entity;


import me.rayzr522.towerdefense.level.Level;

public class TowerBlueprints {
    public Tower BASIC_TOWER;

    private Level level;

    public TowerBlueprints(Level level) {
        this.level = level;
        initTowers();
    }

    private void initTowers() {
        BASIC_TOWER = new Tower(level, 150, 120, 32, 32, 20, 10, 4, 60, "tower");
    }

}
