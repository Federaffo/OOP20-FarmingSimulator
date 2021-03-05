package engine;

import java.awt.event.KeyEvent;

import entity.Pair;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FactoryBlock;
import gameMap.FieldBlock;
import gameMap.Map;
import item.Food;
import item.SeedState;
import item.SeedType;

public class Game {
	private Player pg = new Player(new Pair<>(1, 1));
	private Map map = new Map(32, 18);

	public void loop() {
		pg.move();
	}

	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return this.pg;
	}

	public void interact() {
		Block temp = map.getBlock(0, 0);
		if (temp.getType() == BlockType.FIELD) {
			FieldBlock myBlock = (FieldBlock) temp;
			// pg.getInventory().addSeeds(SeedType.CARROT_SEED, 10);
			if (myBlock.isEmpty()) {
				if (pg.getInventory().getCurrentSeed().isPresent()) {
					SeedType st = pg.getInventory().getCurrentSeed().get().getX();
					myBlock.plant(st);
					pg.getInventory().removeSeed(st);
				}
			} else {
				if (myBlock.getSeed().getSeedState() == SeedState.GROWN) {
					Pair<Food, Integer> food = myBlock.harvest();
					pg.getInventory().addFoods(food.getX(), food.getY());
				}
			}
		}

	}

}
