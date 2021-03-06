package menus;

import game.GameManager;
import interpreter.Stmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LevelArea extends Background {
    private GameManager gameManager;
    private Timer gameLoopTimer;

    public LevelArea(int x, int y, int width, int height, int lvl) {
        super(new ImageIcon("assets/background.png").getImage());

        setLayout(null);
        setBounds(x, y, width, height);

        gameManager = new GameManager(lvl);
        gameLoopTimer = new Timer(500, new GameLoop());
        gameLoopTimer.start();
    }

    public void runInterpreter(List<Stmt> stmts) {
        gameManager.interpret(stmts);
    }

    public int getLevelNum() {
        return gameManager.getLevelNum();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        gameManager.render(graphics);
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            gameManager.loop();
            repaint();
        }
    }
}
