package menus;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Config extends Background {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					JFrame frame = new JFrame();
					Config teste = new Config(0, 0, screenSize.width, screenSize.height);
					frame.setContentPane(teste);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Config(int x, int y, int width, int height) {
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());

		setLayout(null);
		
		ImageIcon btnBg = new ImageIcon("specification/Prototipo_fundo_menu.png");
		
		//Botao de fechar o popup de configuraçoes
		JButton btnFechar = new JButton("Fechar");
		
		int btn_w = 120;
		int btn_h = 30;
		
		btnFechar.setBounds(20, 0, btn_w, btn_h);
		btnFechar.setIcon(btnBg);
		btnFechar.setFont(new Font("Serif", Font.BOLD, 18));
		
		btnFechar.addActionListener(ActionListener -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu);
		});
		
		add(btnFechar);		
		
		JLabel lvlTxt1 = new JLabel("Nao Implementado");
		int label_w = 130;
		int label_h = 20;
		lvlTxt1.setBounds(180, 40, label_w, label_h);
		add(lvlTxt1);
		lvlTxt1.setFont(new Font("Arial", Font.PLAIN, 18));
	}
}
