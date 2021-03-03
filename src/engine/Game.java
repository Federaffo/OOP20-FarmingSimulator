package engine;

import java.awt.event.KeyEvent;
import java.util.Map;

import entity.Inventory;
import entity.Pair;
import entity.Player;

public class Game {
	private Player pg = new Player(new Pair<>(1, 1));
	private Map map;

	public void loop() {
		pg.move();
	}

	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return this.pg;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			pg.setUp(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			pg.setLeft(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			pg.setDown(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			pg.setRight(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			pg.setUp(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			pg.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			pg.setDown(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			pg.setRight(false);
		}

	}

}
