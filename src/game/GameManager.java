package game;

import interpreter.Direction;
import interpreter.Interpreter;
import interpreter.OutputDevice;
import interpreter.Stmt;
import menus.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameManager implements OutputDevice {
    private Interpreter interpreter;
    private boolean isRunning;
    private Timer gameLoopTimer;
    private Level level;

    public GameManager() {
        interpreter = null;
        isRunning = false;

        Resources.load();
        gameLoopTimer = new Timer(20, new GameLoop());

        level = Levels.getLevelTest();
    }

    public void loadLevel(int level) {
        if (level == 0) {
            this.level = Levels.getLevelTest();
        } else {
            throw new IllegalArgumentException("not a valid level: " + level);
        }
    }

    public void interpret(Stmt toInterpret) {
        interpreter = new Interpreter(Arrays.asList(toInterpret), this);
    }

    public void render(Graphics graphics) {
        this.level.render(graphics);
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
        }
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case UP    -> level.player.y++;
            case DOWN  -> level.player.y--;
            case LEFT  -> level.player.x++;
            case RIGHT -> level.player.x--;
        }
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (isRunning && interpreter != null && interpreter.isNotFinished()) {
                interpreter.advance();
            }
        }
    }
}
