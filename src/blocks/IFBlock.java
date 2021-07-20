package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Expr;
import interpreter.Stmt;

public class IFBlock { 

	BlocoArrasta block;
	Expr.Variable leftHandSide = new Expr.Variable("a Direita"), rightHandSide = new Expr.Variable("uma parede");
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;
	Stmt thenBranch = null, elseBranch = null;
	
	public IFBlock() {
	  
		block = new BlocoArrasta(700, 5, 250, 250, Color.YELLOW);
		    
		String direction[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};        
		JComboBox<String> dir = new JComboBox<String>(direction);
		
		String operatorBox[] = {"igual à", "diferente de"};        
		JComboBox<String> op = new JComboBox<String>(operatorBox);
		
		String stats[] = {"uma parede", "nada", "um inimigo"};        
		JComboBox<String> st = new JComboBox<String>(stats);    
		st.setBounds(100, 50, 90, 20);

		
		dir.addActionListener(new ActionListener() {  
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String left = (String)cb.getSelectedItem();
			  	leftHandSide = new Expr.Variable(left);
			  	// Teste: System.out.println(leftHandSide);
			}
		});
		
		op.addActionListener(new ActionListener() {  
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String operatorString = (String)cb.getSelectedItem();
		        
		        if(operatorString == "igual à") {
		        	operator = Expr.Binary.Operator.EQUAL;
		        }else {
		        	operator = Expr.Binary.Operator.NOT_EQUAL;
		        }
			  	// Teste: System.out.println(operator);
			}
		 });
		
		st.addActionListener(new ActionListener() {  
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String right = (String)cb.getSelectedItem();
		        rightHandSide = new Expr.Variable(right);
			  	// Teste: System.out.println(rightHandSide);
			}
		 }); 
		
		block.add(new JLabel("Se "));
		block.add(dir);
		block.add(new JLabel("for "));
		block.add(op);
		block.add(st);
    }
	
	public BlocoArrasta getBlock() {
		return block;
	}
	
	public Stmt getStmt() {
		Stmt.If IF = new Stmt.If(new Expr.Binary(leftHandSide, operator, rightHandSide), thenBranch, elseBranch);
		
		return IF;
	}

}
