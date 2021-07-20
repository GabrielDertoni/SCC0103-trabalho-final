package blocks;

import java.awt.Color;

import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Stmt;
import interpreter.Stmt.Visitor;

public class INTERACTBlock {
	
	BlocoArrasta block;
	
	public INTERACTBlock() {
		
		block = new BlocoArrasta(700, 5, 100, 30, Color.RED);
		
        block.add(new JLabel("Interagir"));
	}
	
	public BlocoArrasta getBlock() {
		return block;
	}
}
