package interpreter;

import java.util.List;

public class Interpreter implements Stmt.Visitor<Void> {
    public Interpreter() {}

    void interpret(List<Stmt> stmts) {
        for (Stmt stmt : stmts) {
            stmt.access(this);
        }
    }

    @Override
    public Void visitIfStmt(Stmt.If stmt) {
        if (stmt.conditional.toBoolean()) {
            stmt.thenBranch.access(this);
        } else if (stmt.elseBranch != null) {
            stmt.elseBranch.access(this);
        }
        return null;
    }

    @Override
    public Void visitInteract(Stmt.Interact stmt) {
        return null;
    }

    @Override
    public Void visitMove(Stmt.Move stmt) {
        return null;
    }

    @Override
    public Void visitLoop(Stmt.Loop stmt) {
        while (stmt.condition.evaluate().toBoolean()) {
            stmt.body.access(this);
        }

        return null;
    }

    @Override
    public Void visitBlock(Stmt.Block stmt) {

        return null;
    }
}
