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
}
