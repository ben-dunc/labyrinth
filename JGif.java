// Benjamin Duncan
// JGif.java

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.event.*;

public class JGif extends JLabel implements Tickable {
	private int frameIndex; 
	private ArrayList<ImageIcon> frames = new ArrayList<ImageIcon>();
	
	public void addFrame(ImageIcon frame) {
		frames.add(frame);
	}
	
	public void addFrame(String frame) {
		frames.add(new ImageIcon(frame));
	}

	// on frame
	public void tick(int msTick) {
		if(frameIndex >= frames.size())
			frameIndex = 0;
		if(frames.size() > 0)
			this.setIcon(frames.get(frameIndex));

		frameIndex++;
	}
}