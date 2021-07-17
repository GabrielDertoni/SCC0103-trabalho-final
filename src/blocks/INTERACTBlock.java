package blocks;

import java.awt.Color;

import javax.swing.JLabel;

import interpreter.Stmt;
import interpreter.Stmt.Visitor;
import menus.BlocoArrasta;

public class INTERACTBlock extends Stmt.Interact {
	
	int x, y, largura, altura;
	
	public BlocoArrasta INTERACTBlock() {
		
		x = 700;
		y = 5;
		largura = 100;
		altura = 30;
  	  
		BlocoArrasta interactBlock = new BlocoArrasta();
		interactBlock.setBounds(x, y, largura, altura);
		interactBlock.setBackground(Color.RED);         
        
        interactBlock.add(new JLabel("Interagir"));

        return interactBlock;
	}

	@Override
	public <R> R access(Visitor<R> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
