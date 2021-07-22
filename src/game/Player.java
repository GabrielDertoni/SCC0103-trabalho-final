package game;

public class Player extends Tile implements Compositor.Layer {
    public Player(int x, int y) {
        super(x, y, TileType.PLAYER);
    }
}
