package game;

import interpreter.Direction;

import java.awt.*;

public class Player extends Tile implements Compositor.Layer {
    Point originalPosition;

    public Player(Point position) {
        super(position.x, position.y, TileType.PLAYER_DOWN);
        originalPosition = position;
    }

    public Player(int x, int y) {
        this(new Point(x, y));
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP    -> y -= SIZE;
            case DOWN  -> y += SIZE;
            case LEFT  -> x -= SIZE;
            case RIGHT -> x += SIZE;
        }
    }

    public void resetPosition() {
        setGridPosition(originalPosition);
    }
}
