package menus;

import game.Resources;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowManager extends JFrame {

	public enum WindowName {
		MainMenu,
		LevelMenu,
		Config,
		Game,
		Tutorial
	}

	private static WindowManager instance = null;

	private WindowName currentWindow;

	private WindowManager() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Resources.load();

		setCurrentWindow(WindowName.MainMenu);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static WindowManager getInstance() {
		if (instance == null) {
			instance = new WindowManager();
		}
		return instance;
	}
	
	public void setCurrentWindow(WindowName window, Object ...args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = 0;
		int y = 0;
		int width = screenSize.width;
		int height = screenSize.height;

		currentWindow = window;

		switch (window) {
			case MainMenu -> {
				setContentPane(new Inicio(x, y, width, height));
			}
			case LevelMenu -> {
				setContentPane(new Niveis(x, y, width, height));
			}
			case Config -> { //Nao implementado
				setContentPane(new Config(x, y, width, height));
			}
			case Game -> {
				setContentPane(new BaseLVL(x, y, width, height, (int)args[0]));
			}
			case Tutorial -> {
				setContentPane(new Tutorial(x, y, width, height));
			}
		}
		System.out.println(currentWindow);

		revalidate();
	}
	
	public WindowName getCurrentWindow() {
		return this.currentWindow;
	}
}
