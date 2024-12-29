// Benjamin Duncan  
// Player.java

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Player implements Drawable, ImageObserver, Tickable {
	private int x, y, playerSize = 30;
	private int moveSpeed = 10;
	private BufferedImage playerPic;

	public Player(int startX, int startY) {
		try {
			playerPic = ImageIO.read(new File("assets/Entities/MazePlayer.png"));
		} catch (IOException e) {
			System.out.println("assets/Entities/MazePlayer.png Read error");
		}

		x = startX;
		y = startY;
	}

	@Override
	public void tick(int msTick, InputManager input) {
		checkMovement(input);
	}

	private void checkMovement(InputManager input) {
		if (input.getKeyPressed("UP"))
			y -= moveSpeed;
		if (input.getKeyPressed("RIGHT"))
			x += moveSpeed;
		if (input.getKeyPressed("DOWN"))
			y += moveSpeed;
		if (input.getKeyPressed("LEFT"))
			x -= moveSpeed;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		g2.drawImage(playerPic, x, y, playerSize, playerSize, this);
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