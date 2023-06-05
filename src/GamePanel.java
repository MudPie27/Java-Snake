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

    // constructor
    public GamePanel() {

        snake = new Snake(15);

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
        snake.draw(g);
       
    }

    // call associated move methods for each objects movement
    public void move() {
        snake.move();
    }
    
    // collision 
    public void checkCollision() {

    }

    // run the game loop
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 30;
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

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP && snake.direction != 1) {
            snake.setDirection(0); // Up
        } else if (keyCode == KeyEvent.VK_DOWN && snake.direction != 0) {
            snake.setDirection(1); // Down
        } else if (keyCode == KeyEvent.VK_LEFT && snake.direction != 3) {
            snake.setDirection(2); // Left
        } else if (keyCode == KeyEvent.VK_RIGHT && snake.direction != 2) {
            snake.setDirection(3); // Right
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
  
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}