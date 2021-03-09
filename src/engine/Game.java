package engine;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import entity.Pair;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FactoryBlock;
import gameMap.FieldBlock;
import gameMap.Map;
import gameShop.Shop;
import item.Food;
import item.SeedState;
import item.SeedType;

public class Game {

	private Player pg = new Player(new Pair<>(1, 1));
	private Map map = new Map(32, 18);
	private Shop shop = new Shop();
	private GameState state = GameState.PLAY;

	public void loop() {
		pg.move();
	}

	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return this.pg;
	}

	public void sellAll() {
		double money = shop.sellAll(pg.getInventory().getFood());
		pg.incrementMoney(money);
		pg.getInventory().removeAllFood();
	}

	public GameState getState() {
		return this.state;
	}

	public void interact() {

		Block temp = pg.blockPosition(map.getMapSet());
		if (temp.getType() == BlockType.FIELD) {
			FieldBlock myBlock = (FieldBlock) temp;

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

	public void play() {
		state = GameState.PLAY;
	}
	
	public void shop() {
		if (state == GameState.SHOP) {
			state = GameState.PLAY;
		} else {
			state = GameState.SHOP;
		}
	}

	public void info() {
		if(state == GameState.INFO) {
			state = GameState.PLAY;
		}else {
			state = GameState.INFO;
		}
	}

}
