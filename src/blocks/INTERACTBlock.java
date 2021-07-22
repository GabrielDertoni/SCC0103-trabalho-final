package blocks;

import interpreter.Stmt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class INTERACTBlock extends BaseBlock {

	BaseBlock father;

	public INTERACTBlock(BaseBlock father) {
		super(700, 5, 200, 30, Color.RED, BaseBlock.Mode.DRAGGABLE);
		
		this.father = father;
		
        add(new JLabel("Interagir"));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.remove(this);
			father.repaint();
		});
		
		add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return null;
	}
}
