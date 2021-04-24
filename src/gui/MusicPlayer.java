package gui;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer implements Runnable {

    private static final float REDUCER = -30.0f;

    public MusicPlayer() {

    }

    /**
     * Start the music when called.
     */
    public void run() {
        try {
            InputStream bufferedIn = new BufferedInputStream(Resources.getRes().getMainTheme());
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(REDUCER); // Reduce volume by {REDUCER} decibels.
                clip.loop(clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
