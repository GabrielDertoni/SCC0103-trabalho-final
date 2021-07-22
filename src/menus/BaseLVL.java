package menus;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import blocks.BaseBlock;
import blocks.CodeBlock;
import interpreter.PseudocodeGenerator;
import interpreter.Stmt;

public class BaseLVL extends JPanel {

	private List<CodeBlock> blocks;
	private BaseBlock editor;

	public BaseLVL(int x, int y, int width, int height, int lvl) {
		blocks = new ArrayList<CodeBlock>();

		setBounds(x, y, width, height);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//Nao usa um gerenciador de posicionamento
		setLayout(null);
		
		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		JPanel mapa = new JPanel();
		mapa.setBounds(0, 0, (int) (width*2/3)-50, height);
		mapa.setBackground(Color.DARK_GRAY);
		add(mapa);

		//Area de manuseio dos blocos de programacao
		editor = new BaseBlock(0, 0, 0, 0, Color.GREEN, BaseBlock.Mode.STATIC);
		editor.setBounds((int) (width*2/3), 0, (int) (width/3),(int) (height*3/5));
		editor.setBackground(Color.GREEN);
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
		
		//Botao de voltar a tela de selecao de niveis
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Game);
		});
		int btnVoltar_w = 90;
		int btnVoltar_h = 30;
		btnNewButton.setBounds(width-btnVoltar_w-30, height-100, btnVoltar_w, btnVoltar_h);
		add(btnNewButton);
		/*btnNewButton.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		TODO adicionar a aparencia do botao
		try {
			Image imgVoltar = ImageIO.read(getClass().getResource(""));
			btnNewButton.setIcon(new ImageIcon(imgVoltar));
		} catch (IOException e1) {
			;
		}*/
		
		//Botao de recomeçar o nivel
		JButton btnRecomear = new JButton("Recomeçar");
		btnRecomear.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		btnRecomear.setBounds(width-120-30, height-150, 120, 25);
		add(btnRecomear);
		/* TODO adicionar a imagem de background
		Image myImage = ImageIO.read("");
		JFrame myJFrame = new JFrame("Image pane");
		myJFrame.setContentPane(new ImagePanel(myImage));
		*//*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnRecomear.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}
		btnRecomear.setFont(new Font("", Font.PLAIN, 18));*/// TODO mudar a fonte
	
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
