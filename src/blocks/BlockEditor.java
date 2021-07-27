package blocks;

import java.awt.*;
import java.util.stream.Collectors;

import javax.swing.*;

import interpreter.Stmt;

public class BlockEditor extends BaseBlock {
	Image image;
	int nBlocks;

	public BlockEditor(int posX, int posY, int width, int height) {
		super(null, null, posX, posY, width, height, BaseBlock.Mode.EDITOR, -1, 0, "assets/Editor.png");
		setBackground(Color.GRAY);

		//Area de manuseio dos blocos de programacao

		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		blockSelect.setBounds(width - 180, height - 35, 90, 30);
		add(blockSelect);
		plusButton.setBounds(width - 90, height - 35, 80, 30);
		add(plusButton);

		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));
	}

	private void addNewBlock(String blockName) {
		nInstructions++;

		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, this, 10, (nBlocks * 80) + 5, depth + 1, nBlocks);
				super.addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, this, 10, (nBlocks * 80) + 5, depth + 1, nBlocks);
				super.addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, this, 10, (nBlocks * 80) + 5, depth + 1, nBlocks);
				super.addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, this, 10, (nBlocks * 80) + 5, depth + 1, nBlocks);
				super.addBlock(INTERACT);
				break;

			default:
				break;
		}
		nBlocks++;
		updateUI();
	}

	public void setImage(String imgName){
		this.image = new ImageIcon(imgName).getImage();
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Block(
				blocks.stream()
						.map(BaseBlock::toStmt)
						.collect(Collectors.toList())
		);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}
}
