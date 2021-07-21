package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		int x = 0;
		int y = 0;
		lvl = 1;
		int width = screenSize.width;
		int height = screenSize.height;
		
		Inicio inicioScreen = new Inicio(x, y, width, height, this);
		Niveis lvlScreen = new Niveis(x, y, width, height, this);
		Config configScreen = new Config(x, y, width, height, this);
		BaseLVL lvlBase = new BaseLVL(x, y, width, height, lvl, this);

		setContentPane(inicioScreen);
		
		this.addPropertyChangeListener(PropertyChangeListener -> {
			if(window.equals("inicio")) {
				setContentPane(inicioScreen);
				System.out.println(window);
			}
			else if(window.equals("config")) {
				setContentPane(configScreen);
				System.out.println(window);
			}
			else if(window.equals("lvls")) {
				setContentPane(lvlScreen);
				System.out.println(window);
			}
			else if(window.equals("lvlBase")) {
				setContentPane(lvlBase);
				lvlBase.setLvl(lvl);
				System.out.println(window + lvl);
			}
		});
	}
	
	public void setWindow(String window) {
		this.window = window;
	}
	
	public String getWindow() {
		return this.window;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public int getLvl() {
		return this.lvl;
	}
	
}
