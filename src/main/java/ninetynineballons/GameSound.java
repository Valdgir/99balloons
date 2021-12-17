package ninetynineballons;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Support for the game sound
 */
public class GameSound implements Runnable {

    private static final Logger LOG = Logger.getLogger(GameSound.class.getName());

    public static final int POPS = 5;

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        var music = new BackgroundMusic();
        var musicThread = new Thread(music);
        musicThread.start();
        var sound = new GameSound();
        var soundThread = new Thread(sound);
        soundThread.start();
    }

    public void playBalloonPoppingSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            LOG.log(Level.FINE, e.getMessage(), e);
        }

        var sound = new ForegroundSound();
        sound.playBalloonPoppingSound();
    }

    @Override
    public void run() {

        for (int i = 0; i < POPS; i++) {

            try {
                playBalloonPoppingSound();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }

        }

    }

}
