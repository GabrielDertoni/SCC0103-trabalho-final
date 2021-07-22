package blocks;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;

import interpreter.PseudocodeGenerator;
import interpreter.Stmt;

public class BlockEditor extends BaseBlock {

	// private BaseBlock editor;

	public BlockEditor(int x, int y, int width, int height) {
		super((int) (width*2/3), 0, (int) (width/3), (height*3/5), Color.GREEN, BaseBlock.Mode.STATIC);

		setBounds(x, y, width, height);
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.GREEN);

		//Area de manuseio dos blocos de programacao

		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		blockSelect.setBounds(width-plusButton_w-125, height-250, 90, 30);
		add(blockSelect);
		plusButton.setBounds(width-plusButton_w-30, height-250, plusButton_w, plusButton_h);
		add(plusButton);

		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));
	
	}

	private void addNewBlock(String blockName) {
		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, 680);
				super.addBlock(IF, 0);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, 680);
				super.addBlock(LOOP, 0);
				add(LOOP);
				blocks.add(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this);
				super.addBlock(MOVE, 0);
				add(MOVE);
				blocks.add(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this);
				super.addBlock(INTERACT, 0);
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
