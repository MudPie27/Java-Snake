import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {

    private static final int INITIAL_LENGTH = 5;

    private int[] x;
    private int[] y;
    private int length;
    private char direction;

    public Snake() {
        x = new int[GamePanel.NUMBER_OF_UNITS];
        y = new int[GamePanel.NUMBER_OF_UNITS];
        length = 0;
        direction = 'D';
    }

    public void init() {
        length = INITIAL_LENGTH;
        for (int i = 0; i < length; i++) {
            x[i] = 0;
            y[i] = 0;
        }
    }

    public void move() {
        for (int i = length; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (direction == 'L') {
            x[0] -= GamePanel.UNIT_SIZE;
        } else if (direction == 'R') {
            x[0] += GamePanel.UNIT_SIZE;
        } else if (direction == 'U') {
            y[0] -= GamePanel.UNIT_SIZE;
        } else if (direction == 'D') {
            y[0] += GamePanel.UNIT_SIZE;
        }
    }

    public boolean checkSelfCollision() {
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision() {
        return x[0] < 0 || x[0] >= GamePanel.WIDTH || y[0] < 0 || y[0] >= GamePanel.HEIGHT;
    }

    public void increaseLength() {
        length++;
    }

    public void setDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;

            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;

            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

    public Point getHead() {
        return new Point(x[0], y[0]);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.white);
        for (int i = 0; i < length; i++) {
            graphics.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
        }
    }
}
