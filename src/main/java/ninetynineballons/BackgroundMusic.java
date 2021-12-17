package ninetynineballons;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sound includes MIDI music.
 *
 * @author Valdgir
 * @version $Id$
 */
public class BackgroundMusic implements Runnable, AutoCloseable {
    private static final Logger LOG = Logger.getLogger(BackgroundMusic.class.getName());

    private Sequencer sequencer;

    public BackgroundMusic() throws MidiUnavailableException, InvalidMidiDataException, IOException {
        var midiFile = BackgroundMusic.class.getResource("/99luftballons.mid");

        if (midiFile != null) {
            sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();
        }

    }

    /**
     * Close the MidiDevice & free resources
     */
    @Override
    public void close() {

        if (sequencer != null) {
            sequencer.stop();
            sequencer.close();
        }

    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    @Override
    public void run() {

        if (sequencer != null) {
            sequencer.start();
        }

    }
}
