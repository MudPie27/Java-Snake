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