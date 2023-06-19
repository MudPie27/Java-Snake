// class that handles music 

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// For playing sound in game
public class Music {
    // variable
    private Clip backgroundMusic;

    // constructor 
    public Music() {
        loadSounds();
    }

    // prepares the soundfile using InputStream and Clip imports
    private void loadSounds() {

        try {

            AudioInputStream bgmSound = AudioSystem.getAudioInputStream(new File(GamePanel.bgmWav));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(bgmSound);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // plays the sound
    public void playBGM() {
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
}