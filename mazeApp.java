// Benjamin Duncan
// MazeApp.java

import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MazeApp extends JFrame implements ActionListener, Scene.SceneManager {

	private int tickRate = 50;
	private Timer tickTimer = new Timer(tickRate, this);

	private Scene currentScene;

	public static void main(String[] args) {
		new MazeApp();
	}

	public MazeApp() {
		// init frame
		super("Labrynth, by Ben Duncan");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setMinimumSize(new Dimension(500, 500 + 28));
		super.setBackground(Color.black);

		// init main menu
		currentScene = new MainMenuScene(this);

		super.add(currentScene);

		// tick!
		tickTimer.start();
		super.setVisible(true);
	}

	// tick!
	public void actionPerformed(ActionEvent e) {
		if (currentScene != null)
			currentScene.tick(tickRate);
	}

	public void LoadScene(Scene scene) {
		if (currentScene != null)
			System.out.println("Loading Scene!");
	}
}
