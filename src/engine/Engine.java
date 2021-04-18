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

	private static final int TICK_TIME = 20;
	private MusicPlayer player;
	private WindowManager window;
	private Timer timer;
	private Game game = new GameImpl();
	private GameState gameState = GameState.PLAY;
	GameSaver gameSaver = new GameSaver();

	@Override
	public void update(Boolean loadLastGame) {
		if(loadLastGame) {
			this.game = gameSaver.load();
			this.game.growAllSeed();
		} else {
			this.game = new GameImpl();
		}
		
		this.start();
	}

	public void CreateGame() {
		if(gameSaver.isSavingPresent()) {
			GamePreloader preloader = new GamePreloader();
			preloader.addObserver(this);
			preloader.askToLoad();
		}else {
			this.start();
		}
		
	}

	public void start() {

		player = new MusicPlayer();
		window = new WindowManager(game);
		timer = new Timer(TICK_TIME, new GameLoop());

		player.run();
		timer.start();



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
