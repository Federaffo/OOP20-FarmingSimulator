package gui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer implements Runnable{
	
	public MusicPlayer() {
		
	}

	@Override
	public void run() {
//		AudioInputStream audioIn;
//		try {
//			audioIn = AudioSystem.getAudioInputStream(Resources.getMainTheme());
//			Clip clip = AudioSystem.getClip();
//			clip.open(audioIn);
//			clip.loop(clip.LOOP_CONTINUOUSLY);
//			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//			gainControl.setValue(-25.0f); // Reduce volume by 10 decibels.
//			clip.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//			e.printStackTrace();
//		}  
		
		
		try (InputStream in = getClass().getResourceAsStream("/hd.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    			gainControl.setValue(-25.0f); // Reduce volume by 10 decibels.
                clip.start();
            }
        } catch (Exception e) {
           e.printStackTrace();
       }
	}



}
