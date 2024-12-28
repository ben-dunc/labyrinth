// Benjamin Duncan
// MazeApp.java

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class MazeApp extends JFrame implements ActionListener, Scene.SceneManager, KeyListener, InputManager {

	private int tickRate = 50;
	private Timer tickTimer = new Timer(tickRate, this);
	private Scene currentScene;

	private ArrayList<Integer> onPressed = new ArrayList<Integer>();
	private ArrayList<Integer> pressed = new ArrayList<Integer>();

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

		// add key listener
		addKeyListener(this);

		// tick!
		tickTimer.start();
		super.setVisible(true);
	}

	// tick!
	@Override
	public void actionPerformed(ActionEvent e) {
		tick();
	}

	private void tick() {
		// tick!
		if (currentScene != null)
			currentScene.tick(tickRate, this);

		if (getKeyOnPressed(KeyEvent.VK_RIGHT))
			System.out.println("ON pressed right!");
		if (getKeyPressed(KeyEvent.VK_RIGHT))
			System.out.println("Pressed right!");

		onPressed.clear();
	}

	@Override
	public void LoadScene(Scene scene) {
		if (currentScene != null) {
			super.remove(currentScene);
			System.out.println("Removed old scene!");
		}
		currentScene = scene;
		super.add(currentScene);
		super.repaint();
		super.revalidate();
		System.out.println("Loading new scene!");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		onPressed.add(keyCode);
		pressed.add(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (onPressed.contains(keyCode))
			onPressed.remove(Integer.valueOf(keyCode));
		pressed.remove(Integer.valueOf(keyCode));
	}

	@Override
	public boolean getKeyOnPressed(int keycode) {
		return onPressed.contains(keycode);
	}

	@Override
	public boolean getKeyPressed(int keycode) {
		return pressed.contains(keycode);
	}
}
