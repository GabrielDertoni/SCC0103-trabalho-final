package blocks;

import interpreter.Stmt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class INTERACTBlock implements CodeBlock {
	
	JPanel father;
	BaseBlock block;
	
	public INTERACTBlock(JPanel father) {
		
		this.father = father;
		
		block = new BaseBlock(700, 5, 200, 30, Color.RED, BaseBlock.Mode.DRAGGABLE);
		
        block.add(new JLabel("Interagir"));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				father.remove(block);
				father.repaint();
			}
		});
		
		block.add(removeButton);
	}

	@Override
	public BaseBlock getDraggablePanel() {
		return block;
	}

	@Override
	public Stmt toStmt() {
		return null;
	}
}
