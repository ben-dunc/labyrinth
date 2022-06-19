//Benjamin Duncan
//GameWindow.java

import java.awt.event.*;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {
	private MazeController game;
	private Timer exitCheck;
	
	public GameWindow(int diff, int size) {			
		
		game = new MazeController(diff, size);

		setTitle("The Maze Game (" + size + " x " + size + ")");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(game.mazeWindowSize(), game.mazeWindowSize() + 22);
		add(game);
		setVisible(true);
				
		exitCheck = new Timer(100, this);
		exitCheck.start();
	}
		
	public void actionPerformed(ActionEvent e) {
		if(game.playerAtExit()) {
			this.dispose();
			exitCheck.stop();
			System.exit(0);
		}
	}
}
		