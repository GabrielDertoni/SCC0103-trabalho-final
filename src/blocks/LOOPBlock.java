package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interpreter.Expr;
import interpreter.Stmt;

public class LOOPBlock implements CodeBlock {
	
	JPanel father;
	BlocoArrasta block;
	Expr.Literal rightHandSide = new Expr.Literal(1);
	Stmt body = null;
	
	public LOOPBlock(JPanel father) {
  	  
		this.father = father;
		
		block = new BlocoArrasta(700, 5, 250, 60, Color.CYAN, BlocoArrasta.NOT_STATIC);
  	    
		SpinnerModel value = new SpinnerNumberModel(1, 1, 15, 1);  
        JSpinner spinner = new JSpinner(value);    
        
        spinner.addChangeListener(event -> {
			int right = (int)spinner.getValue();
			rightHandSide = new Expr.Literal(right);
        });
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.remove(block);
			father.repaint();
		});
		
        block.add(new JLabel("Repete "));
        block.add(spinner);
        block.add(new JLabel(" vezes"));
        block.add(removeButton);
	}

	public BlocoArrasta getBlock() {
		return block;
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Loop(
				new Expr.Binary(
						new Expr.Variable("i"),
						Expr.Binary.Operator.LESS,
						rightHandSide
				),
				body
		);
	}
}
