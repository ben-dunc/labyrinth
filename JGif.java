//Benjamin Duncan
//JGif.java

import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.event.*;

public class JGif extends JLabel implements ActionListener {
	private int frameIndex; 
	private ArrayList<ImageIcon> frames = new ArrayList<ImageIcon>();
	private Timer animateTimer;

	public JGif() {
		animateTimer = new Timer(500, this);
		animateTimer.start();
	}

	public JGif(int fps) {
		animateTimer = new Timer(1000 / fps, this); 
		animateTimer.start();
	}
	
	public void setFPS(int fps) {
		animateTimer.setDelay(1000 / fps);
	}
	
	public void addFrame(ImageIcon frame) {
		frames.add(frame);
	}
	
	public void addFrame(String frame) {
		frames.add(new ImageIcon(frame));
	}

	public void actionPerformed(ActionEvent e) {
		if(frameIndex >= frames.size())
			frameIndex = 0;
		if(frames.size() > 0)
			this.setIcon(frames.get(frameIndex));

		frameIndex++;
	}
}