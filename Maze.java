// Benjamin Duncan 
// Maze.java
 
import java.awt.Graphics;
import java.util.ArrayList;

public class Maze implements Drawable {
	private Cell[][] maze;
	private int cell_size;
	private static int difficulty;
	private ArrayList<Cell> posExits;
	private ArrayList<Enemy> enemies;

	public Maze(int size, int d) {		
	
		difficulty = d;
		posExits = new ArrayList<Cell>();
	
		// make maze array
		cell_size = 750/size;
		maze = new Cell[size][size]; 
		
		// create all cells 
		for(int i = 0; i < size; i++) 
			for(int a = 0; a < size; a++) 
				maze[i][a] = new Cell(a * cell_size, i * cell_size, cell_size, difficulty); // x, y, cell size
				
		// call maze recursion function
		mazeMaker();

		// create exit		
		int r = (int) (Math.random() * (posExits.size() - 1));
		
		if(posExits.size() > 0)
			posExits.get(r).setExit(true);
		else
			maze[0][0].setExit(true);
	}
		
	public void draw(Graphics g) {
		// draw cells
		for(int i = 0; i < maze.length; i++) {
			for(int a = 0; a < maze.length; a++) {
				maze[i][a].draw(g);	
			}
		}
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	public Cell getCell(int x, int y) {
		return maze[y][x];
	}
	public Cell[][] getMaze() {
		return maze;
	}
	public int cellSize() {
		return cell_size;
	}
	public int size() {
		return maze.length;
	}
	
	public void mazeMaker() {
		mazeMaker(0, 0, -1, 1);
	}
	public void mazeMaker(int x, int y, int prevWall, int distance) {
		Cell temp = maze[y][x];

		// destroy wall leading from previous cell if prevWall != -1
		if(prevWall != -1) {
			if(prevWall == 0) // if prevWall = 0 (north) then create a south hall
				temp.createHall(2);
			
			if(prevWall == 1) // if prevWall = 1 (east) then create a west hall
				temp.createHall(3);
			
			if(prevWall == 2) // if prevWall = 2 (south) then create a north hall
				temp.createHall(0);
			
			if(prevWall == 3) // if prevWall = 3 (west) then create a east hall
				temp.createHall(1);
		}
	
		// other such stuff
		boolean aLonely = true;
	
		while(aLonely) {
		
			int r = (int) (Math.random() * 4);
			aLonely = false;
		
			if(r == 0 && y > 0 && !maze[y - 1][x].isConnected()) { // check northern cell
				temp.createHall(0);
				mazeMaker(x, y - 1, 0, distance + 1);
			}
				
			else if(r == 1 && x < maze.length - 1 && !maze[y][x + 1].isConnected()) { // check eastern cell
				temp.createHall(1);
				mazeMaker(x + 1, y, 1, distance + 1);
			}
				
			else if(r == 2 && y < maze.length - 1 && !maze[y + 1][x].isConnected()) { // check southern cell
				temp.createHall(2);
				mazeMaker(x, y + 1, 2, distance + 1);
			}
				
			else if(r == 3 && x > 0 && !maze[y][x - 1].isConnected()) { // check western cell
				temp.createHall(3);
				mazeMaker(x - 1, y, 3, distance + 1);
			}
			
			// check for lonely cells
			if(y > 0 && !maze[y - 1][x].isConnected()) // check northern cell
				aLonely = true;
			else if(x < maze.length - 1 && !maze[y][x + 1].isConnected()) // check eastern cell
				aLonely = true;
			else if(y < maze.length - 1 && !maze[y + 1][x].isConnected()) // check southern cell
				aLonely = true;
			else if(x > 0 && !maze[y][x - 1].isConnected()) // check western cell
				aLonely = true;
		}
		
			
		if(maze[y][x].isDeadEnd() && distance >= maze.length * 1.7)
			posExits.add(maze[y][x]);
	}
}