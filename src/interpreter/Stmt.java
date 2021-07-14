package interpreter;

import java.util.List;

public abstract class Stmt {
   public interface Visitor<R> {
      R visitIfStmt(If stmt);
      R visitInteract(Interact stmt);
      R visitMove(Move stmt);
      R visitLoop(Loop stmt);
      R visitBlock(Block stmt);
   }

   public abstract <R> R access(Visitor<R> visitor);

   public static class If extends Stmt {
      public final ToBoolean conditional;
      public final Stmt thenBranch;
      public final Stmt elseBranch;

      public If(ToBoolean conditional, Stmt thenBranch, Stmt elseBranch) {
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
         return visitor.visitInteract(this);
      }
   }

   public static class Move extends Stmt {
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitMove(this);
      }
   }

   public static class Loop extends Stmt {
      public final Expr<ToBoolean> condition;
      public final Stmt body;

      public Loop(Expr<ToBoolean> condition, Stmt body) {
         this.condition = condition;
         this.body = body;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitLoop(this);
      }
   }

   public static class Block extends Stmt {
      public final List<Block> stmts;

      public Block(List<Block> stmts) {
         this.stmts = stmts;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitBlock(this);
      }
   }
}
