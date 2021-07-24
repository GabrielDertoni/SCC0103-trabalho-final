package blocks;

import interpreter.Stmt;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class INTERACTBlock extends BaseBlock {

	public INTERACTBlock(BaseBlock father, int posX, int posY, int index) {
		super(posX, posY, 400, 80, Color.RED, BaseBlock.Mode.DRAGGABLE_Y, index, null);
		
		this.posX = posX;
		this.posY = posY;
		this.father = father;
		
		JLabel text = new JLabel("Interagir");
		text.setBounds(5, 15, 85, 15);
		add(text);
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.removeBlock(this);
			father.repaint();
		});
		removeButton.setBounds(5, 80 - 25, 120, 20);
		add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Interact();
	}
}
