// Benjamin Duncan
// MazeController.java

import javax.swing.*;

public class MazeController extends JLayeredPane implements Tickable {
	private Maze maze;
	private Player player;
	private int difficulty; // easy = 1, normal = 2, hard = 3

	private PaintLayer MazeLayer;
	private PaintLayer LightLayer;
	private PaintLayer PlayerLayer;

	public MazeController(int diff, int size) {

		maze = new Maze(size, difficulty);
		player = new Player(0, 0);

		// ---- assign objects into layers ----
		MazeLayer = new PaintLayer(mazeWindowSize());
		LightLayer = new PaintLayer(mazeWindowSize());
		PlayerLayer = new PaintLayer(mazeWindowSize());

		add(MazeLayer, JLayeredPane.DEFAULT_LAYER);
		add(LightLayer, JLayeredPane.MODAL_LAYER);
		add(PlayerLayer, JLayeredPane.DRAG_LAYER);

		MazeLayer.addDrawable(maze);
		LightLayer.addDrawable(new LightLayer(maze.size(), maze.cellSize(), player, maze.getMaze(), diff));
		PlayerLayer.addDrawable(player);

		MazeLayer.paint();
		LightLayer.paint();
		PlayerLayer.paint();

		this.setVisible(true);

		MazeLayer.setVisible(true);
		LightLayer.setVisible(true);
		PlayerLayer.setVisible(true);
	}

	public void tick(int msTick, InputManager input) {
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
		// return maze.getCell(player.getX(), player.getY()).isExit();
		return false;
	}
}