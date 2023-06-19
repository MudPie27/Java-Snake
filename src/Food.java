// class that spawns food for the snake to consume

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Food {

    int x, y, chance, size;
    boolean gApple;
    ImageIcon appleIcon, gAppleIcon;
    Image appleImage, gAppleImage;

    public Food() {
        size = 20;
        gApple = false;

        appleIcon = new ImageIcon(GamePanel.appleImg);
        appleImage = appleIcon.getImage();

        gAppleIcon = new ImageIcon(GamePanel.goldenAppleImg);
        gAppleImage = gAppleIcon.getImage();
    }

    public boolean appleChoice() {
        chance = (int) (Math.random() * 7);

        if (chance == 1)
            gApple = true;
        else
            gApple = false;

        return gApple;
    }

    public void generateFood() {
        appleChoice();
        x = ThreadLocalRandom.current().nextInt(0, GamePanel.WIDTH / size) * size;
        y = ThreadLocalRandom.current().nextInt(60 / size, GamePanel.HEIGHT / size) * size;   
    }

    public Point getPosition() {

        return new Point(x, y);

    }

    public void draw(Graphics g) {

        if (gApple) {
            g.drawImage(gAppleImage, x, y, size, size, null);

        } else {
            g.drawImage(appleImage, x, y, size, size, null);
        }

    }
}