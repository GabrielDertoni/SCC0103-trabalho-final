package game;

public class Levels {

    public static Level getLevelTest() {
        Player player = new Player(1, 2);

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
                }, true)
        );
        compositor.pushLayer(player);

        return new Level(3, 1, compositor, player);
    }
}
