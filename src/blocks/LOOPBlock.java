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
import menus.BlocoArrasta;

public class LOOPBlock extends Stmt.Loop {
	
	public LOOPBlock(Expr condition, Stmt body) {
		super(condition, body);
	}

	int x, y, largura, altura;
	
	public BlocoArrasta block() {
		
		x = 700;
		y = 5;
		largura = 250;
		altura = 400;
  	  
		BlocoArrasta loopBlock = new BlocoArrasta();
		loopBlock.setBounds(x, y, largura, altura);
		loopBlock.setBackground(Color.CYAN);
  	    
		SpinnerModel value = new SpinnerNumberModel(1, 0, 20, 1);  
        JSpinner spinner = new JSpinner(value);   
        spinner.setBounds(100,100,50,30);    
        
        spinner.addChangeListener(new ChangeListener() {  
        	public void stateChanged(ChangeEvent e) {
      		String right = e.getSource().toString();
      		//new LOOPBlock(new Expr.Binary(new Expr.Variable("i"), Expr.Binary.Operator.LESS, new Expr.Literal(right)), Stmt.Loop.body);
      	  	}
        });
        
        loopBlock.add(new JLabel("Repete "));
        loopBlock.add(spinner);
        loopBlock.add(new JLabel(" vezes"));
        
        return loopBlock;
	}

	@Override
	public <R> R access(Visitor<R> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
