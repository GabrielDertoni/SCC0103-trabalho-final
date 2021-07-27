package blocks;

import interpreter.Stmt;

import javax.swing.*;

import java.awt.*;

import static game.Resources.whiteRabbit;

public class INTERACTBlock extends BaseBlock {

	public INTERACTBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
		super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, "assets/INTERACTBlock.png");

		ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");

		JLabel text = new JLabel("Interagir");
		text.setBounds(190, 15, 85, 15);
		text.setFont(whiteRabbit.deriveFont(13f));
		text.setForeground(Color.WHITE);
		add(text);

		Image resizedImage = btnBg.getImage().getScaledInstance(120, 20, Image.SCALE_SMOOTH);

		JButton removeButton = new JButton("Remover", new ImageIcon(resizedImage));
		removeButton.addActionListener(event -> {
			editor.nBlocks--;
			father.removeBlock(this);
			editor.repaint();
		});
		removeButton.setBounds(165, 80 - 25, 120, 20);
		removeButton.setFont(whiteRabbit.deriveFont(13f));
		removeButton.setForeground(Color.WHITE);
		removeButton.setVerticalTextPosition(JLabel.CENTER);
		removeButton.setHorizontalTextPosition(JLabel.CENTER);
		add(removeButton);
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Interact();
	}
}
