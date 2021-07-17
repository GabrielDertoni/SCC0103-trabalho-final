package interpreter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
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
          
          String direction[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};        
          JComboBox<String> dir = new JComboBox<String>(direction);    
          dir.setBounds(100, 50, 90, 20);
          
          String operator[] = {"igual à", "diferente de"};        
          JComboBox<String> op = new JComboBox<String>(operator);    
          op.setBounds(100, 50, 90, 20);
          
          String stats[] = {"uma parede", "nada", "um inimigo"};        
          JComboBox<String> st = new JComboBox<String>(stats);    
          st.setBounds(100, 50, 90, 20);
          
          dir.addActionListener(new ActionListener() {  

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String left = (String)cb.getSelectedItem();
      		  	conditional.left = new Expr.Variable(left);
			}
          });
          
          op.addActionListener(new ActionListener() {  

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				JComboBox<String> cb = (JComboBox<String>) e.getSource();
  		        String operator = (String)cb.getSelectedItem();
  		        
  		        if(operator == "igual à") {
  		        	conditional.operator = Expr.Binary.Operator.EQUAL;
 		        }else {
 		        	conditional.operator = Expr.Binary.Operator.NOT_EQUAL;
 		        }
  			}
           });
          
          st.addActionListener(new ActionListener() {  

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				JComboBox<String> cb = (JComboBox<String>) e.getSource();
  		        String right = (String)cb.getSelectedItem();
        		conditional.right = new Expr.Variable(right);
  			}
           }); 
          
          ifBlock.add(new JLabel("Se "));
          ifBlock.add(dir);
          ifBlock.add(new JLabel("for "));
          ifBlock.add(op);
          ifBlock.add(st);
          
    	  return ifBlock;
      }

      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitIfStmt(this);
      }
   }
   //================================================================

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
    	    
          SpinnerModel value = new SpinnerNumberModel(1, 0, 20, 1);  
          JSpinner spinner = new JSpinner(value);   
          spinner.setBounds(100,100,50,30);    
          
          spinner.addChangeListener(new ChangeListener() {  
              public void stateChanged(ChangeEvent e) {
        		  String right = e.getSource().toString();
        		  condition = new Expr.Binary(new Expr.Variable("i"), Expr.Binary.Operator.LESS, new Expr.Literal(right));
        	  }
          });
          
          loopBlock.add(new JLabel("Repete "));
          loopBlock.add(spinner);
          loopBlock.add(new JLabel(" vezes"));
          
    	  return loopBlock;
      }
      
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitLoopStmt(this);
      }
   }
   //================================================================

   //=========================== INTERACT ===========================
   public static class Interact extends Stmt {
      public Interact() {}

      public BlocoArrasta block() {
    	  int x = 700, y = 5;
    	  int largura = 100, altura = 30;
    	  
    	  BlocoArrasta interactBlock = new BlocoArrasta();
    	  interactBlock.setBounds(x, y, largura, altura);
    	  interactBlock.setBackground(Color.RED);         
          
          interactBlock.add(new JLabel("Interagir"));
   
    	  return interactBlock;
      }
      
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitInteractStmt(this);
      }
   }
   //================================================================
   
   //============================= MOVE =============================
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

      public Direction direction;

      public Move(Direction direction) {
         this.direction = direction;
      }

      public BlocoArrasta block() {
    	  int x = 700, y = 5;
    	  int largura = 200, altura = 30;
    	  
    	  BlocoArrasta moveBlock = new BlocoArrasta();
    	  moveBlock.setBounds(x, y, largura, altura);
    	  moveBlock.setBackground(Color.PINK);         
 
    	  String directions[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
          JComboBox<String> dir = new JComboBox<String>(directions);    
          dir.setBounds(100, 50, 90, 20);
          
          dir.addActionListener(new ActionListener() {  

        	  @Override
        	  public void actionPerformed(ActionEvent e) {
        		  JComboBox<String> cb = (JComboBox<String>) e.getSource();
        		  String dir = (String)cb.getSelectedItem();
		    
        		  switch(dir) {
        		  case "a Direita":
        			  direction = Direction.RIGHT;
        		      break;
        		  case "Cima":
        			  direction = Direction.UP;
        			  break;
        		  case "a Esquerda":
        			  direction = Direction.LEFT;
        			  break;
        		  case "Baixo":
        			  direction = Direction.DOWN;
        			  break;
        		  }
        		  System.out.println(direction);
        	  }
          });
          
          moveBlock.add(new JLabel("Mova para"));
          moveBlock.add(dir);
   
    	  return moveBlock;
      }
      
      @Override
      public <R> R access(Visitor<R> visitor) {
         return visitor.visitMoveStmt(this);
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
