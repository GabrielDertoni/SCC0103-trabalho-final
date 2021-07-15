package interpreter;

import java.util.List;

public abstract class Stmt {
   public interface Visitor<R> {
      R visitIfStmt(If stmt);
      R visitInteractStmt(Interact stmt);
      R visitMoveStmt(Move stmt);
      R visitLoopStmt(Loop stmt);
      R visitBlockStmt(Block stmt);
      R visitExprStmt(StmtExpr stmt);
      R visitVariableDeclarationStmt(VariableDeclaration stmt);
      R visitVariableAssignStmt(VariableAssign stmt);
   }

   public abstract <R> R access(Visitor<R> visitor);

   public static class If extends Stmt {
      public final Expr conditional;
      public final Stmt thenBranch;
      public final Stmt elseBranch;

      public If(Expr conditional, Stmt thenBranch, Stmt elseBranch) {
         this.conditional = conditional;
         this.thenBranch = thenBranch;
         this.elseBranch = elseBranch;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitIfStmt(this);
      }
   }

   public static class Interact extends Stmt {
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitInteractStmt(this);
      }
   }

   public static class Move extends Stmt {
      public enum Direction {
         UP, DOWN, LEFT, RIGHT
      }

      public static String getDirectionName(Direction direction) {
         switch (direction) {
            case UP:    return "cima";
            case DOWN:  return "baixo";
            case LEFT:  return "esquerda";
            case RIGHT: return "direita";
         }

         // Unreachable
         return null;
      }

      public final Direction direction;

      public Move(Direction direction) {
         this.direction = direction;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitMoveStmt(this);
      }
   }

   public static class Loop extends Stmt {
      public final Expr condition;
      public final Stmt body;

      public Loop(Expr condition, Stmt body) {
         this.condition = condition;
         this.body = body;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitLoopStmt(this);
      }
   }

   public static class Block extends Stmt {
      public final List<Stmt> stmts;

      public Block(List<Stmt> stmts) {
         this.stmts = stmts;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitBlockStmt(this);
      }
   }

   public static class StmtExpr extends Stmt {
      public final Expr expr;

      public StmtExpr(Expr expr) {
         this.expr = expr;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitExprStmt(this);
      }
   }

   public static class VariableDeclaration extends Stmt {
      public final String varName;
      public final Expr initializer;

      public VariableDeclaration(String varName, Expr initializer) {
         this.varName = varName;
         this.initializer = initializer;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitVariableDeclarationStmt(this);
      }
   }

   public static class VariableAssign extends Stmt {
      public final String varName;
      public final Expr value;

      public VariableAssign(String varName, Expr value) {
         this.varName = varName;
         this.value = value;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitVariableAssignStmt(this);
      }
   }
}
