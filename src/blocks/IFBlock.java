package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Expr;
import interpreter.Stmt;

public class IFBlock extends BaseBlock {

	BaseBlock father;
	String leftHandSideVar = "a Direita";
	String rightHandSideVar = "uma parede";
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;

	public IFBlock(BaseBlock father) {
		super(700, 5, 250, 90, Color.YELLOW, BaseBlock.Mode.DRAGGABLE);
		
		this.father = father;
		
		String direction[] = {"a Direita", "Cima", "a Esquerda", "Baixo"};
		JComboBox<String> dir = new JComboBox<String>(direction);
		dir.addActionListener(event -> leftHandSideVar = (String)dir.getSelectedItem());
				
		String operatorBox[] = {"igual à", "diferente de"};        
		JComboBox<String> op = new JComboBox<String>(operatorBox);
		op.addActionListener(event -> {
			String operatorString = (String)op.getSelectedItem();

			if(operatorString == "igual à") {
				operator = Expr.Binary.Operator.EQUAL;
			}else {
				operator = Expr.Binary.Operator.NOT_EQUAL;
			}
		 });
		
		String stats[] = {"uma parede", "nada", "um inimigo"};        
		JComboBox<String> st = new JComboBox<String>(stats);    
		st.addActionListener(event -> rightHandSideVar = (String)st.getSelectedItem());
		
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});

		int plusButton_w = 80;
		int plusButton_h = 30;
		JButton plusButton = new JButton("+");
		plusButton.setBounds(220, 200, plusButton_w, plusButton_h);
		plusButton.addActionListener(event -> addBlock(blockSelect.getSelectedItem().toString()));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.nInstructions--;
			father.altura -= altura;

			father.setPreferredSize(new Dimension(father.largura, father.altura));

			father.removeBlock(this);
			father.repaint();
		});
		
		add(new JLabel("Se "));
		add(dir);
		add(new JLabel("for "));
		add(op);
		add(st);
		add(blockSelect);
		add(plusButton);
		add(removeButton);
    }

    public void addBlock(String blockName) {
		nInstructions++;

		switch(blockName) {
			case "Se":
				altura += 90;

				blocks.IFBlock IF = new blocks.IFBlock(this);
				super.addBlock(IF);
				break;

			case "Repete":
				altura += 60;

				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this);
				super.addBlock(LOOP);
				break;

			case "Move":
				altura += 30;

				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this);
				super.addBlock(MOVE);
				break;

			case "Interagir":
				altura += 30;

				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this);
				super.addBlock(INTERACT);
				break;
		}

		setPreferredSize(new Dimension(largura, altura));
		updateUI();
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.If(
				new Expr.Binary(
						new Expr.Variable(leftHandSideVar),
						operator,
						new Expr.Variable(rightHandSideVar)
				),
				new Stmt.Block(
						blocks.stream()
								.map(BaseBlock::toStmt)
								.collect(Collectors.toList())
				),
				null
		);
	}
}
