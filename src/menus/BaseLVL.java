package menus;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import blocks.BaseBlock;
import blocks.BlockEditor;
import game.GameManager;
import interpreter.PseudocodeGenerator;
import interpreter.Stmt;

public class BaseLVL extends Background {

	private BlockEditor editor;
	private LevelArea levelArea;

	public BaseLVL(int x, int y, int width, int height, int lvl) {
		super("assets/background.png");

		setBounds(x, y, width, height);
		setLayout(null);
		
		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		levelArea = new LevelArea(0, 0,  (width*2/3)-50, height);
		levelArea.setBackground(Color.DARK_GRAY);
		add(levelArea);

		//Area de manuseio dos blocos de programacao
		editor = new BlockEditor((width*2/3), 0, (width/3), (height*3/5));
		add(editor);

		ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");

		//Botao de voltar a tela de selecao de niveis
		int btn_w = 150;
		int btn_h = 30;

		Image resizedImage = btnBg.getImage().getScaledInstance(btn_w, btn_h, Image.SCALE_SMOOTH);

		JButton btnVoltar = new JButton("Voltar", new ImageIcon(resizedImage));
		btnVoltar.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		btnVoltar.setBounds(width-btn_w-30, height-100, btn_w, btn_h);
		btnVoltar.setFont(new Font("serif", Font.BOLD, 20));
		btnVoltar.setForeground(Color.GREEN.darker());
		btnVoltar.setVerticalTextPosition(JLabel.CENTER);
		btnVoltar.setHorizontalTextPosition(JLabel.CENTER);
		add(btnVoltar);

		//Botao de recomeçar o nivel
		JButton btnRestart = new JButton("Recomeçar", new ImageIcon(resizedImage));
		btnRestart.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Game);
		});
		btnRestart.setBounds(width-btn_w-30, height-150, btn_w, btn_h);
		btnRestart.setFont(new Font("serif", Font.BOLD, 20));
		btnRestart.setForeground(Color.GREEN.darker());
		btnRestart.setVerticalTextPosition(JLabel.CENTER);
		btnRestart.setHorizontalTextPosition(JLabel.CENTER);
		add(btnRestart);

		//Botao de rodar o codigo
		JButton runButton = new JButton("Run", new ImageIcon(resizedImage));

		runButton.addActionListener(event -> {
			Stmt.Block block = (Stmt.Block)editor.toStmt();
			PseudocodeGenerator gen = new PseudocodeGenerator();
			System.out.println(gen.fromStmts(block.stmts));
			levelArea.runInterpreter(block.stmts);
		});
		runButton.setFont(new Font("serif", Font.BOLD, 20));
		runButton.setForeground(Color.GREEN.darker());
		runButton.setHorizontalTextPosition(JLabel.CENTER);
		runButton.setVerticalTextPosition(JLabel.CENTER);
		runButton.setBounds(width-btn_w-30, height-200, btn_w, btn_h);
		add(runButton);
	}
	
}
