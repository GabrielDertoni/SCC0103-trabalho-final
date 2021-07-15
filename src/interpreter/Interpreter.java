package interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Interpreter implements Stmt.Visitor<Void>, Expr.Visitor<Object> {

    private Map<String, Object> variables;

    public Interpreter() {
        variables = new HashMap<String, Object>();
    }

    void interpret(List<Stmt> stmts) {
        for (Stmt stmt : stmts) {
            stmt.access(this);
        }
    }

    public Object evaluate(Expr expr) {
        return expr.access(this);
    }

    /* Statement visitor */

    @Override
    public Void visitIfStmt(Stmt.If stmt) {
        if (isTruthy(evaluate(stmt.conditional))) {
            stmt.thenBranch.access(this);
        } else if (stmt.elseBranch != null) {
            stmt.elseBranch.access(this);
        }
        return null;
    }

    @Override
    public Void visitInteractStmt(Stmt.Interact stmt) {
        return null;
    }

    @Override
    public Void visitMoveStmt(Stmt.Move stmt) {
        return null;
    }

    @Override
    public Void visitLoopStmt(Stmt.Loop stmt) {
        while (isTruthy(evaluate(stmt.condition))) {
            stmt.body.access(this);
        }
        return null;
    }

    @Override
    public Void visitBlockStmt(Stmt.Block stmt) {
        interpret(stmt.stmts);
        return null;
    }

    @Override
    public Void visitVariableDeclarationStmt(Stmt.VariableDeclaration stmt) {
        variables.put(stmt.varName, stmt.initializer.access(this));
        return null;
    }

    @Override
    public Void visitVariableAssignStmt(Stmt.VariableAssign stmt) {
        variables.put(stmt.varName, stmt.value.access(this));
        return null;
    }

    /* Expression visitor */

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        assertCanPerformOperation(expr.operator, left, right);

        switch (expr.operator) {
            case EQUAL:
                return isEqual(left, right);

            case NOT_EQUAL:
                return !isEqual(left, right);

            case LESS:
                return (double)left < (double)right;

            case LESS_EQ:
                return (double)left <= (double)right;

            case GREATER :
                return (double)left > (double)right;

            case GREATER_EQ:
                return (double)left >= (double)right;

            case PLUS:
                return (double)left + (double)right;

            case MINUS:
                return (double)left - (double)right;

            case MULT:
                return (double)left * (double)right;

            case DIV:
                return (double)left / (double)right;

            case MOD:
                return (double)left % (double)right;

            case AND:
                return isTruthy(left) && isTruthy(right);

            case OR:
                return isTruthy(left) || isTruthy(right);
        }

        // Unreachable
        return null;
    }

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.literal;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        if (!variables.containsKey(expr.name)) {
            throw new InterpreterException(String.format("variável %s foi usada mas não definida", expr.name));
        }
        return variables.get(expr.name);
    }

    /* Helper functions */

    private boolean isTruthy(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Boolean) return (boolean)obj;
        return true;
    }

    /**
     * This function will throw a runtime error in case the operation referenced by `operator` cannot be performed with
     * operands `left` and `right`.
     *
     * @param operator
     * @param left
     * @param right
     */
    private void assertCanPerformOperation(Expr.Binary.Operator operator, Object left, Object right) {
        switch (operator) {
            // These operations can always be performed.
            case EQUAL, AND, OR:
                break;

            case LESS, LESS_EQ, GREATER, GREATER_EQ, PLUS, MINUS, MULT:
                if (!(left instanceof Double && right instanceof Double))
                    throw new InterpreterException(String.format("Operador %s não pode ser usado com esses operandos", operator.toString()));

                break;


            case DIV, MOD:
                if (!(left instanceof Double && right instanceof Double))
                    throw new InterpreterException(String.format("Operador %s não pode ser usado com esses operandos", operator.toString()));

                if ((double)right == 0)
                    throw new InterpreterException(String.format("Divisão por 0 em operador %s", operator.toString()));

                break;
        }
    }

    private boolean isEqual(Object left, Object right) {
        if (left == null && right == null) return true;

        if (left == null) return false;

        return left.equals(right);
    }
}
