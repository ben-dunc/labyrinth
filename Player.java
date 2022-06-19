//Benjamin Duncan 
//Player.java

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Player implements Drawable, ImageObserver {
	private int x, y, cell_size, size;
	private BufferedImage playerPic;

	public Player(int xPos, int yPos, int sizenew, int cell_sizeN) {
		try {
		    playerPic = ImageIO.read(new File("assets/Entities/MazePlayer.png"));
		} catch (IOException e) {
			System.out.println("assets/Entities/MazePlayer.png Read error");
		}
	
		x = xPos;
		y = yPos;
		size = cell_sizeN / 3 + 1;
		cell_size = cell_sizeN;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(Color.BLUE); 
		g2.drawImage(playerPic, x * cell_size + size, y * cell_size + size, size, size, this);
	}
	
	public void moveUp(int d) {
		if(y - 1 >= 0)
			y --;
	}
	public void moveRight(int d) {
		if(x + 1 < 750)
			x ++;
	}
	public void moveDown(int d) {
		if(y + 1 < 750)
			y ++;
	}
	public void moveLeft(int d) {
		if(x - 1 >= 0)
			x --;		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		//do nothing. For I know not what I should do.
		return false;
	}
}