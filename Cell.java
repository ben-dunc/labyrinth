//Benjamin Duncan 
//Cell.java

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import java.awt.Image;

public class Cell implements Drawable, ImageObserver{
	private boolean isExit;
	private int x, y;
	private boolean[] halls;
	private double lRatio;
	private static int difficulty;
	
	private static BufferedImage WallImg;
	private static BufferedImage HallNS;
	private static BufferedImage HallEW;
	private static BufferedImage HallRoom;
	private static BufferedImage exitPic;
	
	private static int cell_size, hall_size;
	
	public Cell(int xPos, int yPos, int cellSize, int diff) {
		try {
		    WallImg = ImageIO.read(new File("assets/Maze/mazeWall.png"));
		} catch (IOException e) {
			System.out.println("assets/Maze/mazeWall.png Read error");
		}
	
		try {
		    HallNS = ImageIO.read(new File("assets/Maze/HallNS.png"));
		} catch (IOException e) {
			System.out.println("assets/Maze/HallNS.png Read error");
		}
	
		try {
		    HallEW = ImageIO.read(new File("assets/Maze/HallEW.png"));
		} catch (IOException e) {
			System.out.println("assets/Maze/HallEW.png Read error");
		}
	
		try {
		    HallRoom = ImageIO.read(new File("assets/Maze/HallRoom.png"));
		} catch (IOException e) {
			System.out.println("assets/Maze/HallRoom.png Read error");
		}
	
		try {
		    exitPic = ImageIO.read(new File("assets/Maze/MazeExit.png"));
		} catch (IOException e) {
			System.out.println("assets/Maze/MazeExit.png Read error");
		}
	
		isExit = false;
		x = xPos;
		y = yPos;
		halls = new boolean[4];
		cell_size = cellSize;
		hall_size = cellSize / 3 + 1;
		difficulty = diff;
		if(difficulty == 1)
			lRatio = 1; 
		else 
			lRatio = 0;
		
		for(int i = 0; i < halls.length; i++)
			halls[i] = false;
	}
	
	//draws the individual cell
	public void draw(Graphics g) { 
		Graphics2D g2 = (Graphics2D) g;
	
		//draws walls
		g2.drawImage(WallImg, x, y, cell_size, cell_size, this);
				
		//draws room
		if(isExit)
			g2.drawImage(exitPic, x + hall_size, y + hall_size, hall_size, hall_size, this);
	 	else
			g2.drawImage(HallRoom, x + hall_size, y + hall_size, hall_size, hall_size, this);
		
		
		//draws halls
		if(halls[0]) //north
			g2.drawImage(HallNS, x + hall_size, y, hall_size, hall_size, this);
		if(halls[1]) //east
			g2.drawImage(HallEW, x + hall_size + hall_size, y + hall_size, hall_size, hall_size, this);
		if(halls[2]) //south
			g2.drawImage(HallNS, x + hall_size, y + hall_size + hall_size, hall_size, hall_size, this);
		if(halls[3]) //west
			g2.drawImage(HallEW, x, y + hall_size, hall_size, hall_size, this);
	}
	
	public void createHall(int w) {
		halls[w] = true;
	}
	public void deleteHall(int w) {
		halls[w] = false;
	}
	public boolean isConnected() {
		boolean is = false;
		
		for(int i = 0; i < halls.length; i ++) 
			if(halls[i])
				is = true;
				
		return is;
	}
	public boolean hasHall(int i) {
		return halls[i];
	}
	public boolean isDeadEnd() {
		int hallsCount = 0;
		
		for(int i = 0; i < 4; i++)
			if(halls[i])
				hallsCount++;
		
		if(hallsCount == 1)
			return true;
		else
			return false;
	}
	public boolean isExit() {
		return isExit;
	}
	
	public int hallSize() {
		return hall_size;
	}

	public void setExit(boolean b) {
		isExit = b;
	}
	public void setLightRatio(double l) {
		if(difficulty == 1)
			lRatio = 1;
		else if(difficulty == 2 && ((l >= 0.5 && lRatio >= 0.5) || (lRatio < 0.5 && l > lRatio)))
			lRatio = l;
		else if(difficulty == 3)
			lRatio = l;
	}
	public double getLightRatio() {
		return lRatio;
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