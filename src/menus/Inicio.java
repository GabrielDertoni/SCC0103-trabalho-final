package menus;

import java.awt.Color;
import java.awt.Font;

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
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());

		setLayout(null);
		
		int btnW = 350;
		int btnH = 25;
		
		ImageIcon btnBg = new ImageIcon("specification/Fundo_prototipo_menu.jpg");
		
		//Botoes de mudança de janela
		JButton btnNiveis = new JButton("Niveis");
		//JButton btnNiveis = new JButton("Niveis", btnBg);
		
		btnNiveis.setFont(new Font("serif", Font.PLAIN, 18));
		btnNiveis.setBounds((width-btnW)/2, 4*height/5-20, btnW, btnH);
		btnNiveis.setBorder(BorderFactory.createEmptyBorder());
		
		btnNiveis.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		
		//Botao de acesso a tela de configuraçoes
		JButton btnConfig = new JButton("Configuraçoes");
		//JButton btnConfig = new JButton("Configuraçoes", btnBg);
		
		btnConfig.setBorder(BorderFactory.createEmptyBorder());
		btnConfig.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfig.setForeground(Color.BLACK);
		
		btnConfig.setBounds((width-btnW)/2, 4*height/5+20, btnW, btnH);
		
		btnConfig.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		
		//Texto
		int label_w = 200;
		int label_h = 20;
		
		JLabel lblProjetoFinalPoo = new JLabel("Projeto Final POO");
		lblProjetoFinalPoo.setBounds((int) ((width-label_w)/2), (int) (0.1*height), label_w, label_h);
		lblProjetoFinalPoo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblProjetoFinalPoo.setForeground(Color.GREEN);
		
		//Adiciona os componentes ao Panel
		add(btnConfig);
		add(btnNiveis);
		add(lblProjetoFinalPoo);
	}
}



