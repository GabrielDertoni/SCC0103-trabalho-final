package menus;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import static game.Resources.whiteRabbit;

public class Inicio extends Background {
	public int width;
	public int height;
	public int x;
	public int y;

	public Inicio(int x, int y, int width, int height) {
		super(new ImageIcon("assets/background.png").getImage());

		setLayout(null);

		int btnW = 500;
		int btnH = 100;

		ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");
		Image resizedBG = btnBg.getImage().getScaledInstance(btnW, btnH, Image.SCALE_SMOOTH);

		//Botoes de mudança de janela
		JButton btnNiveis = new JButton("Niveis", new ImageIcon(resizedBG));


		btnNiveis.setFont(whiteRabbit.deriveFont(30f));
		btnNiveis.setForeground(Color.GREEN.darker());
		btnNiveis.setBounds((width-btnW)/2, 3*height/5-btnH, btnW, btnH);
		btnNiveis.setHorizontalTextPosition(JLabel.CENTER);
		btnNiveis.setVerticalTextPosition(JLabel.CENTER);
		
		btnNiveis.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		
		//Botao de acesso ao tutorial
		JButton btnTutorial = new JButton("Tutorial", new ImageIcon(resizedBG));

		btnTutorial.setFont(whiteRabbit.deriveFont(30f));
		btnTutorial.setForeground(Color.GREEN.darker());
		btnTutorial.setHorizontalTextPosition(JLabel.CENTER);
		btnTutorial.setVerticalTextPosition(JLabel.CENTER);
		
		btnTutorial.setBounds((width-btnW)/2, 3*height/5+btnH, btnW, btnH);
		
		btnTutorial.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Tutorial);
		});
		
		//Texto
		int label_w = 600;
		int label_h = 100;
		
		JLabel lblProjetoFinalPoo = new JLabel("Projeto Final POO");
		lblProjetoFinalPoo.setBounds(((width-label_w)/2), (int) (0.1*height), label_w, label_h);
		lblProjetoFinalPoo.setFont(whiteRabbit.deriveFont(60f));
		lblProjetoFinalPoo.setForeground(Color.GREEN.brighter());
		
		//Adiciona os componentes ao Panel
		add(btnTutorial);
		add(btnNiveis);
		add(lblProjetoFinalPoo);
	}
}



