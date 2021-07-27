package interpreter;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class InterpreterTest {
    private TestOutputDevice outputDevice;
    private Interpreter interpreter;

    private void interpretAll(List<Stmt> stmts) {
        interpreter.interpret(stmts);
        while (interpreter.isNotFinished()) {
            interpreter.advance();
        }
    }

    @Before
    public void setup() {
        outputDevice = new TestOutputDevice(0, 0);
        interpreter = new Interpreter(outputDevice);
    }

    @Test
    public void interpret() {
        List<Stmt> stmts = Arrays.asList(
                new Stmt.VariableDeclaration(
                        "minhaVar",
                        new Expr.Literal(5)
                ),
                new Stmt.StmtExpr(
                        new Expr.Call(
                                "imprimeFormatado",
                                Arrays.asList(
                                        new Expr.Literal("minha variável possui valor %.02f"),
                                        new Expr.Variable("minhaVar")
                                )
                        )
                ),
                new Stmt.VariableAssign(
                        "minhaVar",
                        new Expr.Binary(
                                new Expr.Variable("minhaVar"),
                                Expr.Binary.Operator.PLUS,
                                new Expr.Literal(1)
                        )
                ),
                new Stmt.StmtExpr(
                        new Expr.Call(
                                "imprimeFormatado",
                                Arrays.asList(
                                        new Expr.Literal("minha variável possui valor %.02f"),
                                        new Expr.Variable("minhaVar")
                                )
                        )
                )
        );
        interpretAll(stmts);
        assertEquals("minha variável possui valor 5,00", outputDevice.prints.get(0));
        assertEquals("minha variável possui valor 6,00", outputDevice.prints.get(1));
    }

    private static class TestOutputDevice implements OutputDevice {
        public int posX;
        public int posY;
        public List<String> prints;
        // Pair of [x, y]
        public Set<int[]> interactionPoints;

        public TestOutputDevice(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
            prints = new ArrayList<>();
            interactionPoints = new HashSet<>();
        }

        public boolean hasInteractedAt(int posX, int posY) {
            int[] pair = { posX, posY };
            return interactionPoints.contains(pair);
        }

        @Override
        public void print(String msg) {
            prints.add(msg);
        }

        @Override
        public void interact() {
            interactionPoints.add(new int[] { posX, posY });
        }

        @Override
        public void move(Direction direction) {
            switch (direction) {
                case UP    -> posY--;
                case DOWN  -> posY++;
                case LEFT  -> posX--;
                case RIGHT -> posX++;
            }
        }
    }
}