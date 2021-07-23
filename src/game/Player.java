package game;

import interpreter.Direction;

import java.awt.*;

public class Player extends Tile implements Compositor.Layer {
    public Player(Point position) {
        super(position.x, position.y, TileType.PLAYER);
    }

    public Player(int x, int y) {
        super(x, y, TileType.PLAYER);
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP    -> y -= SIZE;
            case DOWN  -> y += SIZE;
            case LEFT  -> x -= SIZE;
            case RIGHT -> x += SIZE;
        }
    }
}
