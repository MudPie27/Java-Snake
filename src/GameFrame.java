// GameFrame class defines the game window

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

  GamePanel panel;
  // details for the game window that pops up
  public GameFrame() {
    panel = new GamePanel(); 
    this.add(panel);
    this.setTitle("Snake"); 
    this.setResizable(false); 
    this.setBackground(Color.pink);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
  
}