package blocks;

import java.awt.Color;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import interpreter.Expr;
import interpreter.Stmt;

public class LOOPBlock extends BaseBlock {
	private int numIterations = 1;

	public LOOPBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int listPos) {
  	  	super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.DRAGGABLE_Y, listPos, "src/blocks/LOOPPlaceHolder.png");

		JLabel text = new JLabel("Repete ");
		text.setBounds(143, 15, 60, 15);
		add(text);

		SpinnerModel value = new SpinnerNumberModel(1, 1, 15, 1);  
        JSpinner spinner = new JSpinner(value);    
		spinner.setBounds(203, 10, 40, 20);
		add(spinner);
        
        spinner.addChangeListener(event -> numIterations = (int)spinner.getValue());
		
		text = new JLabel(" vezes");
		text.setBounds(243, 15, 60, 10);
		add(text);

		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		blockSelect.setBounds(450 - 90 - 70, 80 - 25, 90, 20);
		add(blockSelect);
		
		JButton plusButton = new JButton("+");
		plusButton.addActionListener(event -> addBlock(blockSelect.getSelectedItem().toString()));
		plusButton.setBounds(450 - 65, 80 - 25, 60, 20);
		add(plusButton);

		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			editor.nBlocks--;
			father.removeBlock(this);
			editor.repaint();
		});
		removeButton.setBounds(5, 80 - 25, 120, 20);
		add(removeButton);
	}

	public void addBlock(String blockName) {
		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, editor, 40, ((listPos + nInstructions) * 80) + 5, listPos + nInstructions);
				
				super.addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, editor, 40, ((listPos + nInstructions) * 80) + 5, listPos + nInstructions);

				super.addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, editor, 40, ((listPos + nInstructions) * 80) + 5, listPos + nInstructions);

				super.addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, editor, 40, ((listPos + nInstructions) * 80) + 5, listPos + nInstructions);

				super.addBlock(INTERACT);
				break;
		}
		editor.nBlocks++;
		//setPreferredSize(new Dimension(width, height));
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Repeat(
				new Expr.Literal(numIterations),
				new Stmt.Block(
					blocks.stream()
						.map(BaseBlock::toStmt)
						.collect(Collectors.toList())
				)
		);
	}
}
