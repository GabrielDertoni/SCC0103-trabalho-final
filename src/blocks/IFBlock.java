package blocks;

import java.awt.*;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import interpreter.Expr;
import interpreter.Stmt;

public class IFBlock extends BaseBlock {

	String leftHandSideVar = "a Direita";
	String rightHandSideVar = "uma parede";
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;

	public IFBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int depth, int listPos) {
		super(father, editor, posX, posY, 450, 80, BaseBlock.Mode.STATEMENT, listPos, depth, new Color(0x14, 0x2e, 0x1c));
		
		JLabel text = new JLabel("Se ");
		text.setBounds(15, 15, 25, 10);
		text.setForeground(Color.WHITE);
		add(text);

		JComboBox<String> dir = new JComboBox<String>(new String[]{"a Direita", "Cima", "a Esquerda", "Baixo"});
		dir.addActionListener(event -> leftHandSideVar = (String)dir.getSelectedItem());
		dir.setBounds(40, 10, 120, 20);
		add(dir);
				
		text = new JLabel(" for ");
		text.setBounds(160, 15, 30, 10);
		text.setForeground(Color.WHITE);
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
		op.setBounds(190, 10, 120, 20);
		add(op);
		
		JComboBox<String> st = new JComboBox<String>(new String[]{"uma parede", "nada"});
		st.addActionListener(event -> rightHandSideVar = (String)st.getSelectedItem());
		st.setBounds(310, 10, 120, 20);
		add(st);
		
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

		setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    }

    public void addBlock(String blockName) {
		int upgrade = calculateUpgrade() + 1;
		nInstructions++;
		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade) * 80) + 5, depth + 1, listPos + upgrade);
				
				addBlock(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade) * 80) + 5, depth + 1, listPos + upgrade);

				addBlock(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade) * 80) + 5, depth + 1, listPos + upgrade);

				addBlock(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(this, editor, (depth * 30) + 10, ((listPos + upgrade) * 80) + 5, depth + 1, listPos + upgrade);

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
		return new Stmt.If(
				new Expr.Binary(
						new Expr.Variable(leftHandSideVar),
						operator,
						new Expr.Literal(rightHandSideVar)
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
