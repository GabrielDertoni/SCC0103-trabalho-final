package interpreter;

import java.util.List;

public abstract class Expr {
    public interface Visitor<R> {
        R visitBinaryExpr(Binary expr);
        R visitCallExpr(Call expr);
        R visitLiteralExpr(Literal expr);
        R visitUnaryExpr(Unary expr);
        R visitVariableExpr(Variable expr);
    }

    public abstract <R> R access(Expr.Visitor<R> visitor);

    public static class Binary extends Expr {
        public enum Operator {
            // Comparison operators
            NOT_EQUAL, EQUAL, LESS, LESS_EQ, GREATER, GREATER_EQ,

            // Arithmetic operators
            PLUS, MINUS, MULT, DIV, MOD,

            // Logic operators
            AND, OR
        }

        public static String operatorString(Operator operator) {
            switch (operator) {
                case NOT_EQUAL:  return "!=";
                case EQUAL:      return "==";
                case LESS:       return "<";
                case LESS_EQ:    return "<=";
                case GREATER:    return ">";
                case GREATER_EQ: return ">=";
                case PLUS:       return "+";
                case MINUS:      return "-";
                case MULT:       return "*";
                case DIV:        return "/";
                case MOD:        return "%";
                case AND:        return "e";
                case OR:         return "ou";
            }

            // Unreachable
            return null;
        }

        public final Expr left;
        public final Expr right;
        public final Operator operator;

        public Binary(Expr leftHandSide, Operator operator, Expr rightHandSide) {
            this.left = leftHandSide;
            this.right = rightHandSide;
            this.operator = operator;
        }

        @Override
        public <R> R access(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }
    }

    public static class Call extends Expr {
        public String functionName;
        public List<Expr> arguments;

        public Call(String functionName, List<Expr> arguments) {
            this.functionName = functionName;
            this.arguments = arguments;
        }

        @Override
        public <R> R access(Visitor<R> visitor) {
            return visitor.visitCallExpr(this);
        }
    }

    public static class Literal extends Expr {
        public final Object literal;

        public Literal(Object literal) {
            this.literal = literal;
        }

        @Override
        public <R> R access(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }

    public static class Unary extends Expr {

        @Override
        public <R> R access(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }
    }

    public static class Variable extends Expr {
        public final String name;

        public Variable(String name) {
            this.name = name;
        }

        @Override
        public <R> R access(Visitor<R> visitor) {
            return visitor.visitVariableExpr(this);
        }
    }
}