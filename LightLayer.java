// Benjamin Duncan
// LightLayer.java

import java.awt.*;

public class LightLayer implements Drawable {
	public double lightSpread = 0.22;
	public double lightThreshold;
	public double initialLightLevel;
	public int mazeSize;
	public int cellSize;
	public Player player;
	public Cell[][] maze;
	public double[][] light;
	public int difficulty;
	
	public double EASY_LIGHT_LEVEL = 1.0;
	public double MEDIUM_LIGHT_LEVEL = 0.33;
	public double HARD_LIGHT_LEVEL = 0.0;


	public LightLayer (int mS, int cS, Player p, Cell[][] m, int diff) {
		mazeSize = mS;
		cellSize = cS;
		player = p;
		maze = m;
		
		difficulty = diff;

		light = new double[mS][mS];

		switch(diff) {
			case 1: 
				lightThreshold = EASY_LIGHT_LEVEL;
				initialLightLevel = EASY_LIGHT_LEVEL;
				break;
			case 2: 
				lightThreshold = MEDIUM_LIGHT_LEVEL;
				initialLightLevel = HARD_LIGHT_LEVEL;
				break;
			case 3: 
				lightThreshold = HARD_LIGHT_LEVEL;
				initialLightLevel = HARD_LIGHT_LEVEL;
				break;
			default: 
				lightThreshold = EASY_LIGHT_LEVEL;
		}

		// set initial values
		for (int i = 0; i < mazeSize; i++) {
			for (int a = 0; a < mazeSize; a++) {
				light[a][i] = initialLightLevel;
			}
		}
		
		System.out.println("lightThreshold: " + lightThreshold);
		System.out.println("initialLightLevel: " + initialLightLevel);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (difficulty == 1) 
			return;
	
		lightUp(player.getX(), player.getY());
		
		for (int i = 0; i < mazeSize; i++) {
			for (int a = 0; a < mazeSize; a++) {
				g2.setColor(new Color(0.0f, 0.0f, 0.0f, (float) (1.0 - light[a][i])));
				g2.fillRect(i * cellSize, a * cellSize, cellSize, cellSize);
			}
		}
	}
	
	public void lightUp(int x, int y) {
	
		light[y][x] = 1.0;
		
		if (maze[y][x].hasHall(0)) // northward
			lightUpRecursive(x, y - 1, 1.0, 0);
		if (maze[y][x].hasHall(1)) // eastward
			lightUpRecursive(x + 1, y, 1.0, 1);
		if (maze[y][x].hasHall(2)) // southward
			lightUpRecursive(x, y + 1, 1.0, 2);
		if (maze[y][x].hasHall(3)) // westward
			lightUpRecursive(x - 1, y, 1.0, 3);
	}
	
	public void lightUpRecursive(int x, int y, double r, int dir) {
		light[y][x] = r;

		if (r >= lightThreshold - lightSpread || (light[y][x] < lightThreshold && light[y][x] > 0.0)) {
			if (maze[y][x].hasHall(0) && dir == 0) // northward
				lightUpRecursive(x, y - 1, r - lightSpread, 0);
			else if (maze[y][x].hasHall(0) && dir != 2)
				lightUpRecursive(x, y - 1, r - (lightSpread * 1.5), 0);
				
			if (maze[y][x].hasHall(1) && dir == 1) // eastward
				lightUpRecursive(x + 1, y, r - lightSpread, 1);
			else if (maze[y][x].hasHall(1) && dir != 3)
				lightUpRecursive(x + 1, y, r - (lightSpread * 1.5), 1);
				
			if (maze[y][x].hasHall(2) && dir == 2) // southward
				lightUpRecursive(x, y + 1, r - lightSpread, 2);
			else if (maze[y][x].hasHall(2) && dir != 0)
				lightUpRecursive(x, y + 1, r - (lightSpread * 1.5), 2);
				
			if (maze[y][x].hasHall(3) && dir == 3) // westward
				lightUpRecursive(x - 1, y, r - lightSpread, 3);
			else if (maze[y][x].hasHall(3) && dir != 1)
				lightUpRecursive(x - 1, y, r - (lightSpread * 1.5), 3);
		}

		if (light[y][x] < lightThreshold) {
			light[y][x] = lightThreshold;
		}
	}
}