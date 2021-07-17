package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Expr;
import interpreter.Stmt;
import menus.BlocoArrasta;

public class IFBlock extends Stmt.If {
	
	public IFBlock(Expr conditional, Stmt thenBranch, Stmt elseBranch) {
		super(conditional, thenBranch, elseBranch);
	}

	int x, y, largura, altura;
	
	public BlocoArrasta IFblock() {
		
		x = 700;
		y = 5;
		largura = 250;
		altura = 250;
	  
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
			  	new If(new Expr.Binary(new Expr.Variable(left), Stmt.If.conditional.operator, Stmt.If.conditional.rightHandSide), Stmt.If.thenBranch, Stmt.If.elseBranch);
			}
		});
		
		op.addActionListener(new ActionListener() {  
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String operator = (String)cb.getSelectedItem();
		        
		        if(operator == "igual à") {
		        	new If(new Expr.Binary(Stmt.If.conditional.leftHandSide, Expr.Binary.Operator.EQUAL, Stmt.If.conditional.rightHandSide), Stmt.If.thenBranch, Stmt.If.elseBranch);
		        }else {
		        	new If(new Expr.Binary(Stmt.If.conditional.leftHandSide, Expr.Binary.Operator.NOT_EQUAL, Stmt.If.conditional.rightHandSide), Stmt.If.thenBranch, Stmt.If.elseBranch);
		        }
			}
		 });
		
		st.addActionListener(new ActionListener() {  
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String right = (String)cb.getSelectedItem();
			  	new If(new Expr.Binary(Stmt.If.conditional.leftHandSide, Stmt.If.conditional.operator, new Expr.Variable(right)), Stmt.If.thenBranch, Stmt.If.elseBranch);
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
		// TODO Auto-generated method stub
		return null;
	}
}
