package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interpreter.Direction;
import interpreter.Expr;
import interpreter.Stmt;
import interpreter.Stmt.Move;

public class IFBlock { 

	BlocoArrasta father;
	BlocoArrasta block;
	Expr.Variable leftHandSide = new Expr.Variable("a Direita"), rightHandSide = new Expr.Variable("uma parede");
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;
	Stmt thenBranch = null, elseBranch = null;
	
	public IFBlock(BlocoArrasta father) {
		
		this.father = father;
		
		block = new BlocoArrasta(700, 5, 250, 90, Color.YELLOW, BlocoArrasta.NOT_STATIC);
		    
		String direction[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};        
		JComboBox<String> dir = new JComboBox<String>(direction);
		dir.addActionListener(new ActionListener() {  
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String left = (String)cb.getSelectedItem();
			  	leftHandSide = new Expr.Variable(left);
			  	// Teste: System.out.println(leftHandSide);
			}
		});
				
		String operatorBox[] = {"igual à", "diferente de"};        
		JComboBox<String> op = new JComboBox<String>(operatorBox);
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
		
		String stats[] = {"uma parede", "nada", "um inimigo"};        
		JComboBox<String> st = new JComboBox<String>(stats);    
		st.addActionListener(new ActionListener() {  
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        String right = (String)cb.getSelectedItem();
		        rightHandSide = new Expr.Variable(right);
			  	// Teste: System.out.println(rightHandSide);
			}
		 }); 
		
		JButton plusButton = new JButton("+");
		String statements[] = {"Se", "Repete", "Move", "Interagir"};        
		JComboBox<String> stmts = new JComboBox<String>(statements);
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		//stmts.setBounds(block.WIDTH - 10, block.HEIGHT - 10, plusButton_w, plusButton_h);
		plusButton.setBounds(220, 200, plusButton_w, plusButton_h);
        
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		block.nInstructions++;
				String stmt = stmts.getItemAt(stmts.getSelectedIndex());
				
		        switch(stmt) {
		        	case "Se":
		        		block.altura += 90;
		        		
		        		blocks.IFBlock IF = new blocks.IFBlock(block);
		        		block.add(IF.getBlock());
		        		break;
		        		
		        	case "Repete":
		        		block.altura += 60;
		        		
		        		blocks.LOOPBlock LOOP = new blocks.LOOPBlock(block);
		        		block.add(LOOP.getBlock());
		        		break;
		        		
		        	case "Move":
		        		block.altura += 30;
		        		
		        		blocks.MOVEBlock MOVE = new blocks.MOVEBlock(block);
		        		block.add(MOVE.getBlock());
		        		break;
		        		
		        	case "Interagir":
		        		block.altura += 30;
		        		
		        		blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(block);
		        		block.add(INTERACT.getBlock());
		        		break;
		        }
		        
		        block.setPreferredSize(new Dimension(block.largura, block.altura));
		        
			}
		});
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				father.nInstructions--;
				father.altura -= block.altura;
				
				father.setPreferredSize(new Dimension(father.largura, father.altura));
				
				father.remove(block);
				father.repaint();
			}
		});
		
		block.add(new JLabel("Se "));
		block.add(dir);
		block.add(new JLabel("for "));
		block.add(op);
		block.add(st);
		block.add(stmts);
		block.add(plusButton);
		block.add(removeButton);
    }
	
	public BlocoArrasta getBlock() {
		return block;
	}
	
	public Stmt getStmt() {
		Stmt.If IF = new Stmt.If(new Expr.Binary(leftHandSide, operator, rightHandSide), thenBranch, elseBranch);
		
		return IF;
	}

}
