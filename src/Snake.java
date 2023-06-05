import java.awt.*;

public class Snake {

    private int length;
    private int[] x;
    private int[] y;
    public int direction; // 0 - up, 1 - down, 2 - left, 3 - right

    public Snake(int length) {
        this.length = length;
        x = new int[length];
        y = new int[length];
        direction = 3; // Start with right direction

        // Initialize snake's body positions
        for (int i = 0; i < length; i++) {
            x[i] = (length - i - 1) * 20;
            y[i] = 0;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move() {
        // Move the snake by updating its body positions
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 0: // Up
                y[0] -= 20;
                break;
            case 1: // Down
                y[0] += 20;
                break;
            case 2: // Left
                x[0] -= 20;
                break;
            case 3: // Right
                x[0] += 20;
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        for (int i = 0; i < length; i++) {
            g.fillRect(x[i], y[i], 20, 20);
        }
    }
}
