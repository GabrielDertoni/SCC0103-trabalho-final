package game;

import java.awt.*;

public class Levels {

    public static Level getLevelTest() {
        Player player = new Player(1, 3);

        Compositor compositor = new Compositor();
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0},
                }, false)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {2, 2, 2, 2, 2},
                        {2, 0, 0, 0, 2},
                        {2, 0, 0, 0, 2},
                        {2, 0, 0, 0, 2},
                        {2, 2, 2, 2, 2},
                }, true)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 3, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                }, false)
        );
        return new Level(new Point(3, 1), new Point(1, 3), compositor);
    }
    
    public static Level getLevel1() {
        Player player = new Player(4, 4);

        Compositor compositor = new Compositor();
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 1, 0},
                        {0, 1, 1, 1, 1, 0},
                        {0, 0, 0, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0}
                }, false)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {2, 2, 2, 2, 2, 2},
                        {2, 0, 0, 0, 0, 2},
                        {2, 2, 2, 2, 0, 2},
                        {2, 0, 0, 0, 0, 2},
                        {2, 2, 2, 0, 0, 2},
                        {2, 2, 2, 2, 2, 2}
                }, true)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0}
                }, false)
        );
        return new Level(new Point(3, 1), new Point(4, 4), compositor);
    }

    public static Level getLevel2() {
        Player player = new Player(1, 4);

        Compositor compositor = new Compositor();
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 1, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0},
                        {0, 1, 1, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                }, false)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {2, 2, 2, 2, 2, 2, 2},
                        {2, 0, 2, 0, 0, 0, 2},
                        {2, 0, 2, 2, 2, 0, 2},
                        {2, 0, 0, 0, 2, 0, 2},
                        {2, 0, 2, 0, 2, 0, 2},
                        {2, 0, 2, 0, 0, 0, 2},
                        {2, 2, 2, 2, 2, 2, 2}
                }, true)
        );
        compositor.pushLayer(
                new TileMap(new byte[][]{
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                }, false)
        );
        return new Level(new Point(5, 1), new Point(1, 4), compositor);
    }
}
