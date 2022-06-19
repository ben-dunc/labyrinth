//Benjamin Duncan
//mazeApp.java

import javax.swing.JButton;
import java.awt.event.*;

public class mazeApp {
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
				new GameWindow(startwindow.getDiff(), startwindow.getMazeSize());
				startwindow.dispose();
			}
		}
	}
}


