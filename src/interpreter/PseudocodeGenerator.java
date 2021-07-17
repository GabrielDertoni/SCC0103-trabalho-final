package interpreter;

import java.util.List;

public class PseudocodeGenerator implements Stmt.Visitor<String>, Expr.Visitor<String> {

    private final String tab;

    private int indentationLevel;
    private int precedence;

    public PseudocodeGenerator() {
        indentationLevel = 0;
        tab = "    ";
        precedence = 0;
    }

    public String fromStmts(List<Stmt> stmts) {
        String result = "";
        for (Stmt stmt : stmts) {
            result += indentation();
            result += stmt.access(this);
            result += "\n";
        }
        return result;
    }

    public String fromExpr(Expr expr) {
        return expr.access(this);
    }

    /* Statement visitor */

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        String result = "se " + fromExpr(stmt.conditional) + " ";

        boolean isThenBlock = stmt.thenBranch instanceof Stmt.Block;

        // In case the then branch is not a block, we want to have a line break and then the statement indented. If it
        // is a block, the block will provide newline and indentation.
        if (!isThenBlock) {
            result += "\n";
            indentationLevel++;
            result += indentation();
        }

        result += stmt.thenBranch.access(this);

        if (!isThenBlock) {
            indentationLevel--;
        }

        if (stmt.elseBranch != null) {
            boolean isElseBlock = stmt.elseBranch instanceof Stmt.Block;

            result += " se não ";

            if (!isElseBlock) {
                result += "\n";
                indentationLevel++;
                result += indentation();
            }

            result += stmt.elseBranch.access(this);

            if (!isElseBlock) {
                indentationLevel--;
                result += "\n";
            }
        }
        return result;
    }

    @Override
    public String visitInteractStmt(Stmt.Interact stmt) {
        return "interage();";
    }

    @Override
    public String visitMoveStmt(Stmt.Move stmt) {
        return "movePara " + Stmt.Move.getDirectionName(stmt.direction) + ";";
    }

    @Override
    public String visitLoopStmt(Stmt.Loop stmt) {
        String result = "enquanto " + fromExpr(stmt.condition) + " ";

        boolean isBlockStatement = stmt.body instanceof Stmt.Block;

        if (!isBlockStatement) {
            result += "\n";
            indentationLevel++;
            result += indentation();
        }

        result += stmt.body.access(this);

        if (!isBlockStatement) {
            indentationLevel--;
            result += "\n";
        }

        return result;
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        String result = "{\n";
        indentationLevel++;
        for (Stmt in_block : stmt.stmts) {
            result += indentation();
            result += in_block.access(this);
            result += "\n";
        }
        indentationLevel--;
        result += "}";
        return result;
    }

    @Override
    public String visitExprStmt(Stmt.StmtExpr stmt) {
        return stmt.expr.access(this) + ";";
    }

    @Override
    public String visitVariableDeclarationStmt(Stmt.VariableDeclaration stmt) {
        String result = "var " + stmt.varName;
        if (stmt.initializer != null) {
            result += " = " + stmt.initializer.access(this);
        }
        return result + ";";
    }

    @Override
    public String visitVariableAssignStmt(Stmt.VariableAssign stmt) {
        return stmt.varName + " = " + fromExpr(stmt.value) + ";";
    }

    /* Expression visitor */

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        String op = Expr.Binary.operatorString(expr.operator);
        int opPrecedence = getPrecedenceOf(expr.operator);

        // We update the precedence to match the current operator for the subexpressions. When they are done, we restore
        // the old precedence to continue. If the precedence of the current operator is too low, we put parenthesis around
        // the expression.

        int startingPrecedence = precedence;
        precedence = opPrecedence;
        String result = fromExpr(expr.left) + " " + op + " " + fromExpr(expr.right);
        precedence = startingPrecedence;

        if (opPrecedence < precedence) {
            return parenthesize(result);
        } else {
            return result;
        }
    }

    /**
     * Formato de chamada de função: "nomeFunção(param1, param2, param3)"
     *
     * @param expr
     * @return
     */
    @Override
    public String visitCallExpr(Expr.Call expr) {
        String result = expr.functionName + "(";
        for (int i = 0; i < expr.arguments.size(); i++) {
            result += expr.arguments.get(i).access(this);

            if (i < expr.arguments.size() - 1) {
                result += ", ";
            }
        }
        result += ")";
        return result;
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.literal instanceof Boolean) {
            return (boolean)expr.literal ? "verdadeiro" : "false";
        }

        if (expr.literal instanceof String) {
            return "\"" + expr.literal + "\"";
        }

        return expr.literal.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        // There is no unary operator in the current language specification.
        return "[undefined]";
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name;
    }

    /* utils */

    private String indentation() {
        String result = "";
        for (int i = 0; i < indentationLevel; i++) {
            result += tab;
        }
        return result;
    }

    private String parenthesize(String input) {
        return "(" + input + ")";
    }

    private int getPrecedenceOf(Expr.Binary.Operator operator) {
        switch (operator) {
            case OR:             return 0;
            case AND:            return 1;
            case NOT_EQUAL, EQUAL,
                 LESS, LESS_EQ, GREATER,
                 GREATER_EQ:     return 2;
            case PLUS, MINUS:    return 3;
            case MULT, DIV, MOD: return 4;
        }
        // Unreachable
        return -1;
    }
}
