package menus;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import blocks.BlockEditor;

public class BaseLVL extends JPanel {

	private BlockEditor editor;

	public BaseLVL(int x, int y, int width, int height, int lvl) {
		setBounds(x, y, width, height);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//Nao usa um gerenciador de posicionamento
		setLayout(null);
		
		//Painel onde e mostrado o nivel do mapa atual e o personagem e sua posicao no mapa
		JPanel mapa = new JPanel();
		mapa.setBounds(0, 0, (int) (width*2/3)-50, height);
		mapa.setBackground(Color.DARK_GRAY);
		add(mapa);

		//Area de manuseio dos blocos de programacao
		editor = new BlockEditor((int) (width*2/3), 0, (int) (width/3), (height*3/5));
		add(editor);

		
		//Botao de voltar a tela de selecao de niveis
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.Game);
		});
		int btnVoltar_w = 90;
		int btnVoltar_h = 30;
		btnNewButton.setBounds(width-btnVoltar_w-30, height-100, btnVoltar_w, btnVoltar_h);
		add(btnNewButton);
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
		btnRecomear.addActionListener(event -> {
			WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.LevelMenu);
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
	
}
