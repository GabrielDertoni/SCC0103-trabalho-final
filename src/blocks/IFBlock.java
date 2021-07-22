package blocks;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import interpreter.Expr;
import interpreter.Stmt;

public class IFBlock implements CodeBlock {

	BaseBlock father;
	BaseBlock block;
	String leftHandSideVar = "a Direita";
	String rightHandSideVar = "uma parede";
	Expr.Binary.Operator operator = Expr.Binary.Operator.EQUAL;
	Stmt thenBranch = null, elseBranch = null;
	
	public IFBlock(BaseBlock father) {
		
		this.father = father;
		
		block = new BaseBlock(700, 5, 250, 90, Color.YELLOW, BaseBlock.Mode.DRAGGABLE);
		    
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
		
		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		plusButton.setBounds(220, 200, plusButton_w, plusButton_h);
        
		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(event -> {
			father.nInstructions--;
			father.altura -= block.altura;

			father.setPreferredSize(new Dimension(father.largura, father.altura));

			father.remove(block);
			father.repaint();
		});
		
		block.add(new JLabel("Se "));
		block.add(dir);
		block.add(new JLabel("for "));
		block.add(op);
		block.add(st);
		block.add(blockSelect);
		block.add(plusButton);
		block.add(removeButton);
    }

    private void addNewBlock(String blockName) {
		block.nInstructions++;

		switch(blockName) {
			case "Se":
				block.altura += 90;

				blocks.IFBlock IF = new blocks.IFBlock(block);
				block.add(IF.getDraggablePanel());
				break;

			case "Repete":
				block.altura += 60;

				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(block);
				block.add(LOOP.getDraggablePanel());
				break;

			case "Move":
				block.altura += 30;

				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(block);
				block.add(MOVE.getDraggablePanel());
				break;

			case "Interagir":
				block.altura += 30;

				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(block);
				block.add(INTERACT.getDraggablePanel());
				break;
		}

		block.setPreferredSize(new Dimension(block.largura, block.altura));
		block.updateUI();
	}

    @Override
	public BaseBlock getDraggablePanel() {
		return block;
	}

	@Override
	public Stmt toStmt() {
		return new Stmt.If(
				new Expr.Binary(
						new Expr.Variable(leftHandSideVar),
						operator,
						new Expr.Variable(rightHandSideVar)
				),
				thenBranch,
				elseBranch
		);
	}
}
