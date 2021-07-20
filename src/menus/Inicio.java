package menus;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Inicio extends JPanel {
	public int width=1000;
	public int height=700;
	public int x = 0;
	public int y = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio(0, 0, 1000, 700); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio(int x, int y, int width, int height) {
		Container parent = this.getParent();
		setBounds(x, y, width, height);
		
		// Background
		Background bg = new Background(
				new ImageIcon("specification/Nivel.png").getImage());
		
		ImageIcon btnBg = new ImageIcon("specification/Prototipo_fundo_menu.png");
		
		//Botoes de mudança de janela
		JButton btnNiveis = new JButton("Niveis");

		int btnNiveis_w = 120;
		int btnNiveis_h = 25;
		
		btnNiveis.setIcon(btnBg);
		btnNiveis.setFont(new Font("ITALIC", Font.PLAIN, 18));
		btnNiveis.setBounds((width-btnNiveis_w)/2, 3*height/5-20, btnNiveis_w, btnNiveis_h);
		
		btnNiveis.addActionListener(ActionListener -> {
			setVisible(false);
			((WindowManager) parent).setWindow("lvls");
		});
		add(btnNiveis);
		
		//Botao de acesso a tela de configuraçoes
		JButton btnConfig = new JButton("Config");
		
		btnConfig.setIcon(new ImageIcon("specification/Prototipo_fundo_menu.png"));
		btnConfig.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfig.setIcon(btnBg);
		
		int btnConfig_w = 180;
		int btnConfig_h = 25;
		
		btnConfig.setBounds((width-btnConfig_w)/2, 3*height/5+20	, btnConfig_w, btnConfig_h);
		
		btnConfig.addActionListener(ActionListener -> {
			setVisible(false);
			((WindowManager) parent).setWindow("config");
		});
		add(btnConfig);

		//Texto
		int label_w = 130;
		int label_h = 15;
		
		JLabel lblProjetoFinalPoo = new JLabel("Projeto Final POO");
		lblProjetoFinalPoo.setBounds((int) ((width-label_w)/2), (int) (0.1*height), label_w, label_h);
		add(lblProjetoFinalPoo);
		lblProjetoFinalPoo.setFont(new Font("Serif", Font.PLAIN, 18));
	}
}



