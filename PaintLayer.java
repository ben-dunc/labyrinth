//Benjamin Duncan
//PaintLayer.java

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

public class PaintLayer extends JComponent {
	private ArrayList<Drawable> list;
	
	public PaintLayer(int size) {
		list = new ArrayList<Drawable>();
		setSize(size, size); // It is manditory for setSize to be called. Else paintComponent won't ever be called.
	}
	
	public void addDrawable(Drawable d) {
		list.add(d);
	}
	
	public void paint() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if (list.size() != 0)
			for (int i = 0; i < list.size(); i++)
				list.get(i).draw(g);
	}
	
	public int getListSize() {
		return list.size();
	}
}