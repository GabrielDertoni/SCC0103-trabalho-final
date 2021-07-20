package menus;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class BaseLVL extends JPanel{
	private int lvl;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseLVL frame = new BaseLVL(0, 0, 1000, 700, 1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public BaseLVL(int x, int y, int width, int height, int lvl) {
		setBounds(x, y, width, height);
		Container parent = this.getParent();
		
		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		JPanel mapa = new JPanel();
		mapa.setBounds(0, 0, (int) (width*2/3)-50, height);
		mapa.setBackground(Color.DARK_GRAY);
		Background mapaBg = new Background(
				new ImageIcon("specification/Nivel.png").getImage());
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
		
		//Botao de voltar a tela de selecao de niveis
		JButton btnVoltar = new JButton("Voltar");
		
		int btnVoltar_w = 90;
		int btnVoltar_h = 30;
		
		btnVoltar.setBounds(width-btnVoltar_w-30, height-100, btnVoltar_w, btnVoltar_h);
		
		btnVoltar.addActionListener(ActionListener -> {
			setVisible(false);
			mapa.setVisible(false);
		});
		add(btnVoltar);
		/*btnVoltar.setFont(new Font("", Font.PLAIN, 18));// TODO mudar a fonte
		TODO adicionar a aparencia do botao
		try {
			Image imgVoltar = ImageIO.read(getClass().getResource(""));
			btnVoltar.setIcon(new ImageIcon(imgVoltar));
		} catch (IOException e1) {
			;
		}*/
		
		//Botao de recomeçar o nivel
		JButton btnRecomear = new JButton("Recomeçar");
		btnRecomear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BaseLVL lvl1 = new BaseLVL(x, y, width, height, lvl);
				//setExtendedState(JFrame.MAXIMIZED_BOTH); 
				lvl1.setVisible(true);
			}
		});
		btnRecomear.setBounds(width-120-30, height-150, 120, 25);
		add(btnRecomear);
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
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
