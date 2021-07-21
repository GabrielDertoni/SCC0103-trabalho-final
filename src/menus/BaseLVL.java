package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BaseLVL extends Background{
	private int lvl;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					JFrame frame = new JFrame();
					BaseLVL teste = new BaseLVL(0, 0, screenSize.width, screenSize.height, 1);
					frame.setContentPane(teste);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public BaseLVL(int x, int y, int width, int height, int lvl) {
		super(new ImageIcon("specification/Prototipo_fundo_menu.png").getImage());
		
		setLayout(null);

		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		JPanel mapa = new JPanel();
		mapa.setBounds(0, 0, (int) (width*2/3)-50, height);
		mapa.setBackground(Color.DARK_GRAY);
		Background mapaBg = new Background(
				new ImageIcon("specification/Nivel.png").getImage());
		mapa.add(mapaBg);
		
		add(mapa);
		
		//Area de manuseio dos blocos de programacao
		JPanel blocos = new JPanel();
		blocos.setBounds((int) (width*2/3), 0, (int) (width/3),(int) (height*3/5));
		blocos.setBackground(Color.GREEN);
		BlocoArrasta blocoTeste = new BlocoArrasta();
		blocoTeste.setBounds(100, 100, 50, 50);
		blocoTeste.setSize(100, 100);
		blocoTeste.setBackground(Color.CYAN);
		blocos.add(blocoTeste);
		add(blocos);
		
		ImageIcon btnBg = new ImageIcon("specification/Fundo_prototipo_menu.png");
		
		//Botao de voltar a tela de selecao de niveis
		JButton btnVoltar = new JButton("Voltar");
		
		int btnVoltar_w = 90;
		int btnVoltar_h = 30;
		
		btnVoltar.setIcon(btnBg);
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnVoltar.setBounds(width-btnVoltar_w-30, height-100, btnVoltar_w, btnVoltar_h);
		
		btnVoltar.addActionListener(ActionListener -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
		});
		add(btnVoltar);
		
		//Botao de recomeçar o nivel
		JButton btnRecomear = new JButton("Recomeçar");

		btnRecomear.setBounds(width-120-30, height-150, 120, 25);
		btnRecomear.setFont(new Font("Arial", Font.PLAIN, 18));	

		btnRecomear.addActionListener(ActionListener -> {
			/*recomeçar ao mapa e os blocos*/
		});
		
		add(btnRecomear);
	
		//Botao de executar o codigo definido nos blocos
		JButton btnExecutar = new JButton("Executar");
		
		int btnExecutar_w = 90;
		int btnExecutar_h = 30;
		
		btnExecutar.setIcon(btnBg);
		btnExecutar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnExecutar.setBounds(width-btnExecutar_w-30, height-100, btnExecutar_w, btnExecutar_h);
		
		btnExecutar.addActionListener(ActionListener -> {
			/*Executar o codigo nos blocos*/
		});
		add(btnExecutar);
		
		//Botao de ver o pseudocodigo gerado pelos blocos
		JButton btnVer = new JButton("Ver PseudoCodigo");
		
		int btnVer_w = 90;
		int btnVer_h = 30;
		
		btnVer.setIcon(btnBg);
		btnVer.setFont(new Font("Arial", Font.PLAIN, 18));
		btnVer.setBounds(width-btnVer_w-30, height-100, btnVer_w, btnVer_h);
		
		btnVer.addActionListener(ActionListener -> {
			/*mostrar o pseudocodigo gerado pelos blocos*/
		});
		add(btnVer);
		
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
