package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interpreter.Direction;
import interpreter.Stmt;

public class MOVEBlock {
	
	JPanel father;
	BlocoArrasta block;
	Direction direction = Direction.RIGHT;
	
	public MOVEBlock(JPanel father) {
  	  
		this.father = father;
		
  	  	block = new BlocoArrasta(700, 5, 300, 30, Color.PINK, BlocoArrasta.NOT_STATIC);
  	  	
  	  	String directions[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
        JComboBox<String> dir = new JComboBox<String>(directions);
        
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
      		  	// Teste: System.out.println(direction);
      	  }
        });
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				father.remove(block);
				father.repaint();
			}
		});
        
        block.add(new JLabel("Mova para"));
        block.add(dir);
        block.add(removeButton);
	}

	public BlocoArrasta getBlock() {
		return block;
	}
	
	public Stmt getStmt() {
		Stmt.Move MOVE = new Stmt.Move(direction);
		
		return MOVE;
	}
	
}
