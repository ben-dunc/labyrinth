// Benjamin Duncan
// MazeScene.java

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class MazeScene extends Scene {
	private MazeController mazeController;
	private Timer exitCheck;

	public MazeScene(SceneManager sceneManager) {
		super(sceneManager);

		mazeController = new MazeController(1, 10);
		super.setMinimumSize(new Dimension(mazeController.mazeWindowSize(), mazeController.mazeWindowSize() + 22));

		super.add(mazeController);
		super.setVisible(true);
		super.setBackground(Color.black);
	}

	public void actionPerformed(ActionEvent e) {
		if (mazeController.playerAtExit()) {
			sceneManager.LoadScene(new MainMenuScene(sceneManager));
		}
	}

	public void tick(int msTick) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tick'");
	}
}
