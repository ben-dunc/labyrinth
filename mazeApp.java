// Benjamin Duncan
// MazeApp.java

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class MazeApp extends JFrame implements ActionListener, Scene.SceneManager, InputManager {

	private int tickRate = 50;
	private Timer tickTimer = new Timer(tickRate, this);
	private Scene currentScene;

	private ArrayList<String> onPressed = new ArrayList<String>();
	private ArrayList<String> pressed = new ArrayList<String>();

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

		registerKeyEvent("RIGHT");
		registerKeyEvent("DOWN");
		registerKeyEvent("LEFT");
		registerKeyEvent("UP");

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

		onPressed.clear();
	}

	@Override
	public void LoadScene(Scene scene) {
		if (currentScene != null) {
			super.remove(currentScene);
		}
		currentScene = scene;
		super.add(currentScene);
		super.repaint();
		super.revalidate();
	}

	private void registerKeyEvent(String key) {
		JComponent jComponent = (JComponent) super.getContentPane();

		InputMap im = jComponent.getInputMap(JComponent.WHEN_FOCUSED);
		ActionMap am = jComponent.getActionMap();

		// pressed
		im.put(KeyStroke.getKeyStroke(key), key);
		am.put(key, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyPressed(key);
			}
		});

		// released
		String releasedKeyText = "released " + key;
		im.put(KeyStroke.getKeyStroke(releasedKeyText), releasedKeyText);
		am.put(releasedKeyText, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyReleased(key);
			}
		});
	}

	public void keyPressed(String keyCode) {
		if (!onPressed.contains(keyCode))
			onPressed.add(keyCode);
		if (!pressed.contains(keyCode))
			pressed.add(keyCode);
	}

	public void keyReleased(String keyCode) {
		onPressed.remove(keyCode);
		pressed.remove(keyCode);
	}

	@Override
	public boolean getKeyOnPressed(String keycode) {
		return onPressed.contains(keycode);
	}

	@Override
	public boolean getKeyPressed(String keycode) {
		return pressed.contains(keycode);
	}
}
