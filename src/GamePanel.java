// GamePanel is where the game runs. It updates the game window

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // set game screen size
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    // objects
    public Thread gameThread;
    public Image image;
    public Graphics graphics;
    private Snake snake;
    private Food food;

    // constructor
    public GamePanel() {

        snake = new Snake(30);
        food = new Food();

        this.setFocusable(true);
        this.addKeyListener(this);

        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

        gameThread = new Thread(this);
        gameThread.start();
    }

    // paint method 
    public void paint(Graphics g) {
        image = createImage(GAME_WIDTH, GAME_HEIGHT);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    private void draw(Graphics g) {
        // draw all the created objects from the constructor 
    	ImageIcon grass = new ImageIcon("E:\\Intern northpnd\\Java-Snake\\src\\assets\\BG.png");
    	Image grassImage = grass.getImage();
        g.drawImage(grassImage, 0 ,0, null);
        snake.draw(g);
        food.draw(g);
       
    }

    // call associated move methods for each objects movement
    public void move() {
        snake.move();
    }
    
    // collision 
    public void checkCollision() {
        // Check collision with boundaries
        if (snake.bodySegments.get(0).x < 0) {
            snake.bodySegments.get(0).x = 0;
        } else if (snake.bodySegments.get(0).x >= GAME_WIDTH) {
            snake.bodySegments.get(0).x = GAME_WIDTH - 20;
        }
    
        if (snake.bodySegments.get(0).y < 0) {
            snake.bodySegments.get(0).y = 0;
        } else if (snake.bodySegments.get(0).y >= GAME_HEIGHT) {
            snake.bodySegments.get(0).y = GAME_HEIGHT - 20;
        }
    
        // Check collision with body segments (excluding the head)
        for (int i = 1; i < snake.bodySegments.size(); i++) {
            if (snake.bodySegments.get(0).equals(snake.bodySegments.get(i))) {
                // Collision with body segment, handle game over or any other desired action
                // For example, you can call a method like gameOver() to handle the game over condition
                gameOver();
                break;
            }
        }

        if (food.checkCollision(snake.bodySegments.get(0).x, snake.bodySegments.get(0).y)) {
            snake.grow(); // Make the snake grow when it collides with the food
            food.spawnFood(); // Spawn new food
        }
    }
    
    private void gameOver() {
        // Implement your game over logic here
        // For example, stop the game loop, show a message, etc.
    }
    
    


    // run the game loop
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long now;

        while (true) {
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
    	snake.controls(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
  
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}