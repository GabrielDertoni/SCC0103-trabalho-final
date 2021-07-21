package menus;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inicio extends Background {
	public int width;
	public int height;
	public int x;
	public int y;
	
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					JFrame frame = new JFrame();
					Inicio teste = new Inicio(0, 0, screenSize.width, screenSize.height, frame);
					frame.setContentPane(teste);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
					
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public Inicio(int x, int y, int width, int height, WindowManager parent) {
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());

/*		System.out.println(parent);
		System.out.println(parent.getWindow());
		parent.setWindow("lvls");*/
		System.out.println("inicio "+parent.getWindow());
		
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
		
		btnNiveis.addActionListener(ActionListener -> {
			parent.setWindow("lvls");
		});
		
		//Botao de acesso a tela de configuraçoes
		JButton btnConfig = new JButton("Configuraçoes");
		//JButton btnConfig = new JButton("Configuraçoes", btnBg);
		
		btnConfig.setBorder(BorderFactory.createEmptyBorder());
		btnConfig.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfig.setForeground(Color.BLACK);
		
		btnConfig.setBounds((width-btnW)/2, 4*height/5+20, btnW, btnH);
		
		btnConfig.addActionListener(ActionListener -> {
			parent.setWindow("config");
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



