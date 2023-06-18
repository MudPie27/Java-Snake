import java.awt.*;

public class Score {

    // variables
    public static int points;
    private Font pointFont;

    // constructor
    public Score() {
        pointFont = new Font("Arial", Font.PLAIN, 30);
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
            points+=2;
        else 
            points++;
    }

    // display the scores
    public void draw(Graphics g) {

        g.setColor(Color.white);
        g.setFont(pointFont);
        g.drawString("" + points, 750, 50);
    }
}