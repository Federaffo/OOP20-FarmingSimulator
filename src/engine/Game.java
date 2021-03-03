package engine;

import java.awt.event.KeyEvent;

import entity.Pair;
import entity.Player;
import gameMap.Map;

public class Game {
	private Player pg = new Player(new Pair<>(1, 1));
	private Map map =  new Map(30, 30); 

	public void loop() {
		pg.move();
	}

	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return this.pg;
	}

	

}
