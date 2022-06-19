//Benjamin Duncan 
//Enemy.java

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.ArrayList;

public class Enemy implements ActionListener, Drawable, ImageObserver {
	private int hp;
	private int attack;
	private int x, y;
	private Timer timer;
	private int prevDir;
	private int size;
	private int cell_size;
	private Cell[][] maze;
	private static BufferedImage EnemyPic;
	
	public Enemy(int x, int y, int cellSize, Cell[][] m) {
		try {
		    EnemyPic = ImageIO.read(new File("assets/Entities/MazeEnemy.png"));
		} catch (IOException e) {
			System.out.println("assets/Entities/MazeEnemy.png Read error");
		}
	
		size = cellSize / 3 + 1;
		cell_size = cellSize;
		timer = new Timer((int) (Math.random() * 300 + 850), this);
		maze = m;
		prevDir = -1;
		
		this.x = x;
		this.y = y;
		
		timer.start();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// double lRatio = maze[y][x].getLightRatio();
		
		if(g2 != null) {			
			g2.drawImage(EnemyPic, x * cell_size + size, y * cell_size + size, size, size, this);
		}
		else
			System.out.println("NULL");
	}
	
	public void actionPerformed(ActionEvent e) {
		ArrayList<Integer> pos = new ArrayList<Integer>();
		Cell temp = maze[this.y][this.x];
	
		if(prevDir != 0 && temp.hasHall(0))
			pos.add(0);
		if(prevDir != 1 && temp.hasHall(1))
			pos.add(1);	
		if(prevDir != 2 && temp.hasHall(2))
			pos.add(2);
		if(prevDir != 3 && temp.hasHall(3))
			pos.add(3);	
					
		if(pos.isEmpty()) {
			if(prevDir == 0) {
				y--;
				prevDir = 2;
			}
			else if(prevDir == 1) {
				x++;
				prevDir = 3;
			}
			else if(prevDir == 2) {
				y++;
				prevDir = 0;
			}
			else if(prevDir == 3) {
				x--;
				prevDir = 1;
			}
		}
		else {
			int r = pos.get((int) (Math.random() * pos.size()));
			
			if(r == 0) {
				y--;
				prevDir = 2;
			}
			if(r == 1) {
				x++;
				prevDir = 3;
			}
			if(r == 2) {
				y++;
				prevDir = 0;
			}
			if(r == 3) {
				x--;
				prevDir = 1;
			}
		}
	}	
	
	public void setPrevDir(int d) {
		prevDir = d;
	}
	public int getHp() {
		return hp;
	}
	public int getAttack() {
		return attack;
	}
	
	//public abstract boolean hit();
	public void damageTaken(int d) {
		hp -= d;
	}
	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		//do nothing. For I know not what I should do.
		return false;
	}
}