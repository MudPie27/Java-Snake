import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Food {

    private int x;
    private int y;

    public void generateFood() {
        x = ThreadLocalRandom.current().nextInt(0, GamePanel.WIDTH / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        y = ThreadLocalRandom.current().nextInt(0, GamePanel.HEIGHT / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
    }
}
