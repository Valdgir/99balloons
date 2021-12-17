package ninetynineballons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
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
        var audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        var format = audioInputStream.getFormat();
        var auline = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, format));
        auline.open(format);
        auline.start();
        var nBytesRead = 0;

        try {

            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(buffer, 0, buffer.length);

                if (nBytesRead >= 0) {
                    auline.write(buffer, 0, nBytesRead);
                }

            }

        } finally {
            auline.drain();
            auline.close();
        }

    }

}