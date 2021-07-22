package game;

import java.awt.*;

public class Tile extends Rectangle {

    public enum TileType {
        EMPTY(0), FLOOR(1), WALL(2), OBJECTIVE(3), PLAYER(4);

        public static TileType fromIndex(int index) {
            switch (index) {
                case 0: return EMPTY;
                case 1: return FLOOR;
                case 2: return WALL;
                case 3: return OBJECTIVE;
                case 4: return PLAYER;
                default: throw new IllegalArgumentException("Expected index to be 0, 1 or 2");
            }
        }

        private int index;

        TileType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index - 1;
        }
    }

    public static final int SIZE = 100;
    private TileType type;

    public Tile(int x, int y, TileType type) {
        super(x * SIZE, y * SIZE, SIZE, SIZE);
        this.type = type;
    }

    public Image getImage() {
        if (this.type == TileType.EMPTY) return null;
        return Resources.TEXTURES.get(this.type.getIndex());
    }

    public int getGridX() {
        return x / SIZE;
    }

    public int getGridY() {
        return y / SIZE;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(getImage(), x, y, width, height, null);
    }
}
