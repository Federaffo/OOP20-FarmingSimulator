package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import gui.GamePreloader;
import gui.MusicPlayer;
import gui.WindowManager;
import item.SeedType;
import utils.Observer;

public class Engine implements Observer<Boolean> {

	private MusicPlayer player;
	private WindowManager window;
	private Timer timer;
	private Game game;
	private GameState gameState = GameState.PLAY;

	@Override
	public void update(Boolean loadLastGame) {
		GameSaver gameSaver = new GameSaver();
		if(gameSaver.isSavingPresent() && loadLastGame) {
			this.game = gameSaver.load();
			this.game.growAllSeed();
		} else {
			this.game = new Game();
		}
		this.start();
	}

	public void CreateGame() {
		GamePreloader preloader = new GamePreloader();
		preloader.addObserver(this);
		preloader.askToLoad();
	}

	public void start() {

		player = new MusicPlayer();
		window = new WindowManager(game);
		timer = new Timer(16, new GameLoop());

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

			if (gameState != game.getState()) {
				gameState = game.getState();
				window.setWindow(gameState);
			}
		}
	}
}
