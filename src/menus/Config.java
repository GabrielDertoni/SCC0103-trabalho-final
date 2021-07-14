package projFinalPOO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class Config extends JFrame {
	private JPanel contentPane;
	private JTextArea txtrNaoImplementado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config frame = new Config();
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
	public Config() {
		//TODO vai usar isso pra alguma coisa??
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFrame temp = this;
		
		//Botao de fechar o popup de configuraçoes
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.dispose();
			}
		});
		int btn_w = 120;
		int btn_h = 30;
		btnFechar.setBounds(20, 0, btn_w, btn_h);
		contentPane.add(btnFechar);
		/*btnFechar.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnFechar.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}*/
		
		
		JLabel lblProjetoFinalPoo = new JLabel("Nao Implementado");
		int label_w = 130;
		int label_h = 20;
		lblProjetoFinalPoo.setBounds(180, 40, label_w, label_h);
		contentPane.add(lblProjetoFinalPoo);
		//lblProjetoFinalPoo.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
	}
}
