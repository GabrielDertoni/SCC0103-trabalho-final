package blocks;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import interpreter.Expr;
import interpreter.Stmt;

public class LOOPBlock extends BaseBlock {
	
	Expr.Literal rightHandSide = new Expr.Literal(1);
	Stmt body = null;

	public LOOPBlock(BaseBlock father, int posX) {
  	  	super(posX, 5, 450, 60, Color.CYAN, BaseBlock.Mode.DRAGGABLE_Y);

		this.posX = posX;
		this.father = father;

		SpinnerModel value = new SpinnerNumberModel(1, 1, 15, 1);  
        JSpinner spinner = new JSpinner(value);    
        
        spinner.addChangeListener(event -> {
			int right = (int)spinner.getValue();
			rightHandSide = new Expr.Literal(right);
        });
		
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});

		int plusButton_w = 80;
		int plusButton_h = 30;
		JButton plusButton = new JButton("+");
		plusButton.setBounds(220, 200, plusButton_w, plusButton_h);
		plusButton.addActionListener(event -> addBlock(blockSelect.getSelectedItem().toString()));

		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.removeBlock(this);
			father.repaint();
		});
		
        add(new JLabel("Repete "));
        add(spinner);
        add(new JLabel(" vezes"));
		add(blockSelect);
		add(plusButton);
        add(removeButton);
	}

	public void addBlock(String blockName) {
		switch(blockName) {
			case "Se":
				height += 90;

				blocks.IFBlock IF = new blocks.IFBlock(this, posX + 10);
				super.addBlock(IF, 80);
				break;

			case "Repete":
				height += 60;

				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, posX + 10);
				super.addBlock(LOOP, 50);
				break;

			case "Move":
				height += 30;

				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this);
				super.addBlock(MOVE, 30);
				break;

			case "Interagir":
				height += 30;

				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this);
				super.addBlock(INTERACT, 30);
				break;
		}
		//setPreferredSize(new Dimension(width, height));
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.Loop(
				new Expr.Binary(
						new Expr.Variable("i"),
						Expr.Binary.Operator.LESS,
						rightHandSide
				),
				body
		);
	}
}
