package interpreter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interpreter.Expr.Binary;
import interpreter.Expr.Literal;
import interpreter.Expr.Variable;
import menus.BlocoArrasta;

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

   //============================== IF ==============================
   public static class If extends Stmt {
      public Expr.Binary conditional;
      public Stmt thenBranch;
      public Stmt elseBranch;

      public If(Expr.Variable left, Expr.Binary.Operator operator, Expr.Variable right, Stmt thenBranch, Stmt elseBranch) {
         this.conditional = new Expr.Binary(left, operator, right);
         this.thenBranch = thenBranch;
         this.elseBranch = elseBranch;
      }
      
      public BlocoArrasta block() {
    	  int x = 700, y = 5;
    	  int largura = 250, altura = 250;
    	  
    	  BlocoArrasta ifBlock = new BlocoArrasta();
    	  ifBlock.setBounds(x, y, largura, altura);
    	  ifBlock.setBackground(Color.YELLOW); 
          
          JLabel se = new JLabel("Se ");  
          //se.setBounds(10,30, 200,200);
          
          String direction[]={"a Direita", "Cima", "a Esquerda", "Baixo"};        
          JComboBox dir = new JComboBox(direction);    
          dir.setBounds(100, 50, 90, 20);
          
          JLabel be = new JLabel("for ");  
          be.setBounds(10,30, 200,200);
          
          String operator[]={"igual à", "diferente de"};        
          JComboBox op = new JComboBox(operator);    
          op.setBounds(100, 50, 90, 20);
          
          String stats[]={"uma parede", "nada", "um inimigo"};        
          JComboBox st = new JComboBox(stats);    
          st.setBounds(100, 50, 90, 20);
          
          JButton b = new JButton("Show");  
          b.setBounds(200,100,75,20);  
          b.addActionListener(new ActionListener() { 
        	  @Override
        	  public void actionPerformed(ActionEvent e) {       
        		  Expr.Variable left = new Expr.Variable((String) dir.getItemAt(dir.getSelectedIndex()));
        		  String operator = (String) op.getItemAt(op.getSelectedIndex());
        		  Expr.Variable right = new Expr.Variable((String) st.getItemAt(st.getSelectedIndex()));
        		  
        		  if(operator == "igual à") {
        		         conditional = new Expr.Binary(left, Expr.Binary.Operator.EQUAL, right);
        		         thenBranch = null;
        		         elseBranch = null;
        		  }else {
        		         conditional = new Expr.Binary(left, Expr.Binary.Operator.NOT_EQUAL, right);
        		         thenBranch = null;
        		         elseBranch = null;
        		  }
        	  }
          });           
          
          ifBlock.add(se);
          ifBlock.add(dir);
          ifBlock.add(be);
          ifBlock.add(op);
          ifBlock.add(st);
          ifBlock.add(b);
   
    	  return ifBlock;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitIfStmt(this);
      }
   }
   //================================================================

   public static class Interact extends Stmt {
      public Interact() {}

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitInteractStmt(this);
      }
   }

   public static class Move extends Stmt {

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

   //============================= LOOP =============================
   public static class Loop extends Stmt {
      public Expr.Binary condition;
      public Stmt body;

      public Loop(Expr.Literal number, Stmt body) {
         this.condition = new Expr.Binary(new Expr.Variable("i"), Expr.Binary.Operator.LESS, number);
         this.body = body;
      }

      public BlocoArrasta block() {
    	  int x = 700, y = 5;
    	  int largura = 250, altura = 400;
    	  
    	  BlocoArrasta loopBlock = new BlocoArrasta();
    	  loopBlock.setBounds(x, y, largura, altura);
    	  loopBlock.setBackground(Color.CYAN); 
          
          JLabel repete = new JLabel("Repete ");
          
          SpinnerModel value = new SpinnerNumberModel(1, 0, 20, 1);  
          JSpinner spinner = new JSpinner(value);   
          spinner.setBounds(100,100,50,30);    
          
          spinner.addChangeListener(new ChangeListener() {  
              public void stateChanged(ChangeEvent e) {
        		  String right = e.getSource().toString();
        		  condition = new Expr.Binary(new Expr.Variable("i"), Expr.Binary.Operator.LESS, new Expr.Literal(right));
        	  }
          });           
          
          loopBlock.add(repete);
          loopBlock.add(spinner);
   
    	  return loopBlock;
      }
      
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitLoopStmt(this);
      }
   }
   //================================================================
   
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
