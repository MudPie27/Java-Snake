import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Score {

    // variables
    public static int points, highScore;
    private Font pointFont, GOFont;

    private FileWriter writer;
    private List<String> fileLines;

    // constructor
    public Score() {
        pointFont = new Font("Lato", Font.PLAIN, 30);
        GOFont = new Font("Arial", Font.BOLD, 80);
        points = 0;

        try {
            fileLines = Files.readAllLines(Paths.get(GamePanel.hsFile));
            highScore = Integer.parseInt(fileLines.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method that returns the scores
    public int getPoints() {
        return points;
    }

    // methods that counts the scores and checks if the game is over
    public void updatePoints(boolean ga) {
        if (ga)
            points += 2;
        else
            points++;
    }

    // save the final score to a file
    public void saveHighScore() {
        if (points > highScore) {
            try {
                writer = new FileWriter(GamePanel.hsFile);
                writer.write(Integer.toString(points));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // display the scores
    public void draw(Graphics g) {

        g.setColor(Color.white);
        g.setFont(pointFont);
        g.drawString("Controls: \u2191 \u2192 \u2193 \u2190", 5, 30);
        g.drawString("High Score: " + highScore, 600, 30);
        g.drawString("" + points, 762, 60);

        if (GamePanel.gameOver) {
            g.setFont(GOFont);
            g.drawString("Game Over", ((GamePanel.WIDTH / 2) - 210), (GamePanel.HEIGHT / 2));
            saveHighScore();
        }
    }
}