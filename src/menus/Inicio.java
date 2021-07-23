package menus;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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
		JButton btnNiveis = new JButton("Níveis", new ImageIcon(resizedBG));
		
		btnNiveis.setFont(new Font("serif", Font.BOLD, 30));
		btnNiveis.setForeground(Color.GREEN.darker());
		btnNiveis.setBounds((width-btnW)/2, 3*height/5-btnH, btnW, btnH);
		btnNiveis.setBorder(BorderFactory.createEmptyBorder());
		btnNiveis.setHorizontalTextPosition(JLabel.CENTER);
		btnNiveis.setVerticalTextPosition(JLabel.CENTER);
		
		btnNiveis.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		
		//Botao de acesso a tela de configuraçoes
		JButton btnTutorial = new JButton("Tutorial", new ImageIcon(resizedBG));
		
		btnTutorial.setBorder(BorderFactory.createEmptyBorder());
		btnTutorial.setFont(new Font("Serif", Font.BOLD, 30));
		btnTutorial.setForeground(Color.GREEN.darker());
		btnTutorial.setHorizontalTextPosition(JLabel.CENTER);
		btnTutorial.setVerticalTextPosition(JLabel.CENTER);
		
		btnTutorial.setBounds((width-btnW)/2, 3*height/5+btnH, btnW, btnH);
		
		btnTutorial.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		
		//Texto
		int label_w = 500;
		int label_h = 100;
		
		JLabel lblProjetoFinalPoo = new JLabel("Projeto Final POO");
		lblProjetoFinalPoo.setBounds(((width-label_w)/2), (int) (0.1*height), label_w, label_h);
		lblProjetoFinalPoo.setFont(new Font("customFont", Font.PLAIN, 60));
		lblProjetoFinalPoo.setForeground(Color.GREEN.brighter());
		
		//Adiciona os componentes ao Panel
		add(btnTutorial);
		add(btnNiveis);
		add(lblProjetoFinalPoo);
	}
}



