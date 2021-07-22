package menus;

import game.GameManager;
import interpreter.Direction;
import interpreter.PseudocodeGenerator;
import interpreter.Stmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class LevelArea extends Background {
    private GameManager gameManager;
    private Timer gameLoopTimer;

    public LevelArea(int x, int y, int width, int height) {
        super(new ImageIcon("assets/background.png").getImage());

        setLayout(null);
        setBounds(x, y, width, height);

        gameManager = new GameManager();
        // gameManager.loadLevel(lvl);
        gameLoopTimer = new Timer(500, new GameLoop());
        gameLoopTimer.start();
    }

    public void runInterpreter(List<Stmt> stmts) {
        /*
        List<Stmt> stmts2 = Arrays.asList(
            new Stmt.Move(Direction.RIGHT),
            new Stmt.Move(Direction.RIGHT),
            new Stmt.Move(Direction.UP),
            new Stmt.Move(Direction.UP),
            new Stmt.Interact()
        );

        PseudocodeGenerator gen = new PseudocodeGenerator();
        */
        gameManager.interpret(stmts);
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
