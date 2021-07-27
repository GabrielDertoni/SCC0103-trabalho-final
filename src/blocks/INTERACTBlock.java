package blocks;

import interpreter.Stmt;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class INTERACTBlock extends BaseBlock {

	public INTERACTBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
		super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, "assets/INTERACTBlock.png");
		
		JLabel text = new JLabel("Interagir");
		text.setBounds(190, 15, 85, 15);
		add(text);
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			editor.nBlocks--;
			father.removeBlock(this);
			editor.repaint();
		});
		removeButton.setBounds(165, 80 - 25, 120, 20);
		add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Interact();
	}
}
