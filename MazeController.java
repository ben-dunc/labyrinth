//Benjamin Duncan
//MazeController.java

import java.awt.event.*;
import java.awt.*; 
import javax.swing.*;
import java.util.ArrayList;

public class MazeController extends JLayeredPane implements ActionListener {
	private Maze maze;
	private Player player;
	private int difficulty; // easy = 1, normal = 2, hard = 3
	private ArrayList<Enemy> enemies;
	private Timer timer;
	
	private PaintLayer MazeLayer;
	// private PaintLayer EnemyLayer;
	private PaintLayer LightLayer;
	private PaintLayer PlayerLayer;
	
	public MazeController(int diff, int size) {	
		
		enemies = new ArrayList<Enemy>();
		
		maze = new Maze(size, difficulty);
		player = new Player(0, 0, maze.getCell(0,0).hallSize(), maze.cellSize());
		
		//create enemies
		int enemyChance = (4 - diff) * 1;
		int count = 0;
		
		for(int i = 0; i < maze.size(); i++)
			for(int a = 0; a < maze.size(); a++) {
				if(maze.getCell(i, a).isDeadEnd() && !(i == 0 || a == 0)) {
					if(count % enemyChance == 0)
						enemies.add(new Enemy(a, i, maze.cellSize(), maze.getMaze()));	
					count++;
				}
			}
		
		//add bindings
			//up binding
			getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
			getActionMap().put("up", new UpPressed());
		
			//right binding
			getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
			getActionMap().put("right", new RightPressed());
		
			//down binding
			getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
			getActionMap().put("down", new DownPressed());
		
			//left binding
			getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
			getActionMap().put("left", new LeftPressed());
					
		
		// ---- assign objects into layers ----
		MazeLayer = new PaintLayer(mazeWindowSize());
		// EnemyLayer = new PaintLayer(mazeWindowSize());
		LightLayer = new PaintLayer(mazeWindowSize());
		PlayerLayer = new PaintLayer(mazeWindowSize());
		
		add(MazeLayer, JLayeredPane.DEFAULT_LAYER);
		// add(EnemyLayer, JLayeredPane.PALETTE_LAYER);
		add(LightLayer, JLayeredPane.MODAL_LAYER);
		add(PlayerLayer, JLayeredPane.DRAG_LAYER);
		
		MazeLayer.addDrawable(maze);
		
		for(int i = 0; i < enemies.size(); i++) {
			// EnemyLayer.addDrawable(enemies.get(i));
		}
		
		LightLayer.addDrawable(new LightLayer(maze.size(), maze.cellSize(), player, maze.getMaze(), diff));
		
		PlayerLayer.addDrawable(player);
		
		MazeLayer.paint();
		// EnemyLayer.paint();
		LightLayer.paint();
		PlayerLayer.paint();
		
		this.setVisible(true);

		MazeLayer.setVisible(true);
		// EnemyLayer.setVisible(true);
		LightLayer.setVisible(true);
		PlayerLayer.setVisible(true);

		//start timer
		Timer timer = new Timer(20, this);
		timer.start();
	}
	
	// tick of 100 ms
	public void actionPerformed(ActionEvent e) {
		// EnemyLayer.repaint();
		LightLayer.paint();
		PlayerLayer.repaint();
	}
	
	public int mazeWindowSize() {
		return maze.cellSize() * maze.size();
	}
	public int cellSize() {
		return maze.cellSize();
	}
	public int numOfCells() {
		return maze.size();
	}
	
	public boolean playerAtExit() {
		if(maze.getCell(player.getX(), player.getY()).isExit())
			return true;
		else 
			return false;
	}
	
	class UpPressed extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if(maze.getCell(player.getX(), player.getY()).hasHall(0)) {
				player.moveUp(maze.cellSize());
			}
		}
	}
	class RightPressed extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if(maze.getCell(player.getX(), player.getY()).hasHall(1)) {
				player.moveRight(maze.cellSize());
			}
		}
	}
	class DownPressed extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if(maze.getCell(player.getX(), player.getY()).hasHall(2)) {
				player.moveDown(maze.cellSize());
			}
		}
	}
	class LeftPressed extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if(maze.getCell(player.getX(), player.getY()).hasHall(3)) {
				player.moveLeft(maze.cellSize());
			}
		}
	}
}