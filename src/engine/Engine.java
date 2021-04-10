package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	
	public void CreateGame() {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to load the last game?", "Load", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			this.game = new GameSaver().load();
			this.game.growAllSeed();
		}else {
			this.game = new Game();
		}
		
		start();
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

			if(gameState != game.getState()) {
				gameState = game.getState();
				window.setWindow(gameState);
			}
		}
	}

//	private class Loader extends JFrame{
//		public Loader() {
//			setTitle("Farming Simulator");
//			setResizable(false);
//			setVisible(true);
//			setLocationRelativeTo(null);
//			createPanel();
//			pack();
//		}
//
//		private void createPanel() {
//			JPanel panel = new JPanel();
//			JLabel label = new JLabel("Vuoi caricare l'ultima partita?");
//			JButton load = new JButton("Si");
//			JButton restart = new JButton("No");
//			
//			load.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					game = new GameSaver().load();
//					closeAndStart();
//				}
//
//			});
//			restart.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					game = new Game();
//					closeAndStart();
//				}
//			});
//			
//			panel.add(label);
//			panel.add(load);
//			panel.add(restart);
//			add(panel);
//		}
//		private void closeAndStart() {
//			setVisible(false); //you can't see me!
//			dispose(); 
//			start();
//		}
//	}

}
