package blocks;

import java.awt.*;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import interpreter.Stmt;

public class BlockEditor extends BaseBlock {

	// private BaseBlock editor;
	int x, y;

	public BlockEditor(int x, int y, int width, int height, int index) {
		super(x, y, width, height, Color.GREEN, BaseBlock.Mode.STATIC, index, "src/blocks/editPlaceHolder.png");

		this.x = x;
		this.y = y;

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
		int sum = 0;
		for(int i = 0; i < nInstructions; i++){
			sum += blocks.get(i).height;
		}

		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, 10, sum + 5, nInstructions);
				super.addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, 10, sum + 5, nInstructions);
				super.addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, 10, sum + 5, nInstructions);
				super.addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, 10, sum + 5, nInstructions);
				super.addBlock(INTERACT);
				break;
		}
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
