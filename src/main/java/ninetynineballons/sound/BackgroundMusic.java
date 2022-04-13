package ninetynineballons.sound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.IOException;

/**
 * Sound includes MIDI music.
 *
 */
public class BackgroundMusic implements Runnable, AutoCloseable {

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

    @Override
    public void run() {

        if (sequencer != null) {
            sequencer.start();
        }

    }
}
