package menus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import javax.swing.JTextArea;

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
					Config teste = new Config(0, 0, screenSize.width, screenSize.height, frame);
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
	public Config(int x, int y, int width, int height, JFrame parent) {
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
			((WindowManager) parent).setWindow("inicio");
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
