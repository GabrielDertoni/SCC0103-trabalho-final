package menus;

import interpreter.Direction;
import interpreter.Interpreter;
import interpreter.OutputDevice;

import javax.swing.*;
import java.awt.*;

public class GameManager implements OutputDevice {
    private static GameManager instance = null;

    private Interpreter interpreter;
    private Player player;
    private int goalX;
    private int goalY;

    private GameManager() {
        player = new Player(0, 0);
        goalX = 10;
        goalY = 10;
        interpreter = new Interpreter(this);
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
        if (player.x == goalX && player.y == goalY) {
            JOptionPane.showConfirmDialog(WindowManager.getInstance(), "Nível vencido!");
        }
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
