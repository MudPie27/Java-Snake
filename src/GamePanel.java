// GamePanel is where the game runs. It updates the game window

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	// set game screen size
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	// file directories
	public static String snakeImg = "src\\images\\Snake.png";
	public static String appleImg = "src\\images\\Apple.png";
	public static String goldenAppleImg = "src\\images\\GoldenApple.png";
	public static String bombImg = "src\\images\\Bomb.png";
	public static String grassIMG = "src\\images\\BG.png";
	public static String bgmWav = "src\\images\\BGM.wav";
	public static String hsFile = "src\\HighScore.txt";

	// objects
	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	private Snake snake;
	private Food food;
	private Bomb bomb;
	private Score score;
	public static boolean gameOver = false;

	// constructor
	public GamePanel() {
		
		food = new Food();
		bomb = new Bomb();
		snake = new Snake();
		score = new Score();

		this.setFocusable(true);
		this.addKeyListener(this);

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// spawns the initial objects and player snake
		food.generateFood();
		bomb.generateBomb();
		snake.startPosition();

		gameThread = new Thread(this);
		gameThread.start();
	}

	// paint method
	public void paint(Graphics g) {
		image = createImage(WIDTH, HEIGHT);
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	private void draw(Graphics g) {
		// draw all the created objects from the constructor
		ImageIcon grass = new ImageIcon(grassIMG); 
		Image grassImage = grass.getImage();
		g.drawImage(grassImage, 0, 0, null); // backgroubnd
		food.draw(g);
		bomb.draw(g);
		snake.draw(g);
		score.draw(g);
	}

	// call snake move methods 
	public void move() {
		snake.move();
	}

	// collision
	public void checkCollision() {

		// checks if snake's head collided with food
		if (snake.getLocation().equals(food.getPosition())) {
			snake.increaseLength(); // makes snake longer
			score.updatePoints(food.gApple); // updates point
			food.generateFood(); // generates new food
			bomb.generateBomb(); // generates bomb
		} 
		// all collisions below ends the game
		// checks if snake's head collided with bomb
		if (snake.getLocation().equals(bomb.getPosition())) {
			gameOver();
		} 

		// checks if snake's head collided with other parts of it's body
		if (snake.selfCollision() == true) {
			gameOver();
		}
		// checks if snake's head goes outside the screen
		if (snake.wallCollision()) {
			gameOver();
		}
	}

	// method that stops the game loop if called
	private void gameOver() {
		
		gameOver = true;

	}

	// run the game loop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 10;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;

		while (!gameOver) {
			now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) { // run necessary methods in the loop
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	// Key press listeners
	@Override
	public void keyPressed(KeyEvent e) {
		snake.control(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}