package blocks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interpreter.PseudocodeGenerator;
import interpreter.Stmt;
import menus.WindowManager;

public class BlockEditor extends JPanel {

	private List<CodeBlock> blocks;
	private BaseBlock editor;

	public BlockEditor(int x, int y, int width, int height) {
		blocks = new ArrayList<CodeBlock>();

		//Area de manuseio dos blocos de programacao
		editor = new BaseBlock((int) (width*2/3), 0, (int) (width/3), (height*3/5), Color.GREEN, BaseBlock.Mode.STATIC);
		add(editor);

		JButton plusButton = new JButton("+");
		JComboBox<String> blockSelect = new JComboBox<String>(new String[]{"Se", "Repete", "Move", "Interagir"});
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		blockSelect.setBounds(width-plusButton_w-125, height-250, 90, 30);
		add(blockSelect);
		plusButton.setBounds(width-plusButton_w-30, height-250, plusButton_w, plusButton_h);
		add(plusButton);

		plusButton.addActionListener(event -> addNewBlock(blockSelect.getSelectedItem().toString()));
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(event -> {
			List<Stmt> stmts = blocks.stream().map(CodeBlock::toStmt).collect(Collectors.toList());
			PseudocodeGenerator gen = new PseudocodeGenerator();
			System.out.println(gen.fromStmts(stmts));
		});

		int runButton_w = 80;
		int runButton_h = 30;
		runButton.setBounds(width-runButton_w-30, height-200, runButton_w, runButton_h);
		add(runButton);
	
	}

	private void addNewBlock(String blockName) {
		switch(blockName) {
			case "Se":
				blocks.IFBlock IF = new blocks.IFBlock(editor);
				editor.add(IF.getDraggablePanel());
				blocks.add(IF);
				break;

			case "Repete":
				blocks.LOOPBlock LOOP = new blocks.LOOPBlock(editor);
				editor.add(LOOP.getDraggablePanel());
				blocks.add(LOOP);
				break;

			case "Move":
				blocks.MOVEBlock MOVE = new blocks.MOVEBlock(editor);
				editor.add(MOVE.getDraggablePanel());
				blocks.add(MOVE);
				break;

			case "Interagir":
				blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(editor);
				editor.add(INTERACT.getDraggablePanel());
				blocks.add(INTERACT);
				break;
		}
		editor.updateUI();
	}
	
}
