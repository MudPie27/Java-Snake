// snake class that creates and controls the snake.

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Snake {

	// variables
	public static final int SIZE = 20; // size of each snake component
	public static final int GRID = (GamePanel.WIDTH * GamePanel.HEIGHT) / (SIZE * SIZE); // grid division that fits the snakes size
	private int length; // number of snake components
	private char direction; // snakes direction

	// snake design
	ImageIcon snakeIcon;
    Image snakeImage;

	// arrays that contain x and y coordinates of each component 
	private int[] x;
	private int[] y;

	public Snake() {

		length = 5; // sets start length to 5

		// sets the x/y arrays to the grid size (the maximum length the snake can possibly be)
		x = new int[GRID];
		y = new int[GRID];

		direction = 'R'; // starts by going right
		
		// sets image
		snakeIcon = new ImageIcon(GamePanel.snakeImg);
        snakeImage = snakeIcon.getImage();
	}

	// Starts the snake in the middle of the screen
	public void startPosition() {
		for (int i = 0; i < length; i++) {
			x[i] = 400;
			y[i] = 300;
		}
	}

	// controlls for the snake
	public void control(int keyPressed) {
		switch (keyPressed) {
		case KeyEvent.VK_LEFT:
			if (direction != 'R') {  // cannot go in the exact opposite direction as it'd be self collision
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

	// move method
	public void move() {
		// Makes each component follow the one before it, creating the classic snake movement
		for (int i = length; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		// moves the head of the snake by one grid section in the direction the player chooses. The rest follows
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

	// checkc for self collision
	public boolean selfCollision() {
		// returns true if any of the components have the same position 
		for (int i = length; i > 0; i--) {
			if (x[0] == x[i] && y[0] == y[i]) {
				return true;
			}
		}
		return false;
	}

	// checks for wall collision
	public boolean wallCollision() {
		// returns true if the head goes outside the border of the screen 
		if ((x[0] < 0) || (x[0] >= GamePanel.WIDTH) || (y[0] < 0) || (y[0] >= GamePanel.HEIGHT)) {
			return true;
		}
		return false;	
	}

	// increases the number of components
	public void increaseLength() {
		length++;
	}

	// returns the snake head location using point object. Used to detect collision with food/bomb
	public Point getLocation() {
		Point pos = new Point(x[0], y[0]);
		return pos;
	}

	// draw method
	public void draw(Graphics g) {
		g.setColor(new Color(104, 171, 237));
		for (int i = 0; i < length; i++) {
			g.drawImage(snakeImage, x[i], y[i], SIZE, SIZE, null);
		}
	}
}