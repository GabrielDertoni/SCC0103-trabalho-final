package game;

import java.awt.*;

public class Level {
    public Point goalPosition;
    public Point startPosition;
    public Compositor compositor;

    public Level(Point goalPosition, Point startPosition, Compositor compositor) {
        this.goalPosition = goalPosition;
        this.startPosition = startPosition;
        this.compositor = compositor;
    }

    public void render(Graphics graphics) {
        compositor.render(graphics);
    }
    public Compositor.Layer collidesWith(Rectangle other) {
        return compositor.collidesWith(other);
    }
}
