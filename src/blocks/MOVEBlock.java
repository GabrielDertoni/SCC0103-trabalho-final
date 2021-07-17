package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Stmt;
import menus.BlocoArrasta;

public class MOVEBlock extends Stmt.Move {

	public MOVEBlock(Direction direction) {
		super(direction);
	}

	int x, y, largura, altura;
	
	public BlocoArrasta block() {
		
		x = 700;
		y = 5;
  	  	largura = 200;
  	  	altura = 30;
  	  
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
      		  			new MOVEBlock(Direction.RIGHT);
      		  			break;
      		  		case "Cima":
      		  			new MOVEBlock(Direction.UP);
      		  			break;
      		  		case "a Esquerda":
      		  			new MOVEBlock(Direction.LEFT);
      		  			break;
      		  		case "Baixo":
      		  			new MOVEBlock(Direction.DOWN);
      		  			break;
      		  	}
      		  	//System.out.println(Move.direction);
      	  }
        });
        
        moveBlock.add(new JLabel("Mova para"));
        moveBlock.add(dir);
 
  	  	return moveBlock;
    }

	@Override
	public <R> R access(Visitor<R> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
