package blocks;

import interpreter.Stmt;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class INTERACTBlock extends BaseBlock {

	public INTERACTBlock(BaseBlock father) {
		super(700, 5, 400, 40, Color.RED, BaseBlock.Mode.DRAGGABLE_Y);
		
		this.father = father;
		
        add(new JLabel("Interagir"));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.removeBlock(this);
			father.repaint();
		});
		
		add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return null;
	}
}
