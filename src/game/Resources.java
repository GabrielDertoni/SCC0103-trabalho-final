package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Resources {
    public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<>(10);

    public static void load() {
        try {
            TEXTURES.add(Tile.TileType.FLOOR.getIndex(), ImageIO.read(new File("assets/tile1.png")));
            TEXTURES.add(Tile.TileType.WALL.getIndex(), ImageIO.read(new File("assets/wall.png")));
            TEXTURES.add(Tile.TileType.OBJECTIVE.getIndex(), ImageIO.read(new File("assets/objective.png")));
            TEXTURES.add(Tile.TileType.PLAYER_DOWN.getIndex(), ImageIO.read(new File("assets/frontRight.png")));
            TEXTURES.add(Tile.TileType.PLAYER_RIGHT.getIndex(), ImageIO.read(new File("assets/frontRight.png")));
            TEXTURES.add(Tile.TileType.PLAYER_LEFT.getIndex(), ImageIO.read(new File("assets/frontRight.png")));
            TEXTURES.add(Tile.TileType.PLAYER_UP.getIndex(), ImageIO.read(new File("assets/backRight.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
