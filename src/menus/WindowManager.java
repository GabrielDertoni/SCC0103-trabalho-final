package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WindowManager extends JFrame {
	private JPanel contentPane;
	private int lvl;
	private String window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowManager frame = new WindowManager();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
					
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowManager() {
		window = "inicio";
		int x = getBounds().x;
		int y = getBounds().y;
		lvl = 1;
		int width = getBounds().width;
		int height = getBounds().height;
		setBounds(x, y, width, height);
		
		
		contentPane = new JPanel();
		Inicio inicioScreen = new Inicio(x, y, width, height);
		Niveis lvlScreen = new Niveis(x, y, width, height);
		Config configScreen = new Config(x, y, width, height);
		BaseLVL lvlBase = new BaseLVL(x, y, width, height, lvl);
				
		contentPane.add(inicioScreen);
		contentPane.add(configScreen);
		contentPane.add(lvlScreen);
		contentPane.add(lvlBase);
		
		this.addPropertyChangeListener(PropertyChangeListener -> {		
			if(window.equals("inicio")) {
				inicioScreen.setVisible(true);
			}
			else if(window.equals("config")) {
				configScreen.setVisible(true);
			}
			else if(window.equals("lvls")) {
				lvlScreen.setVisible(true);
			}
			else if(window.equals("lvlBase")) {
				lvlBase.setVisible(true);
				lvlBase.setLvl(lvl);
			}
		});
	}
	
	public void setWindow(String window) {
		this.window = window;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
