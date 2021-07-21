package menus;

import interpreter.Direction;
import interpreter.OutputDevice;

import java.awt.*;

public class GameManager implements OutputDevice {
    private static GameManager instance = null;

    private Grid grid;
    private Player player;
    private int goalX;
    private int goalY;

    private GameManager() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        grid = new Grid(screenSize.width, screenSize.height, 30, 30);
        player = new Player(0, 0);
        goalX = 10;
        goalY = 10;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /* Implementation of OutputDevice */

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public void interact() {

    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case UP:
                player.y++;
                break;

            case DOWN:
                player.y--;
                break;

            case LEFT:
                player.x++;
                break;

            case RIGHT:
                player.x--;
                break;
        }
    }
}
