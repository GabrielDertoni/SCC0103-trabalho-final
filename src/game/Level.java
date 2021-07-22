package game;

import java.awt.*;

public class Level {
    public Player player;
    public int goalX;
    public int goalY;

    private Compositor compositor;

    public Level(int goalX, int goalY, Compositor compositor, Player player) {
        this.goalX = goalX;
        this.goalY = goalY;
        this.compositor = compositor;
        this.player = player;
    }

    public void render(Graphics graphics) {
        compositor.render(graphics);
    }
}
