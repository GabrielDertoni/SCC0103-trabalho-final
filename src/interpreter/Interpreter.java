package interpreter;

import java.util.*;

import iterutils.Iterator;

public class Interpreter implements Stmt.Visitor<Iterator<Void>>, Expr.Visitor<Object> {

    private Environment environment;
    private Iterator<Void> interpretIterator;

    public Interpreter(List<Stmt> stmts, OutputDevice outputDevice) {
        environment = new Environment(outputDevice);
        addBuiltinFunctions();
        interpret(stmts);
    }

    public Interpreter(OutputDevice outputDevice) {
        environment = new Environment(outputDevice);
        addBuiltinFunctions();
        interpretIterator = null;
    }

    public boolean isNotFinished() {
        return interpretIterator != null && interpretIterator.hasNext();
    }

    public void advance() {
        interpretIterator.next();
    }

    public void interpret(List<Stmt> stmts) {
        // Add one empty iteration before the actual code begins.
        interpretIterator = new Iterator.Once<Void>(arg0 -> null)
                .chain(new Iterator.JavaAdapter<>(stmts.iterator())
                    .flatMap(stmt -> stmt.access(this))
                );
    }

    public Object evaluate(Expr expr) {
        return expr.access(this);
    }

    public void addBuiltinFunction(String name, Function<List<Object>, Object> func) {
        environment.addFunction(name, func);
    }

    public void setVariable(String name, Object value) {
        environment.setVariables(name, value);
    }

    private void addBuiltinFunctions() {
        addBuiltinFunction("imprime", (env, args) -> {
            for (Object arg : args) {
                env.outputDevice.print(arg.toString());
                env.outputDevice.print(" ");
            }
            return null;
        });

        addBuiltinFunction("imprimeLinha", (env, args) -> {
            env.callFunction("imprime", args);
            env.outputDevice.print(" ");
            return null;
        });

        addBuiltinFunction("imprimeFormatado", (env, args) -> {
            if (!(args.get(0) instanceof String)) {
                throw new RuntimeException("primeiro argumento para função 'imprimeFormatado' tem que ser um texto");
            }

            String format = (String)args.remove(0);
            env.outputDevice.print(String.format(format, args.toArray()));
            return null;
        });
    }

    /* Statement visitor */

    @Override
    public Iterator<Void> visitIfStmt(Stmt.If stmt) {
        return new Iterator.Once<Iterator<Void>>(arg0 -> {
            if (isTruthy(evaluate(stmt.conditional))) {
                return stmt.thenBranch.access(this);
            } else if (stmt.elseBranch != null) {
                return stmt.elseBranch.access(this);
            }
            return new Iterator.Empty<Void>();
        }).flatMap(java.util.function.Function.identity());
    }

    @Override
    public Iterator<Void> visitInteractStmt(Stmt.Interact stmt) {
        return new Iterator.Once<>(arg0 -> {
            environment.outputDevice.interact();
            return null;
        });
    }

    @Override
    public Iterator<Void> visitMoveStmt(Stmt.Move stmt) {
        return new Iterator.Once<>(arg0 -> {
            System.out.println("MOVE");
            environment.outputDevice.move(stmt.direction);
            return null;
        });
    }

    @Override
    public Iterator<Void> visitLoopStmt(Stmt.Loop stmt) {
        return new Iterator.Repeat()
                .takeWhile(arg0 -> isTruthy(evaluate(stmt.condition)))
                .flatMap(arg0 -> stmt.body.access(this));
    }

    @Override
    public Iterator<Void> visitRepeatStmt(Stmt.Repeat stmt) {
        Object value = stmt.numIterations.access(this);

        final int numIterations;
        if (value instanceof Double) {
            numIterations = (int)(double)value;
        } else {
            throw new InterpreterException("valor de repetição precisa ser numérico");
        }

        return new Iterator.Repeat()
                .take(numIterations)
                .flatMap(arg0 -> stmt.body.access(this));
    }

    @Override
    public Iterator<Void> visitBlockStmt(Stmt.Block stmt) {
        return new Iterator.JavaAdapter<>(stmt.stmts.iterator())
                .flatMap(innerStmt -> innerStmt.access(this));
    }

    @Override
    public Iterator<Void> visitExprStmt(Stmt.StmtExpr stmt) {
        return new Iterator.Once<>(arg0 -> {
            stmt.expr.access(this);
            return null;
        });
    }

    @Override
    public Iterator<Void> visitVariableDeclarationStmt(Stmt.VariableDeclaration stmt) {
        return new Iterator.Once<>(arg0 -> {
            environment.declareVariable(stmt.varName, stmt.initializer.access(this));
            return null;
        });
    }

    @Override
    public Iterator<Void> visitVariableAssignStmt(Stmt.VariableAssign stmt) {
        return new Iterator.Once<>(arg0 -> {
            environment.declareVariable(stmt.varName, stmt.value.access(this));
            return null;
        });
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
        if (!environment.isFunctionDefined(expr.functionName)) {
            throw new InterpreterException(String.format("função %s não foi definida", expr.functionName));
        }

        List<Object> arguments = new ArrayList<Object>();

        for (Expr argument : expr.arguments) {
            arguments.add(argument.access(this));
        }

        return environment.callFunction(expr.functionName, arguments);
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
        if (!environment.isVariableDefined(expr.name)) {
            throw new InterpreterException(String.format("variável %s foi usada mas não definida", expr.name));
        }
        return environment.lookupVariable(expr.name);
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
                    throw new InterpreterException(
                            String.format("Operador '%s' não pode ser usado com esses operandos", Expr.Binary.operatorString(operator))
                    );

                break;


            case DIV, MOD:
                if (!(left instanceof Double && right instanceof Double))
                    throw new InterpreterException(
                            String.format("Operador '%s' não pode ser usado com esses operandos", Expr.Binary.operatorString(operator))
                    );

                if ((double)right == 0)
                    throw new InterpreterException(
                            String.format("Divisão por 0 em operador '%s'", Expr.Binary.operatorString(operator))
                    );

                break;
        }
    }

    private boolean isEqual(Object left, Object right) {
        if (left == null && right == null) return true;

        if (left == null) return false;

        return left.equals(right);
    }
}
