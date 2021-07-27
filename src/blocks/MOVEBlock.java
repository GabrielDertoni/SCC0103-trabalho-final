package blocks;

import javax.swing.*;

import interpreter.Direction;
import interpreter.Stmt;

import java.awt.*;

import static game.Resources.whiteRabbit;

public class MOVEBlock extends BaseBlock {
	
	Direction direction = Direction.RIGHT;
	
	public MOVEBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
		super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, new Color(0x70, 0x52, 0x40));
  	  
		ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");

		JLabel text = new JLabel("Mova para");
		text.setBounds(125, 15, 85, 15);
		text.setFont(whiteRabbit.deriveFont(13f));
		text.setForeground(Color.WHITE);
		add(text);

  	  	String directions[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
        JComboBox<String> directionPicker = new JComboBox<String>(directions);
		directionPicker.setBounds(210, 10, 120, 20);
		directionPicker.setFont(whiteRabbit.deriveFont(13f));
		directionPicker.setForeground(Color.WHITE);
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
		
		Image resizedImage = btnBg.getImage().getScaledInstance(120, 20, Image.SCALE_SMOOTH);
		JButton removeButton = new JButton("Remover", new ImageIcon(resizedImage));
		removeButton.addActionListener(event -> {
			editor.nBlocks--;
			father.removeBlock(this);
			editor.repaint();
		});
		removeButton.setBounds(165, 80 - 25, 120, 20);
		removeButton.setFont(whiteRabbit.deriveFont(13f));
		removeButton.setVerticalTextPosition(JLabel.CENTER);
		removeButton.setHorizontalTextPosition(JLabel.CENTER);
		removeButton.setForeground(Color.WHITE);
        add(removeButton);

		setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Move(direction);
	}
}
