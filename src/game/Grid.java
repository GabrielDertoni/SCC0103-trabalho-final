package menus;

import menus.Background;

import javax.swing.*;
import java.awt.*;

public class Grid extends Background {
    private int width;
    private int height;
    private int cellWidth;
    private int cellHeight;

    public Grid(int width, int height, int cellWidth, int cellHeight) {
        super(new ImageIcon("specification/Prototipo_fundo_menu.jpg").getImage());
        this.width = width;
        this.height = height;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        setBounds(0, 0, width, height);
    }
}
