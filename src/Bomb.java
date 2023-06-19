// class that spawns bomb as an obstacle for the snake
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Bomb {
    // variables
    private final int SIZE = 20;
    private int x, y, chance;
    private boolean bombSpawns;
    // bomb designs
    private ImageIcon bombIcon;
    private Image bombImage;

    public Bomb() {
        // sets bomb images
        bombIcon = new ImageIcon(GamePanel.bombImg);
        bombImage = bombIcon.getImage();
    }

    // decides if a bomb with spawn or not
    public boolean bombChoice() {
        chance = (int) (Math.random() * 2); // it hasa one in two chances of spawning alongside the apples

        if (chance == 1)
            bombSpawns = true;
        else
            bombSpawns = false;

        return bombSpawns;
    }

    // genereates bomb location
    public void generateBomb() {
        bombChoice();
        // bomb starts spawning after the player has atleast 5 points
        if ((Score.points > 5) && (chance == 1)) {
            x = ThreadLocalRandom.current().nextInt(0, GamePanel.WIDTH / SIZE) * SIZE;
            y = ThreadLocalRandom.current().nextInt(60 / SIZE, GamePanel.HEIGHT / SIZE) * SIZE;
        } else { // before 5 points, the bomb remains off screen
            x = -25;
            y = -25;
        }
    }

    // returns current bomb position
    public Point getPosition() {
        return new Point(x, y);
    }

    // draw method
    public void draw(Graphics g) {
        g.drawImage(bombImage, x, y, SIZE, SIZE, null);
    }
}