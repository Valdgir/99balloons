package ninetynineballons.sound;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Support for the game sound
 */
public class GameSound {

    public static void playMusicInBackground() throws MidiUnavailableException, InvalidMidiDataException, IOException {
        var music = new BackgroundMusic();
        var musicThread = new Thread(music);
        musicThread.start();
    }

    public static void playBalloonPoppingSound() {

        try {
            var sound = new ForegroundSound();
            sound.playBalloonPoppingSound();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

    }

}
