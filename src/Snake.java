// snake class that creates and controls the snake.

import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {

	public static final int SIZE = 20;
	public static final int GRID = (GamePanel.WIDTH * GamePanel.HEIGHT) / (SIZE * SIZE);
	private int length, direction;

	private int[] x;
	private int[] y;

	public Snake() {

		length = 5;

		x = new int[GRID];
		y = new int[GRID];

		direction = 'R';
	}

	public void startPosition() {
		for (int i = 0; i < length; i++) {
			x[i] = 400;
			y[i] = 300;
		}
	}

	public void move() {
		// movement of the body
		for (int i = length; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		
		if (direction == 'L') {
			x[0] -= SIZE;
		} else if (direction == 'R') {
			x[0] += SIZE;
		} else if (direction == 'U') {
			y[0] -= SIZE;
		} else if (direction == 'D') {
			y[0] += SIZE;
		}
	}

	public boolean selfCollision() {
		
		for (int i = length; i > 0; i--) {
			if (x[0] == x[i] && y[0] == y[i]) {
				return true;
			}
		}
		return false;
	}

	public boolean wallCollision() {
		
		if ((x[0] < 0) || (x[0] >= GamePanel.WIDTH) || (y[0] < 0) || (y[0] >= GamePanel.HEIGHT)) {
			return true;
		}
		return false;	
	}

	public void increaseLength() {
		length++;
	}

	public void control(int keyPressed) {
		switch (keyPressed) {
		case KeyEvent.VK_LEFT:
			if (direction != 'R') {
				direction = 'L';
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (direction != 'L') {
				direction = 'R';
			}
			break;

		case KeyEvent.VK_UP:
			if (direction != 'D') {
				direction = 'U';
			}
			break;

		case KeyEvent.VK_DOWN:
			if (direction != 'U') {
				direction = 'D';
			}
			break;
		}
	}

	public Point getLocation() {
		Point pos = new Point(x[0], y[0]);
		return pos;
	}

	public void draw(Graphics g) {
		g.setColor(new Color(104, 171, 237));
		for (int i = 0; i < length; i++) {
			g.fillRect(x[i], y[i], SIZE, SIZE);
		}
	}
}