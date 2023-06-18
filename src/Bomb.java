// class that spawns food for the snake to consume

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Bomb {

    int x, y, size;
    ImageIcon bombIcon;
    Image bombImage;

    public Bomb() {
        size = 20;

        bombIcon = new ImageIcon("src\\images\\Bomb.png");
        bombImage = bombIcon.getImage();

    }

    public void generateBomb() {

        x = ThreadLocalRandom.current().nextInt(0, GamePanel.WIDTH / Snake.SIZE) * Snake.SIZE;
        y = ThreadLocalRandom.current().nextInt(0, GamePanel.HEIGHT / Snake.SIZE) * Snake.SIZE;

        if (Score.points <= 5) {
            x = -25;
            y = -25;

        }

    }

    public Point getPosition() {

        return new Point(x, y);

    }

    public void draw(Graphics g) {
        g.drawImage(bombImage, x, y, size, size, null);
    }
}