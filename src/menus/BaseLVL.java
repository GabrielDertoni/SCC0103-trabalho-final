package menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BaseLVL extends JFrame{
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseLVL frame = new BaseLVL(0, 0, 1000, 700, 1);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setUndecorated(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public BaseLVL(int x, int y, int width, int height, int lvl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		JFrame temp = this;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Nao usa um gerenciador de posicionamento
		contentPane.setLayout(null);
		
		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		JPanel mapa = new JPanel();
		mapa.setBounds(0, 0, (int) (width*2/3)-50, height);
		mapa.setBackground(Color.DARK_GRAY);
		contentPane.add(mapa);
		
		//Area de manuseio dos blocos de programacao
		JPanel blocos = new JPanel();
		blocos.setBounds((int) (width*2/3), 0, (int) (width/3),(int) (height*3/5));
		blocos.setBackground(Color.GREEN);/*
		JPanel blocoTeste = new JPanel();
		blocoTeste.setBounds(width-50, 20, 30, 30);
		blocoTeste.setBackground(Color.CYAN);*/
		BlocoArrasta blocoTeste = new BlocoArrasta();
		//blocoTeste.setBounds(width-50, 20, 30, 30);
		blocoTeste.setBounds(100, 100, 50, 50);
		blocoTeste.setSize(100, 100);
		blocoTeste.setBackground(Color.CYAN);
		blocos.add(blocoTeste);
		contentPane.add(blocos);
		System.out.println(blocoTeste.getRootPane());
		System.out.println(contentPane.toString());
		System.out.println(blocoTeste.toString());
		
		
		//Botao de voltar a tela de selecao de niveis
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				temp.dispose();
				Niveis lvl_screen = new Niveis(x, y, width, height);
				//lvl_screen.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				lvl_screen.setVisible(true);
			}
		});
		int btnVoltar_w = 90;
		int btnVoltar_h = 30;
		btnNewButton.setBounds(width-btnVoltar_w-30, height-100, btnVoltar_w, btnVoltar_h);
		contentPane.add(btnNewButton);
		/*btnNewButton.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		TODO adicionar a aparencia do botao
		try {
			Image imgVoltar = ImageIO.read(getClass().getResource(""));
			btnNewButton.setIcon(new ImageIcon(imgVoltar));
		} catch (IOException e1) {
			;
		}*/
		
		//Botao de recomeçar o nivel
		JButton btnRecomear = new JButton("Recomeçar");
		btnRecomear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.dispose();
				BaseLVL lvl1 = new BaseLVL(x, y, width, height, lvl);
				//setExtendedState(JFrame.MAXIMIZED_BOTH); 
				lvl1.setVisible(true);
			}
		});
		btnRecomear.setBounds(width-120-30, height-150, 120, 25);
		contentPane.add(btnRecomear);
		/* TODO adicionar a imagem de background
		Image myImage = ImageIO.read("");
		JFrame myJFrame = new JFrame("Image pane");
		myJFrame.setContentPane(new ImagePanel(myImage));
		*//*TODO adicionar a aparencia do botao
		try {
			Image img = ImageIO.read(getClass().getResource(""));
			btnRecomear.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			;
		}
		btnRecomear.setFont(new Font("", Font.PLAIN, 18));*/// TODO mudar a fonte
	
	}
	
}
