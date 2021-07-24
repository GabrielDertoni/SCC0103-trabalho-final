package blocks;

import java.awt.Color;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Expr;
import interpreter.Stmt;

public class IFBlock extends BaseBlock {

	String leftHandSideVar = "a Direita";
	String rightHandSideVar = "uma parede";
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;

	public IFBlock(BaseBlock father, int posX, int posY, int index) {
		super(posX, posY, 450, 80, Color.YELLOW, BaseBlock.Mode.DRAGGABLE_Y, index, null);
		
		this.posX = posX;
		this.posY = posY;
		this.father = father;
		
		JLabel text = new JLabel("Se ");
		text.setBounds(5, 15, 25, 10);
		add(text);

		JComboBox<String> dir = new JComboBox<String>(new String[]{"a Direita", "Cima", "a Esquerda", "Baixo"});
		dir.addActionListener(event -> leftHandSideVar = (String)dir.getSelectedItem());
		dir.setBounds(30, 10, 120, 20);
		add(dir);
				
		text = new JLabel(" for ");
		text.setBounds(150, 15, 30, 10);
		add(text);
		
		JComboBox<String> op = new JComboBox<String>(new String[]{"igual à", "diferente de"});        
		op.addActionListener(event -> {
			String operatorString = (String)op.getSelectedItem();

			if(operatorString == "igual à") {
				operator = Expr.Binary.Operator.EQUAL;
			}else {
				operator = Expr.Binary.Operator.NOT_EQUAL;
			}
		 });
		op.setBounds(180, 10, 120, 20);
		add(op);
		
		JComboBox<String> st = new JComboBox<String>(new String[]{"uma parede", "nada", "um inimigo"});        
		st.addActionListener(event -> rightHandSideVar = (String)st.getSelectedItem());
		st.setBounds(300, 10, 120, 20);
		add(st);
		
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		blockSelect.setBounds(450 - 90 - 70, 80 - 25, 90, 20);
		add(blockSelect);

		JButton plusButton = new JButton("+");
		plusButton.addActionListener(event -> addBlock(blockSelect.getSelectedItem().toString()));
		plusButton.setBounds(450 - 65, 80 - 25, 60, 20);
		add(plusButton);
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.removeBlock(this);
			father.repaint();
		});
		removeButton.setBounds(5, 80 - 25, 120, 20);
		add(removeButton);
    }

    public void addBlock(String blockName) {
		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, 40, height - 10, nInstructions);
				
				super.addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, 40, height - 10, nInstructions);

				super.addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, 40, height - 10, nInstructions);

				super.addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, 40, height - 10, nInstructions);

				super.addBlock(INTERACT);
				break;
		}
		//setPreferredSize(new Dimension(width, height));
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
