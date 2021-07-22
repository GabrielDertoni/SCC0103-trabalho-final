package menus;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowManager extends JFrame {

	public enum WindowName {
		LevelMenu,
		MainMenu,
		Config,
		Game,
	}

	private static WindowManager instance = null;

	private JPanel contentPane;
	private int lvl;
	private WindowName currentWindow;

	private Inicio inicioScreen;
	private Niveis lvlScreen;
	private Config configScreen;
	private BaseLVL lvlBase;

	private WindowManager() {
		currentWindow = WindowName.MainMenu;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		int x = 0;
		int y = 0;
		lvl = 1;
		int width = screenSize.width;
		int height = screenSize.height;

		inicioScreen = new Inicio(x, y, width, height);
		lvlScreen = new Niveis(x, y, width, height);
		configScreen = new Config(x, y, width, height);
		lvlBase = new BaseLVL(x, y, width, height, lvl);
		setContentPane(inicioScreen);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static WindowManager getInstance() {
		if (instance == null) {
			instance = new WindowManager();
		}
		return instance;
	}
	
	public void setCurrentWindow(WindowName window) {
		currentWindow = window;
		switch (window) {
			case LevelMenu -> {
				setContentPane(lvlScreen);
			}
			case MainMenu -> {
				setContentPane(inicioScreen);
			}
			case Config -> {
				setContentPane(configScreen);
			}
			case Game -> {
				setContentPane(lvlBase);
				// lvlBase.setLvl(lvl);
			}
		}
		System.out.println(currentWindow);
		revalidate();
	}
	
	public WindowName getCurrentWindow() {
		return this.currentWindow;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public int getLvl() {
		return this.lvl;
	}
	
}
