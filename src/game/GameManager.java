package game;

import interpreter.Direction;
import interpreter.Interpreter;
import interpreter.OutputDevice;
import interpreter.Stmt;
import menus.MyOptionPane;
import menus.WindowManager;

import java.awt.*;
import java.util.List;

public class GameManager implements OutputDevice {
    private Interpreter interpreter;
    private boolean isRunning;
    private Level level;
    private Player player;
    private int level_num;

    public GameManager(int lvl) {
        interpreter = null;
        isRunning = false;

        Resources.load();
        loadLevel(lvl);
    }

    public void loadLevel(int level) {
        level_num = level;
        if (level == 0) {
            this.level = Levels.getLevelTest();
        } else if (level == 1) {
            this.level = Levels.getLevel1();
        } else if (level == 2) {
            this.level = Levels.getLevel2();
        } else {
            new MyOptionPane("Jogo concluido!", 350, 200);
            WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu);
        }
        player = new Player(this.level.startPosition);
        this.level.compositor.pushLayer(player);
    }

    public void interpret(List<Stmt> stmts) {
        isRunning = true;
        player.resetPosition();
        interpreter = new Interpreter(stmts, this);
    }

    private void updateInterpreterVariables() {
        Rectangle right = new Rectangle(player.x + Tile.SIZE, player.y, Tile.SIZE, Tile.SIZE);
        Rectangle left  = new Rectangle(player.x - Tile.SIZE, player.y, Tile.SIZE, Tile.SIZE);
        Rectangle up    = new Rectangle(player.x, player.y + Tile.SIZE, Tile.SIZE, Tile.SIZE);
        Rectangle down  = new Rectangle(player.x, player.y - Tile.SIZE, Tile.SIZE, Tile.SIZE);

        System.out.println("direita: " + (level.compositor.collidesWith(right) != null));
        System.out.println("esquerda: " + (level.compositor.collidesWith(left) != null));
        System.out.println("cima: " + (level.compositor.collidesWith(up) != null));
        System.out.println("baixo: " + (level.compositor.collidesWith(down) != null));

        interpreter.setVariable(
                "a Direita",
                level.compositor.collidesWith(right) != null ?
                    "uma parede" :
                    "nada"
        );

        interpreter.setVariable(
                "a Esquerda",
                level.compositor.collidesWith(left) != null ?
                        "uma parede" :
                        "nada"
        );

        interpreter.setVariable(
                "Cima",
                level.compositor.collidesWith(up) != null ?
                        "uma parede" :
                        "nada"
        );

        interpreter.setVariable(
                "Baixo",
                level.compositor.collidesWith(down) != null ?
                        "uma parede" :
                        "nada"
        );
    }

    public void render(Graphics graphics) {
        level.render(graphics);
    }

    public void loop() {
        if (isRunning && interpreter != null && interpreter.isNotFinished()) {
            updateInterpreterVariables();
            interpreter.advance();
        } else if (interpreter != null && !interpreter.isNotFinished()) {
            isRunning = false;
        }
    }

    /* Implementation of OutputDevice */

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public void interact() {
        System.out.println("INTERACT");
        if (player.x == level.goalPosition.x * Tile.SIZE && player.y == level.goalPosition.y * Tile.SIZE) {
            MyOptionPane myoptp = new MyOptionPane("Nivel Vencido!", 350, 200);
            loadLevel(level_num + 1);
        }
    }

    @Override
    public void move(Direction direction) {
        // Tries to move in one direction, if it collides with a tilemap, move back.
        player.move(direction);
        Compositor.Layer collision = level.compositor.collidesWith(player);
        if (collision instanceof TileMap) {
            System.out.println("COLLISION when trying to move " + direction);
            player.move(direction.getOpposite());
        }
    }
}
