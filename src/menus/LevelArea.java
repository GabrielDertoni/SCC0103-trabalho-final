package menus;

import game.GameManager;

import javax.swing.*;
import java.awt.*;

public class LevelArea extends Background {
    private GameManager gameManager;

    public LevelArea(int x, int y, int width, int height) {
        super(new ImageIcon("assets/background.png").getImage());

        setLayout(null);
        setBounds(x, y, width, height);

        gameManager = new GameManager();
        // gameManager.loadLevel(lvl);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        gameManager.render(graphics);
    }
}
