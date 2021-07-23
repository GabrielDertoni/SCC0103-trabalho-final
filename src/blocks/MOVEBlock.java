package blocks;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Direction;
import interpreter.Stmt;

public class MOVEBlock extends BaseBlock {
	
	Direction direction = Direction.RIGHT;
	
	public MOVEBlock(BaseBlock father) {
		super(700, 5, 300, 30, Color.PINK, BaseBlock.Mode.DRAGGABLE_Y);
  	  
		this.father = father;
		
  	  	String directions[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
        JComboBox<String> directionPicker = new JComboBox<String>(directions);

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
			}
        });
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.removeBlock(this);
			father.repaint();
		});
        
        add(new JLabel("Mova para"));
        add(directionPicker);
        add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Move(direction);
	}
}
