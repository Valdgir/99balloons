package ninetynineballons;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.IOException;

/**
 * Sound includes MIDI music.
 *
 * @author Valdgir
 * @version $Id$
 */
public class BackgroundMusic {
    private Sequencer sequencer;

    public BackgroundMusic() throws MidiUnavailableException, InvalidMidiDataException, IOException {
        var midiFile = BackgroundMusic.class.getResource("/99luftballons.mid");

        if (midiFile != null) {
            sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();
            sequencer.start();
        }

    }

    /**
     * Close the MidiDevice & free resources
     */
    public void close() {

        if (sequencer != null) {
            sequencer.stop();
            sequencer.close();
        }

    }

    public Sequencer getSequencer() {
        return sequencer;
    }

}
