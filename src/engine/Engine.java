package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import gui.MusicPlayer;
import gui.WindowManager;
import item.SeedType;

public class Engine {

	private MusicPlayer player;
	private WindowManager window;
	private Timer timer;
	private Game game;
	private GameState gameState = GameState.PLAY;
		
	public Engine() {
		player = new MusicPlayer();
		game = new Game();
		window = new WindowManager(game);
		timer = new Timer(16, new GameLoop());
	}

	public void start() {
		player.run();
		timer.start();
		
		game.getPlayer().getInventory().addSeeds(SeedType.POTATO_SEED, 10);
		game.getPlayer().getInventory().addSeeds(SeedType.CARROT_SEED, 10);
		game.getPlayer().getInventory().addSeeds(SeedType.WHEAT_SEED, 10);
		game.getPlayer().getInventory().addSeeds(SeedType.TOMATO_SEED, 10);

	}

	private class GameLoop implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			game.loop();
//			window.setWindow(gameState);
			
			
			
			if(gameState != game.getState()) {
				gameState = game.getState();
				window.setWindow(gameState);
			}
		}
	}

	

}
