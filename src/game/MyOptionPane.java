package game;

import menus.Background;

import javax.swing.*;
import java.awt.*;

import static game.Resources.whiteRabbit;

public class MyOptionPane extends JFrame {
	public MyOptionPane(String message, int width, int height){
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Background contentPane = new Background("assets/background.png");
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setVisible(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);

		// Mensagem
		int lblW = width-50;
		int lblH = 20;

		JLabel msg = new JLabel(message);
		msg.setBounds((width-lblW)/2, (height-lblH)/2, lblW, lblH);
		msg.setFont(whiteRabbit.deriveFont(20f));
		msg.setForeground(Color.GREEN.darker());
		msg.setVerticalTextPosition(JLabel.CENTER);
		msg.setHorizontalTextPosition(JLabel.CENTER);
		contentPane.add(msg);

		//Botao de voltar
		int btnW = 80;
		int btnH = 20;

		Image bg =  new ImageIcon("assets/botaoSemTextura.png").getImage().
				getScaledInstance(width, height, Image.SCALE_SMOOTH);
		JButton btnFechar = new JButton("X", new ImageIcon(bg));
		btnFechar.setFont(whiteRabbit.deriveFont(20f));
		btnFechar.setForeground(Color.GREEN.darker());
		btnFechar.setBounds(5, 5, btnW, btnH);
		btnFechar.setVerticalTextPosition(JLabel.CENTER);
		btnFechar.setHorizontalTextPosition(JLabel.CENTER);
		btnFechar.addActionListener(event-> {
			this.dispose();
		});
		contentPane.add(btnFechar);
	}
}