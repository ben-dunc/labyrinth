//Benjamin Duncan
//mazeApp.java

import javax.swing.JFrame; 
import java.awt.Graphics2D; 
import java.util.Scanner;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class mazeApp {
	private GameWindow gamewindow;
	private StartWindow startwindow;
	
	public static void main(String [] args) {		
		new mazeApp();
	}
	
	public mazeApp() {
		startwindow = new StartWindow();
				
		JButton beginBtn = startwindow.getBeginBtn();
		
		beginBtn.addActionListener(new beginListener());
	}
		
	private class beginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (startwindow.close()) {
				gamewindow = new GameWindow(startwindow.getDiff(), startwindow.getMazeSize());
				startwindow.dispose();
			}
		}
	}
}


