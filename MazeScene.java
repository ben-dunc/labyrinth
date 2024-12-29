// Benjamin Duncan
// MazeScene.java

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class MazeScene extends Scene {

	private Player player;

	public MazeScene(SceneManager sceneManager) {
		super(sceneManager);

		JLabel j = new JLabel("asdf");
		super.setBackground(Color.black);
		j.setSize(100, 100);

		player = new Player(10, 10);

		super.add(j);
	}

	public void tick(int msTick, InputManager input) {
		player.tick(msTick, input);
		super.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		player.draw(g);
	}
}
