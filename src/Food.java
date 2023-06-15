import java.awt.*;
import java.util.Random;

public class Food {

    private int x;
    private int y;
    private int size=15;

    public Food() {
        spawnFood();
    }

    public void spawnFood() {
        Random random = new Random();
        int maxX = GamePanel.GAME_WIDTH / size;
        int maxY = GamePanel.GAME_HEIGHT / size;

        // Generate random coordinates for the food within the game screen
        x = random.nextInt(maxX) * size;
        y = random.nextInt(maxY) * size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);
    }

    public boolean checkCollision(int headX, int headY) {
        // Check collision between the snake's head and the food
        return x == headX && y == headY;
    }
}
