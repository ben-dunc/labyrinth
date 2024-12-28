// Benjamin Duncan
// MazeApp.java

import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MazeApp extends JFrame implements ActionListener, Scene.SceneManager {

	private int tickRate = 50;
	private Timer tickTimer = new Timer(tickRate, this);
	
	private MainMenuScene mainMenu;
	
	public static void main(String [] args) {		
		new MazeApp();
	}
	
	public MazeApp() {
		// init frame
		super("Labrynth, by Ben Duncan");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setMinimumSize(new Dimension(500, 500 + 28));
		super.setVisible(true);
		super.setBackground(Color.black);
		
		// init main menu
		mainMenu = new MainMenuScene(this);

		super.add(mainMenu);

		// tick!
		tickTimer.start();
	}
	
	// tick!
	public void actionPerformed(ActionEvent e) {
		if (mainMenu != null)
			mainMenu.tick(tickRate);
	}

	public void LoadScene(Scene scene) {
		System.out.println("OUT!");
	}
}
