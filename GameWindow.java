// Benjamin Duncan
// GameWindow.java

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {
	private MazeController mazeController;
	private Timer exitCheck;

	public GameWindow(int diff, int size) {

		mazeController = new MazeController(diff, size);

		super.setTitle("The Maze Game (" + size + " x " + size + ")");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setMinimumSize(new Dimension(mazeController.mazeWindowSize(), mazeController.mazeWindowSize() + 22));

		super.add(mazeController);
		super.setVisible(true);
		super.setBackground(Color.black);

		exitCheck = new Timer(100, this);
		exitCheck.start();
	}

	public void actionPerformed(ActionEvent e) {
		if (mazeController.playerAtExit()) {
			this.dispose();
			exitCheck.stop();
			System.exit(0);
		}
	}
}
