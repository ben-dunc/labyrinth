// Benjamin Duncan
// MazeScene.java

import java.awt.Color;
import javax.swing.*;

public class MazeScene extends Scene {

	private Player player;

	public MazeScene(SceneManager sceneManager) {
		super(sceneManager);

		JLabel j = new JLabel("asdf");
		super.setBackground(Color.black);
		j.setSize(100, 100);

		player = new Player(10, 10, 90);

		super.add(j, JLayeredPane.DEFAULT_LAYER);
	}

	public void tick(int msTick, InputManager input) {
		player.tick(msTick, input);
		player.draw(super.getGraphics());
	}
}
