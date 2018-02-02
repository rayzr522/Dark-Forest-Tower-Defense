package me.rayzr522.towerdefense.level;

public enum Tile {

    EMPTY("", "-1", -1),
    GRASS("/textures/grass.png", "0", 1),
    PATH("/textures/path.png", "1", 0);

    public final String texture;
    public final String id;
    public final int height;

    Tile(String texture, String id, int height) {
        this.texture = texture;
        this.id = id;
        this.height = height;
    }

    public static Tile getByID(String id) {
        for (Tile tile : Tile.values()) {
            if (tile.id.equals(id)) {
                return tile;
            }
        }
        return EMPTY;
    }

}
