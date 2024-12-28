// Benjamin Duncan  
// Player.java

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Player implements Drawable, ImageObserver, Tickable {
	private int x, y, cellSize, playerSize;
	private BufferedImage playerPic;

	public Player(int xPos, int yPos, int cellSize) {
		try {
			playerPic = ImageIO.read(new File("assets/Entities/MazePlayer.png"));
		} catch (IOException e) {
			System.out.println("assets/Entities/MazePlayer.png Read error");
		}

		x = xPos;
		y = yPos;
		playerSize = cellSize / 3 + 1;
		this.cellSize = cellSize;
	}

	@Override
	public void tick(int msTick, InputManager input) {
		checkMovement(input);
	}

	private void checkMovement(InputManager input) {
		if (y - 1 >= 0 && input.getKeyOnPressed(KeyEvent.VK_UP))
			y--;
		if (x + 1 < 750 && input.getKeyOnPressed(KeyEvent.VK_RIGHT))
			x++;
		if (y + 1 < 750 && input.getKeyOnPressed(KeyEvent.VK_DOWN))
			y++;
		if (x - 1 >= 0 && input.getKeyOnPressed(KeyEvent.VK_LEFT))
			x--;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		g2.drawImage(playerPic, x * cellSize + playerSize, y * cellSize + playerSize, playerSize, playerSize, this);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}