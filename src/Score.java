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
        // font details 
        pointFont = new Font("Lato", Font.PLAIN, 30);
        GOFont = new Font("Arial", Font.BOLD, 80);

        points = 0; // starts at 0 points

        // reads the HighScore file to get the high score
        try {
            fileLines = Files.readAllLines(Paths.get(GamePanel.hsFile));
            highScore = Integer.parseInt(fileLines.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // methods that counts the scores
    public void updatePoints(boolean ga) {
        if (ga)
            points += 2; // 2 points if golden apple
        else
            points++; // 1 point if regular apple
    }

    // save the final score to a file
    public void saveHighScore() {
        if (points > highScore) { // if the latest player score is higher than the overall high score
            try {
                writer = new FileWriter(GamePanel.hsFile);
                writer.write(Integer.toString(points));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // draw method
    public void draw(Graphics g) {
        // displays control, points and high score information
        g.setColor(Color.white);
        g.setFont(pointFont);
        g.drawString("Controls: \u2191 \u2192 \u2193 \u2190", 5, 30);
        g.drawString("High Score: " + highScore, 600, 30);
        g.drawString("" + points, 762, 60);
        // displays game over text is the game ends
        if (GamePanel.gameOver) {
            g.setFont(GOFont);
            g.drawString("Game Over", ((GamePanel.WIDTH / 2) - 210), (GamePanel.HEIGHT / 2));
            saveHighScore();
        }
    }
}