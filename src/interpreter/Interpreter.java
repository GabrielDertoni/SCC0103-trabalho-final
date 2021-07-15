package interpreter;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Interpreter implements Stmt.Visitor<Stream<Void>>, Expr.Visitor<Object> {

    private Environment environment;

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter(new OutputDevice() {
            @Override
            public void print(String msg) {
                System.out.println(msg);
            }

            @Override
            public void interact() {
                System.out.println("interacted");
            }

            @Override
            public void move(Direction direction) {
                System.out.println("move " + direction.name());
            }
        });

        List<Stmt> stmts = Arrays.asList(
                new Stmt.VariableDeclaration(
                        "minhaVar",
                        new Expr.Literal(5)
                ),
                new Stmt.StmtExpr(
                        new Expr.Call(
                                "imprimeFormatado",
                                Arrays.asList(
                                        new Expr.Literal("minha variável possui valor %f"),
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
                                        new Expr.Literal("minha variável possui valor %f"),
                                        new Expr.Variable("minhaVar")
                                )
                        )
                )
        );

        PseudocodeGenerator gen = new PseudocodeGenerator();
        System.out.println(gen.fromStmts(stmts));

        Iterator<Void> interpretStream = interpreter.interpret(stmts).iterator();

        Scanner scanner = new Scanner(System.in);
        while (interpretStream.hasNext()) {
            interpretStream.next();
            scanner.nextLine();
        }
    }

    public Interpreter(OutputDevice outputDevice) {
        this.environment = new Environment(outputDevice);

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

    Stream<Void> interpret(List<Stmt> stmts) {
        return stmts.stream().flatMap((stmt) -> stmt.access(this));
    }

    public Object evaluate(Expr expr) {
        return expr.access(this);
    }

    public void addBuiltinFunction(String name, Function<List<Object>, Object> func) {
        environment.addFunction(name, func);
    }

    /* Statement visitor */

    @Override
    public Stream<Void> visitIfStmt(Stmt.If stmt) {
        return once(() -> {
            if (isTruthy(evaluate(stmt.conditional))) {
                stmt.thenBranch.access(this);
            } else if (stmt.elseBranch != null) {
                stmt.elseBranch.access(this);
            }
            return null;
        });
    }

    @Override
    public Stream<Void> visitInteractStmt(Stmt.Interact stmt) {
        return Stream.empty();
    }

    @Override
    public Stream<Void> visitMoveStmt(Stmt.Move stmt) {
        return Stream.empty();
    }

    @Override
    public Stream<Void> visitLoopStmt(Stmt.Loop stmt) {
        Interpreter iterpreter = this;

        return iteratorToStream(new Iterator<Void>() {
            private boolean condition = true;

            @Override
            public boolean hasNext() {
                return condition;
            }

            @Override
            public Void next() {
                condition = isTruthy(evaluate(stmt.condition));
                if (condition) {
                    stmt.body.access(iterpreter);
                }
                return null;
            }
        });
    }

    @Override
    public Stream<Void> visitBlockStmt(Stmt.Block stmt) {
        interpret(stmt.stmts);
        return Stream.empty();
    }

    @Override
    public Stream<Void> visitExprStmt(Stmt.StmtExpr stmt) {
        return once(() -> {
            stmt.expr.access(this);
            return null;
        });
    }

    @Override
    public Stream<Void> visitVariableDeclarationStmt(Stmt.VariableDeclaration stmt) {
        return once(() -> {
            environment.declareVariable(stmt.varName, stmt.initializer.access(this));
            return null;
        });
    }

    @Override
    public Stream<Void> visitVariableAssignStmt(Stmt.VariableAssign stmt) {
        return once(() -> {
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

    private <E> Stream<E> iteratorToStream(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @FunctionalInterface
    private interface OnceFunction<E> {
        E call();
    }

    private <E> Stream<E> once(OnceFunction<E> function) {
        return iteratorToStream(new Iterator<E>() {
            private boolean hasRun = false;

            @Override
            public boolean hasNext() {
                return !hasRun;
            }

            @Override
            public E next() {
                hasRun = true;
                return function.call();
            }
        });
    }
}
