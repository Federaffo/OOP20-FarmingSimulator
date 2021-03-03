package gui;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer implements Runnable{
	
	public MusicPlayer() {
		
	}

	@Override
	public void run() {
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(Resources.getMainTheme().toURI().toURL());
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}  
	}

}
