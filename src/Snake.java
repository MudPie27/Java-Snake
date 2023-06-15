import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Snake {

    public ArrayList<Point> bodySegments;
    private int direction;
    private int speed;

    public Snake(int initialLength) {
        bodySegments = new ArrayList<>();
        direction = 3; // Right by default
        speed = 5;

        // Initialize the snake's body segments
        int startX = 400;
        int startY = 0;
        for (int i = 0; i < initialLength; i++) {
            int segmentX = startX - i * 20;
            int segmentY = startY;
            bodySegments.add(new Point(segmentX, segmentY));
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void controls(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && direction != 1)
            setDirection(0); // Up
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != 0)
            setDirection(1); // Down
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != 3)
            setDirection(2); // Left
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 2)
            setDirection(3); // Right
    }

    public void move() {
        // Move the snake's head in the current direction
        Point head = bodySegments.get(0);
        int headX = head.x;
        int headY = head.y;

        switch (direction) {
            case 0: // Up
                headY -= speed;
                break;
            case 1: // Down
                headY += speed;
                break;
            case 2: // Left
                headX -= speed;
                break;
            case 3: // Right
                headX += speed;
                break;
        }

        // Create a new head segment with the updated position
        Point newHead = new Point(headX, headY);

        // Insert the new head segment at the beginning of the bodySegments list
        bodySegments.add(0, newHead);

        // Remove the tail segment to maintain the snake's length
        bodySegments.remove(bodySegments.size() - 1);
    }

    public void grow() {
        // To make the snake grow, add a new segment at the current tail position
        Point tail = bodySegments.get(bodySegments.size() - 1);
        bodySegments.add(new Point(tail.x, tail.y));
    }

    public void draw(Graphics g) {
        g.setColor(new Color(115, 150, 240));

        for (Point segment : bodySegments) {
            int segmentX = segment.x;
            int segmentY = segment.y;
            g.fillRect(segmentX, segmentY, 30, 30);
        }
    }
}
