package blocks;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interpreter.Expr;
import interpreter.Stmt;

public class LOOPBlock {
	
	BlocoArrasta block;
	Expr.Literal rightHandSide = new Expr.Literal(1);
	Stmt body = null;
	
	public LOOPBlock() {
  	  
		block = new BlocoArrasta(700, 5, 250, 400, Color.CYAN);
  	    
		SpinnerModel value = new SpinnerNumberModel(1, 1, 15, 1);  
        JSpinner spinner = new JSpinner(value);    
        
        spinner.addChangeListener(new ChangeListener() {  
        	public void stateChanged(ChangeEvent e) {
        		int right = (int) ((JSpinner) e.getSource()).getValue();
        		rightHandSide = new Expr.Literal(right);
        		// Teste: System.out.println(rightHandSide);
      	  	}
        });
        
        block.add(new JLabel("Repete "));
        block.add(spinner);
        block.add(new JLabel(" vezes"));
	}

	public BlocoArrasta getBlock() {
		return block;
	}
	
	public Stmt getStmt() {
		Stmt.Loop LOOP = new Stmt.Loop(new Expr.Binary(new Expr.Variable("i"), Expr.Binary.Operator.LESS, rightHandSide), body);
		
		return LOOP;
	}
	
}
