package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Expr;
import interpreter.Stmt;

public class MOVEBlock {

	BlocoArrasta block;
	Direction direction;
	
	public MOVEBlock() {
  	  
  	  	block = new BlocoArrasta(700, 5, 200, 30, Color.PINK);
  	  	
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
        
        block.add(new JLabel("Mova para"));
        block.add(dir);
	}

	public BlocoArrasta getBlock() {
		return block;
	}
}
