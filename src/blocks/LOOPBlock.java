package blocks;

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

	public LOOPBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
  	  	super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, "assets/LOOPBlock.png");

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

		JComboBox<String> blockSelect;
		if(depth < 3){
			blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		}else{
			blockSelect = new JComboBox<String>(new String[]{"Move", "Interagir"});
		}
		blockSelect.setBounds(450 - 90 - 70, 80 - 25, 90, 20);
		add(blockSelect);
		
		JButton plusButton = new JButton("+");
		plusButton.addActionListener(event -> addBlock(blockSelect.getSelectedItem().toString()));
		plusButton.setBounds(450 - 65, 80 - 25, 60, 20);
		add(plusButton);

		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			editor.nBlocks += father.removeBlock(this);
			editor.updateUI();
		});
		removeButton.setBounds(5, 80 - 25, 120, 20);
		add(removeButton);
	}

	public void addBlock(String blockName) {
		int upgrade = calculateUpgrade();
		nInstructions++;

		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade + 1) * 80) + 5, depth + 1, listPos + upgrade + 1);
				
				addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade + 1) * 80) + 5, depth + 1, listPos + upgrade + 1);

				addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade + 1) * 80) + 5, depth + 1, listPos + upgrade + 1);

				addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade + 1) * 80) + 5, depth + 1, listPos + upgrade + 1);

				addBlock(INTERACT);
				break;

			default:
				break;
		}
		editor.nBlocks++;
		editor.updateUI();
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
