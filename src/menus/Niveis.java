package menus;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

public class Niveis extends Background {
	/**
	 * Create the frame.
	 */
	public Niveis(int x, int y, int width, int height) {
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());
		
		setLayout(null);

		Font whiteRabbit = null;
		try {
			whiteRabbit = Font.createFont(Font.TRUETYPE_FONT, new File("assets/WHITRABT.TTF"));
		} catch (FontFormatException| IOException e) {
			e.printStackTrace();
		}

		ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");

		// Botoes de voltar ao inicio

		int btnVoltar_w = 200;
		int btnVoltar_h = 40;

		Image resizedImage = btnBg.getImage().getScaledInstance(btnVoltar_w, btnVoltar_h, Image.SCALE_SMOOTH);
		JButton btnVoltar = new JButton("Voltar", new ImageIcon(resizedImage));

		btnVoltar.setBounds(20, 20, btnVoltar_w, btnVoltar_h);

		btnVoltar.setFont(whiteRabbit.deriveFont(25f));
		btnVoltar.setForeground(Color.GREEN.darker());
		btnVoltar.setHorizontalTextPosition(JLabel.CENTER);
		btnVoltar.setVerticalTextPosition(JLabel.CENTER);
		
		btnVoltar.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu);
		});
		add(btnVoltar);
		
		// Botao de acesso ao lvl1

		int btnLvl_w = 500;
		int btnLvl_h = 100;

		resizedImage = btnBg.getImage().getScaledInstance(btnLvl_w, btnLvl_h, Image.SCALE_SMOOTH);
		JButton btnLvl1 = new JButton("1", new ImageIcon(resizedImage));

		btnLvl1.setBounds((int) (0.1*width), (3*height/5), btnLvl_w, btnLvl_h);

		btnLvl1.setForeground(Color.GREEN.darker());
		btnLvl1.setFont(whiteRabbit.deriveFont(30f));
		btnLvl1.setHorizontalTextPosition(JLabel.CENTER);
		btnLvl1.setVerticalTextPosition(JLabel.CENTER);
		
		btnLvl1.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Game);
			WindowManager.getInstance().setLvl(1);
		});
		add(btnLvl1);
		
		//Botao de acesso ao lvl2
		JButton btnLvl2 = new JButton("2", new ImageIcon(resizedImage));

		btnLvl2.setBounds((int) (0.6*width), (3*height/5), btnLvl_w, btnLvl_h);

		btnLvl2.setFont(whiteRabbit.deriveFont(30f));
		btnLvl2.setForeground(Color.GREEN.darker());
		btnLvl2.setHorizontalTextPosition(JLabel.CENTER);
		btnLvl2.setVerticalTextPosition(JLabel.CENTER);
		
		btnLvl2.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Game);
			WindowManager.getInstance().setLvl(2);
		});
		
		add(btnLvl2);
		
		//Texto
		int label_w = 700;
		int label_h = 100;
		
		JLabel lblLvlSelection = new JLabel("Selecione o Nivel:");
		lblLvlSelection.setBounds(((width-label_w)/2), label_h*2, label_w, label_h);
		lblLvlSelection.setForeground(Color.GREEN.darker());
		lblLvlSelection.setFont(whiteRabbit.deriveFont(60f));
		add(lblLvlSelection);
	}
}
