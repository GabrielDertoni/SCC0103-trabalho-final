package blocks;

import java.awt.*;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import interpreter.Stmt;

public class BlockEditor extends BaseBlock {

	// private BaseBlock editor;
	//int posX, posY;
	int nBlocks;

	public BlockEditor(int posX, int posY, int width, int height) {
		super(null, null, posX, posY, width, height, BaseBlock.Mode.STATIC, -1, "src/blocks/editPlaceHolder.png");

		//this.posX = posX;
		//this.posY = posY;

		//Area de manuseio dos blocos de programacao

		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		blockSelect.setBounds(width-plusButton_w-100, height-35, 90, 30);
		add(blockSelect);
		plusButton.setBounds(width-plusButton_w-10, height-35, plusButton_w, plusButton_h);
		add(plusButton);

		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));
	
	}

	private void addNewBlock(String blockName) {
		/*int sum = 0;
		for(int i = 0; i < nInstructions; i++){
			sum += blocks.get(i).height;
		}*/
		nInstructions++;

		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, this, 10, (nBlocks * 80) + 5, nBlocks);
				super.addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, this, 10, (nBlocks * 80), nBlocks);
				super.addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, this, 10, (nBlocks * 80), nBlocks);
				super.addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, this, 10, (nBlocks * 80), nBlocks);
				super.addBlock(INTERACT);
				break;

		}
		nBlocks++;
		updateUI();
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Block(
				blocks.stream()
						.map(BaseBlock::toStmt)
						.collect(Collectors.toList())
		);
	}
}
