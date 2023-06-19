// class that spawns food for the snake to consume

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Food {
    // variables
	private final int SIZE = 20; // size of food. Matches snake sizeSIZE int x, y, chance;
    private int x, y, chance;
    public boolean gApple;
    // apple designs
    private ImageIcon appleIcon, gAppleIcon;
    private Image appleImage, gAppleImage;

    public Food() {
        // sets images        
        appleIcon = new ImageIcon(GamePanel.appleImg);
        appleImage = appleIcon.getImage();

        gAppleIcon = new ImageIcon(GamePanel.goldenAppleImg);
        gAppleImage = gAppleIcon.getImage();
    }

    // randomly decides if the apple will be regular or golden
    public boolean appleChoice() {
        chance = (int) (Math.random() * 7); // one in seven chances of it being golden

        if (chance == 1)
            gApple = true;
        else
            gApple = false;

        return gApple; 
    }

    // genereates food location
    public void generateFood() {
        appleChoice();
        // threadlocalrandom is used instead of math.random because for some reason math.random
        // made the food non interactable. It had something to do with being on a separate thread, so the snake
        // just goes over the food.
        x = ThreadLocalRandom.current().nextInt(0, GamePanel.WIDTH / SIZE) * SIZE;
        y = ThreadLocalRandom.current().nextInt(60 / SIZE, GamePanel.HEIGHT / SIZE) * SIZE; // excludes top 60 pixels
    }

    // returns current food position using point object
    public Point getPosition() {
        return new Point(x, y);
    }

    // draw method
    public void draw(Graphics g) {
        // draws regular or golden apple 
        if (gApple) {
            g.drawImage(gAppleImage, x, y, SIZE, SIZE, null);

        } else {
            g.drawImage(appleImage, x, y, SIZE, SIZE, null);
        }

    }
}