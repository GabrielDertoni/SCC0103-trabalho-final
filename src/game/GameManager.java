package game;

import interpreter.Direction;
import interpreter.Interpreter;
import interpreter.OutputDevice;
import interpreter.Stmt;
import menus.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameManager implements OutputDevice {
    private Interpreter interpreter;
    private boolean isRunning;
    private Level level;
    private int level_num;

    public GameManager() {
        interpreter = null;
        isRunning = false;

        Resources.load();

        level = Levels.getLevelTest();
        System.out.println(level.compositor.collidesWith(level.player));
    }

    public void loadLevel(int level) {
        level_num = level;
        if (level == 0) {
            this.level = Levels.getLevelTest();
        } else {
            JOptionPane.showConfirmDialog(WindowManager.getInstance(), "Jogo concluído");
            WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu);
        }
    }

    public void interpret(List<Stmt> stmts) {
        interpreter = new Interpreter(stmts, this);
    }

    public void render(Graphics graphics) {
        level.render(graphics);
    }

    public void loop() {
        if (isRunning && interpreter != null && interpreter.isNotFinished()) {
            interpreter.advance();
        }
    }

    /* Implementation of OutputDevice */

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public void interact() {
        if (level.player.x == level.goalX && level.player.y == level.goalY) {
            JOptionPane.showConfirmDialog(WindowManager.getInstance(), "Nível vencido!");
            loadLevel(level_num + 1);
        }
    }

    @Override
    public void move(Direction direction) {
        // Tries to move in one direction, if it collides with a tilemap, move back.
        level.player.move(direction);
        Compositor.Layer collision = level.compositor.collidesWith(level.player);
        if (collision instanceof TileMap) {
            level.player.move(direction.getOpposite());
        }
    }
}
