package blocks;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Stmt;

import java.awt.*;

public class MOVEBlock extends BaseBlock {
	
	Direction direction = Direction.RIGHT;
	
	public MOVEBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
		super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, new Color(0x70, 0x52, 0x40));
  	  
		JLabel text = new JLabel("Mova para");
		text.setBounds(125, 15, 85, 15);
		add(text);

  	  	String directions[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
        JComboBox<String> directionPicker = new JComboBox<String>(directions);
		directionPicker.setBounds(210, 10, 120, 20);
		add(directionPicker);

		directionPicker.addActionListener(e -> {
			String dir = (String)directionPicker.getSelectedItem();

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
				default:
					break;
			}
        });
		
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
		return new Stmt.Move(direction);
	}
}
