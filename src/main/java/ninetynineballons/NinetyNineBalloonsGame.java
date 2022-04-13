package ninetynineballons;

import ninetynineballons.graphics.ShowGameFrame;
import ninetynineballons.sound.GameSound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;

public class NinetyNineBalloonsGame {

    public static void main(String []args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        ShowGameFrame.showGameFrame();
        GameSound.playMusicInBackground();
    }
}
