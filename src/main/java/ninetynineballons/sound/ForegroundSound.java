package ninetynineballons.sound;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ForegroundSound {
    private static final int EXTERNAL_BUFFER_SIZE = 524288;
    private final byte[] buffer = new byte[EXTERNAL_BUFFER_SIZE];

    public void playBalloonPoppingSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        var soundFile = ForegroundSound.class.getResource("/popballoon.wav");

        if (soundFile != null) {
            playBalloonPoppingSound(soundFile);
        }

    }

    private void playBalloonPoppingSound(URL soundFile) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        var audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        var format = audioInputStream.getFormat();
        var dataLine = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, format));
        dataLine.open(format);
        dataLine.start();
        var nBytesRead = 0;

        try {

            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(buffer, 0, buffer.length);

                if (nBytesRead >= 0) {
                    dataLine.write(buffer, 0, nBytesRead);
                }

            }

        } finally {
            dataLine.drain();
            dataLine.close();
        }

    }

}