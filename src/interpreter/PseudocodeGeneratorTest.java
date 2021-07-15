package interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PseudocodeGeneratorTest {
    private PseudocodeGenerator generator;

    @Before
    public void setup() {
        generator = new PseudocodeGenerator();
    }

    @After
    public void teardown() {
        generator = null;
    }

    @Test
    public void testFromStmts() {
        List<Stmt> stmts = new ArrayList<Stmt>(){{
           add(new Stmt.If(new Expr.Literal(true),
                   new Stmt.StmtExpr(
                           new Expr.Call("imprime", Arrays.asList(
                                   new Expr.Literal("Hello, World!")
                           ))),
                   null));
        }};

        String expected = "se verdadeiro \n" +
                          "    imprime(\"Hello, World!\");\n";

        assertEquals(expected, generator.fromStmts(stmts));
    }

    @Test
    public void testFromExpr() {
        Expr expr = new Expr.Binary(
                new Expr.Literal(2),
                Expr.Binary.Operator.PLUS,
                new Expr.Binary(
                        new Expr.Literal(2),
                        Expr.Binary.Operator.MULT,
                        new Expr.Literal(3)
                )
        );

        String expected = "2 + 2 * 3";
        assertEquals(expected, generator.fromExpr(expr));
    }

    @Test
    public void testFromExprWithPrecedence() {
        Expr expr = new Expr.Binary(
                new Expr.Literal(3),
                Expr.Binary.Operator.MULT,
                new Expr.Binary(
                        new Expr.Literal(2),
                        Expr.Binary.Operator.PLUS,
                        new Expr.Literal(2)
                )
        );

        String expected = "3 * (2 + 2)";
        assertEquals(expected, generator.fromExpr(expr));
    }

    @Test
    public void testVisitIfStmt() {
        Stmt.If stmt = new Stmt.If(
                new Expr.Binary(
                        new Expr.Literal(2),
                        Expr.Binary.Operator.LESS,
                        new Expr.Literal(3)
                ),
                new Stmt.Block(Arrays.asList(
                        new Stmt.VariableDeclaration(
                                "minhaVar",
                                new Expr.Literal("hello")
                        )
                )),
                new Stmt.StmtExpr(
                        new Expr.Call(
                                "imprime",
                                Arrays.asList(
                                        new Expr.Literal("Olá"),
                                        new Expr.Literal("Mundo")
                                )
                        )
                )
        );

        String expected = "se 2 < 3 {\n" +
                          "    var minhaVar = \"hello\";\n" +
                          "} se não \n" +
                          "    imprime(\"Olá\", \"Mundo\");\n";

        assertEquals(expected, generator.visitIfStmt(stmt));
    }

    @Test
    public void testVisitInteractStmt() {
        Stmt.Interact stmt = new Stmt.Interact();
        assertEquals("interage();", generator.visitInteractStmt(stmt));
    }

    @Test
    public void testVisitMoveStmt() {
        Stmt.Move stmt = new Stmt.Move(Stmt.Move.Direction.DOWN);
        assertEquals("movePara baixo;", generator.visitMoveStmt(stmt));
    }

    @Test
    public void testVisitLoopStmt() {
        Stmt.Loop stmt = new Stmt.Loop(
                new Expr.Call(
                        "verifica",
                        Arrays.asList(
                                new Expr.Variable("variável")
                        )
                ),
                new Stmt.StmtExpr(
                        new Expr.Call(
                                "imprime",
                                Arrays.asList(
                                        new Expr.Variable("variável")
                                )
                        )
                )
        );

        String expected = "enquanto verifica(variável) \n" +
                          "    imprime(variável);\n";

        assertEquals(expected, generator.visitLoopStmt(stmt));
    }

    @Test
    public void testVisitBlockStmt() {
        Stmt.Block stmt = new Stmt.Block(Arrays.asList(
           new Stmt.Move(Stmt.Move.Direction.UP)
        ));

        String expected = "{\n" +
                          "    movePara cima;\n" +
                          "}";

        assertEquals(expected, generator.visitBlockStmt(stmt));
    }

    @Test
    public void testVisitVariableDeclarationStmt() {
        Stmt.VariableDeclaration stmt = new Stmt.VariableDeclaration(
                "minhaVariável",
                new Expr.Call("acessaValor", Arrays.asList())
        );

        assertEquals("var minhaVariável = acessaValor();", generator.visitVariableDeclarationStmt(stmt));
    }

    @Test
    public void testVisitVariableAssignStmt() {
        Stmt.VariableAssign stmt = new Stmt.VariableAssign(
                "minhaVariável",
                new Expr.Call("acessaValor", Arrays.asList())
        );

        assertEquals("minhaVariável = acessaValor();", generator.visitVariableAssignStmt(stmt));
    }

    @Test
    public void testVisitBinaryExpr() {
        Expr.Binary expr = new Expr.Binary(
                new Expr.Call("leValor", Arrays.asList()),
                Expr.Binary.Operator.PLUS,
                new Expr.Literal(1)
        );

        assertEquals("leValor() + 1", generator.visitBinaryExpr(expr));
    }

    @Test
    public void testVisitCallExpr() {
        Expr.Call expr = new Expr.Call(
                "minhaFunção",
                Arrays.asList(
                        new Expr.Literal(10),
                        new Expr.Variable("variável")
                )
        );

        assertEquals("minhaFunção(10, variável)", generator.visitCallExpr(expr));
    }

    @Test
    public void testVisitLiteralExpr() {
        Expr.Literal expr = new Expr.Literal(42);
        assertEquals("42", generator.visitLiteralExpr(expr));
    }

    @Test
    public void testVisitUnaryExpr() {
        // Not supported
    }

    @Test
    public void testVisitVariableExpr() {
        Expr.Variable expr = new Expr.Variable("umaVariável");
        assertEquals("umaVariável", generator.visitVariableExpr(expr));
    }
}