package interpreter;

public abstract class Stmt {
   public interface Visitor<R> {
      R visitIfStmt(If stmt);
      R visitInteract(Interact stmt);
      R visitMove(Move stmt);
      R visitLoop(Loop stmt);
   }

   public abstract <R> R access(Visitor<R> visitor);

   public static class If extends Stmt {
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
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitLoop(this);
      }
   }
}
