package projFinalPOO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Niveis extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Niveis frame = new Niveis(0, 0, 500, 300);
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
	public Niveis(int x, int y, int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFrame temp = this;
		/* TODO adicionar a imagem de background
		Image myImage = ImageIO.read("");
		JFrame myJFrame = new JFrame("Image pane");
		contentPane.setContentPane(new ImagePanel(myImage));
		*/
		
		// Botoes de voltar ao inicio
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				temp.dispose();
				Inicio inicio = new Inicio(x, y, width, height);
				//inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				inicio.setVisible(true);
			}
		});
		/*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnVoltar.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		//btnVoltar.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		int btnVoltar_w = 120;
		int btnVoltar_h = 25;
		btnVoltar.setBounds(20, 20	, btnVoltar_w, btnVoltar_h);
		contentPane.add(btnVoltar);
		
		// Botoes de seleçao de nivel
		JButton btnNewButton = new JButton("1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.dispose();
				BaseLVL lvl1= new BaseLVL(x, y, width, height, 1);
				lvl1.setVisible(true);
			}
		});
		/*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnNewButton.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		//btnNewButton.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		int btnNewButton_w = 120;
		int btnNewButton_h = 25;
		btnNewButton.setBounds((int) (0.1*width), (int) (2*height/5), btnNewButton_w, btnNewButton_h);
		contentPane.add(btnNewButton);
		
		//TODO adicionar açao de abrir o lvl 2
		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.dispose();
				BaseLVL lvl1= new BaseLVL(x, y, width, height, 2);
				lvl1.setVisible(true);
			}
		});/*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnNewButton_1.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		//btnNewButton_1.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		int btnNewButton_1_w = 120;
		int btnNewButton_1_h = 25;
		btnNewButton_1.setBounds((int) (0.6*width), (int) (2*height/5), btnNewButton_1_w, btnNewButton_1_h);
		contentPane.add(btnNewButton_1);
		
		int label_w = 190;
		int label_h = 20;
		JLabel lblSelecioneONivel = new JLabel("Selecione o Nivel:");
		lblSelecioneONivel.setBounds((int) ((width-label_w)/2), 80, label_w, label_h);
		contentPane.add(lblSelecioneONivel);
		//lblSelecioneONivel.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
	}
}
