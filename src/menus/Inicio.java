package menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Inicio extends JFrame {
	private JPanel contentPane;
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
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
	public Inicio(int x, int y, int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		JFrame temp = this;
		
		// Background
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		/* TODO adicionar a imagem de background
		Image myImage = ImageIO.read("");
		JFrame myJFrame = new JFrame("Image pane");
		contentPane.setContentPane(new ImagePanel(myImage));
		*/
		
		//Botoes de mudança de janela
		JButton btnNiveis = new JButton("Niveis");
		btnNiveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				temp.dispose();
				Niveis lvl = new Niveis(x, y, width, height);
				//lvl.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				lvl.setVisible(true);
			}
		});
		/*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnNiveis.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		//btnNiveis.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		int btnNiveis_w = 120;
		int btnNiveis_h = 25;
		btnNiveis.setBounds((width-btnNiveis_w)/2, 3*height/5-20, btnNiveis_w, btnNiveis_h);
		panel.add(btnNiveis);
		
		JButton btnConfiguraoes = new JButton("Configuraçoes");
		btnConfiguraoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Config config_screen = new Config();
				config_screen.setVisible(true);
			}
		});
		/* TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnConfiguraoes.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		//btnConfiguraoes.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		int btnConfig_w = 180;
		int btnConfig_h = 25;
		btnConfiguraoes.setBounds((width-btnConfig_w)/2, 3*height/5+20	, btnConfig_w, btnConfig_h);
		panel.add(btnConfiguraoes);
		
		int label_w = 130;
		int label_h = 15;
		JLabel lblProjetoFinalPoo = new JLabel("Projeto Final POO");
		lblProjetoFinalPoo.setBounds((int) ((width-label_w)/2), (int) (0.1*height), label_w, label_h);
		panel.add(lblProjetoFinalPoo);
		//lblProjetoFinalPoo.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
	}
}



