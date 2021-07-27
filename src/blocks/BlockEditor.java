package blocks;

import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import interpreter.Stmt;

public class BlockEditor extends BaseBlock {

	int nBlocks;

	public BlockEditor(int posX, int posY, int width, int height) {
		super(null, null, posX, posY, width, height, BaseBlock.Mode.EDITOR, -1, 0, "assets/Editor.png");

		//Area de manuseio dos blocos de programacao

		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		blockSelect.setBounds(width - 180, height - 35, 90, 30);
		add(blockSelect);
		plusButton.setBounds(width - 90, height - 35, 80, 30);
		add(plusButton);

		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));

		//JScrollPane scrollPane = new JScrollPane(this);
  		//scrollPane.setPreferredSize(new Dimension(width, lblH));
		//add(scrollPane);
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

	@Override
	public Stmt toStmt() {
		return new Stmt.Block(
				blocks.stream()
						.map(BaseBlock::toStmt)
						.collect(Collectors.toList())
		);
	}
}
