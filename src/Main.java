/*
 ICS4U1-3 Summative 
 Snake Game
 Aseer & Kabir
 05/06/2023
*/

// client class

class Main { 
    // main method that runs the program
    public static void main(String[] args) {
      // calls background music
      Music sfx = new Music();
      sfx.playBGM();
      new GameFrame();
    }
  }