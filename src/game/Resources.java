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
            TEXTURES.add(Tile.TileType.WALL.getIndex(), ImageIO.read(new File("assets/wallTemp.png")));
            TEXTURES.add(Tile.TileType.OBJECTIVE.getIndex(), ImageIO.read(new File("assets/objective.png")));
            TEXTURES.add(Tile.TileType.PLAYER.getIndex(), ImageIO.read(new File("assets/frontRight.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
