package menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import blocks.BlocoArrasta;
import interpreter.PseudocodeGenerator;
import interpreter.Stmt;

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
		BlocoArrasta blocos = new BlocoArrasta(0, 0, 0, 0, Color.GREEN, BlocoArrasta.STATIC);
		blocos.setBounds((int) (width*2/3), 0, (int) (width/3),(int) (height*3/5));
		blocos.setBackground(Color.GREEN);/*
		JPanel blocoTeste = new JPanel();
		blocoTeste.setBounds(width-50, 20, 30, 30);
		blocoTeste.setBackground(Color.CYAN);*/
		
		blocks.IFBlock ifStat = new blocks.IFBlock(blocos);
		blocos.add(ifStat.getBlock());
		//contentPane.add(moveStat.getBlock());
		//contentPane.add(inteStat.getBlock());
		//contentPane.add(ifStat.getBlock());
		//contentPane.add(loopStat.getBlock());
		contentPane.add(blocos);
		
		JButton plusButton = new JButton("+");
		String statements[] = {"Se", "Repete", "Move", "Interagir"};        
		JComboBox<String> stmts = new JComboBox<String>(statements);
		
		int plusButton_w = 80;
		int plusButton_h = 30;
		stmts.setBounds(width-plusButton_w-125, height-250, 90, 30);
		contentPane.add(stmts);
		plusButton.setBounds(width-plusButton_w-30, height-250, plusButton_w, plusButton_h);
		contentPane.add(plusButton);
        
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stmt = stmts.getItemAt(stmts.getSelectedIndex());
				
		        switch(stmt) {
		        	case "Se":
		        		blocks.IFBlock IF = new blocks.IFBlock(blocos);
		        		blocos.add(IF.getBlock());
		        		break;
		        		
		        	case "Repete":
		        		blocks.LOOPBlock LOOP = new blocks.LOOPBlock(blocos);
		        		blocos.add(LOOP.getBlock());
		        		break;
		        		
		        	case "Move":
		        		blocks.MOVEBlock MOVE = new blocks.MOVEBlock(blocos);
		        		blocos.add(MOVE.getBlock());
		        		break;
		        		
		        	case "Interagir":
		        		blocks.INTERACTBlock INTERACT = new blocks.INTERACTBlock(blocos);
		        		blocos.add(INTERACT.getBlock());
		        		break;
		        }
		        
			}
		});
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Stmt> stmts = Arrays.asList(ifStat.getStmt());

		        PseudocodeGenerator gen = new PseudocodeGenerator();
		        System.out.println(gen.fromStmts(stmts));
			}
		});
		int runButton_w = 80;
		int runButton_h = 30;
		runButton.setBounds(width-runButton_w-30, height-200, runButton_w, runButton_h);
		contentPane.add(runButton);

        //Iterator<Void> interpretStream = Interpreter.interpret(stmts).iterator();
		
		
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
