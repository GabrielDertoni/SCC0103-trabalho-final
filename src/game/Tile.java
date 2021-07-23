package game;

import java.awt.*;

public class Tile extends Rectangle {

    public enum TileType {
        EMPTY(0), FLOOR(1), WALL(2), OBJECTIVE(3),
        PLAYER_DOWN(4), PLAYER_RIGHT(5), PLAYER_LEFT(6), PLAYER_UP(7);

        public static TileType fromIndex(int index) {
            switch (index) {
                case 0: return EMPTY;
                case 1: return FLOOR;
                case 2: return WALL;
                case 3: return OBJECTIVE;
                case 4: return PLAYER_DOWN;
                case 5: return PLAYER_RIGHT;
                case 6: return PLAYER_LEFT;
                case 7: return PLAYER_UP;
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

    public Point getGridPosition() {
        return new Point(x / SIZE, y / SIZE);
    }

    public void setGridPosition(Point position) {
        x = position.x * SIZE;
        y = position.y * SIZE;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(getImage(), x, y, width, height, null);
    }
}
