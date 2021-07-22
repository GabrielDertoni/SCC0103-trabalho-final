package game;

import java.awt.*;

public class TileMap implements Compositor.Layer {
    private Tile[][] tiles;
    private boolean hasCollider;

    public TileMap(byte[][] tilesData, boolean hasCollider) {
        this.hasCollider = hasCollider;
        tiles = new Tile[tilesData.length][tilesData[0].length];

        for (int i = 0; i < tilesData.length; i++) {
            for (int j = 0; j < tilesData[i].length; j++) {
                if (tilesData[i][j] == 0) {
                    tiles[i][j] = null;
                } else {
                    tiles[i][j] = new Tile(i, j, Tile.TileType.fromIndex(tilesData[i][j]));
                }
            }
        }
    }

    public boolean collidesWith(Rectangle other) {
        if (!hasCollider) return false;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null && tiles[i][j].intersects(other)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(Graphics graphics) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null) {
                    tiles[i][j].render(graphics);
                }
            }
        }
    }
}
