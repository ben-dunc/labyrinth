// Benjamin Duncan
// MazeApp.java

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class MazeApp extends JFrame implements ActionListener, Scene.SceneManager, KeyListener {

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

		// add bindings
		// JComponent contentJComponent = ((JComponent) super.getContentPane());
		// contentJComponent.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW)
		// .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
		// contentJComponent.getActionMap().put("up", new KeyPressedEvent("up"));
		addKeyListener(this);

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
		if (currentScene != null) {
			super.remove(currentScene);
			System.out.println("Removed old scene!");
		}
		currentScene = scene;
		System.out.println("Loading new scene!");
	}

	// private void RegisterKeyPresedEvent(JComponent jComponent, String keycode) {

	// }

	// class KeyPressedEvent extends AbstractAction {
	// private String keyCode = "";
	// private boolean pressed = false;

	// public KeyPressedEvent(String keyCode) {
	// super();
	// this.keyCode = keyCode;
	// }

	// public void actionPerformed(ActionEvent e) {

	// }

	// public String getKeyCode() {
	// return keyCode;
	// }

	// public void isPressed() {

	// }
	// }

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("KeyTyped!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyPressed!");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyReleased!");
	}
}
