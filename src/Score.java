import java.awt.*;

public class Score {

    // variables
    public static int points;
    private Font pointFont, GOFont;

    // constructor
    public Score() {
        pointFont = new Font("Arial", Font.BOLD, 30);
        GOFont = new Font("Arial", Font.BOLD, 80);
        points = 0;
    }

    // method that returns the scores
    public int getPoints() {
        return points;
    }

    // methods that counts the scores and checks if the game is over
    public void updatePoints(boolean ga) {
        System.out.println(ga);
        if (ga)
            points += 2;
        else
            points++;
    }

    // display the scores
    public void draw(Graphics g) {

        g.setColor(Color.white);
        g.setFont(pointFont);
        g.drawString("" + points, 750, 50);

        if (GamePanel.gameOver) {
            g.setFont(GOFont);
            g.drawString("Game Over", ((GamePanel.WIDTH / 2) - 210), (GamePanel.HEIGHT / 2));
        }
    }
}