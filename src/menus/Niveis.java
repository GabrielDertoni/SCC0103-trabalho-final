package menus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Niveis extends Background {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					JFrame frame = new JFrame();
					Niveis teste = new Niveis(0, 0, screenSize.width, screenSize.height, frame);
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
	public Niveis(int x, int y, int width, int height, JFrame parent) {
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());
		
		setLayout(null);
		
		ImageIcon btnBg = new ImageIcon("specification/Fundo_prototipo_menu.jpg");
		
		// Botoes de voltar ao inicio
		JButton btnVoltar = new JButton("Voltar");
		
		int btnVoltar_w = 120;
		int btnVoltar_h = 25;
		
		btnVoltar.setFont(new Font("Serif", Font.PLAIN, 18));// TODO mudar a fonte
		btnVoltar.setIcon(btnBg);
		btnVoltar.setBounds(20, 20	, btnVoltar_w, btnVoltar_h);
		btnVoltar.setBorder(BorderFactory.createEmptyBorder());
		
		btnVoltar.addActionListener(ActionListener -> {
			((WindowManager) parent).setWindow("inicio");
		});
		add(btnVoltar);
		
		// Botao de acesso ao lvl1
		JButton btnLvl1 = new JButton("1");
		
		int btnLvl1_w = 120;
		int btnLvl1_h = 25;
		
		btnLvl1.setBounds((int) (0.1*width), (int) (2*height/5), btnLvl1_w, btnLvl1_h);
		btnLvl1.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLvl1.setIcon(btnBg);
		btnLvl1.setBorder(BorderFactory.createEmptyBorder());
		
		btnLvl1.addActionListener(ActionListener -> {
			((WindowManager) parent).setWindow("lvlBase");
			((WindowManager) parent).setLvl(1);
		});
		add(btnLvl1);
		
		//Botao de acesso ao lvl2
		JButton btnLvl2 = new JButton("2");
		
		int btnLvl2_w = 120;
		int btnLvl2_h = 25;
		
		btnLvl2.setBounds((int) (0.6*width), (int) (2*height/5), btnLvl2_w, btnLvl2_h);
		btnLvl2.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLvl2.setIcon(btnBg);
		btnLvl2.setBorder(BorderFactory.createEmptyBorder());
		
		btnLvl2.addActionListener(ActionListener -> {
			((WindowManager) parent).setWindow("lvlBase");
			((WindowManager) parent).setLvl(2);
		});
		
		add(btnLvl2);
		
		//Texto
		int label_w = 190;
		int label_h = 20;
		
		JLabel lblLvlSelection = new JLabel("Selecione o Nivel:");
		lblLvlSelection.setBounds((int) ((width-label_w)/2), 80, label_w, label_h);
		add(lblLvlSelection);
		lblLvlSelection.setFont(new Font("Arial", Font.PLAIN, 18));// TODO mudar a fonte
	}
}
