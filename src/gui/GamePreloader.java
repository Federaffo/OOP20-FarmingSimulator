package gui;

import javax.swing.JOptionPane;

import utils.Observable;
import utils.Observer;

public class GamePreloader implements Observable<Boolean> {
	public Observer obs;

	@Override
	public void notifyObserver(Boolean loadLastGame) {
		this.obs.update(loadLastGame);
	}

	@Override
	public void addObserver(Observer obs) {
		this.obs = obs;
	}

	public void askToLoad() {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to load the last game?", "Load",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			notifyObserver(true);
		} else {
			notifyObserver(false);
		}
	}
}