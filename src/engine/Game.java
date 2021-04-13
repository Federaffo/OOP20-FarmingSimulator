package engine;

import javax.swing.JOptionPane;

import entity.Pair;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gameMap.Map;
import gameMap.UnlockableBlock;
import gameShop.Shop;
import item.Food;
import item.SeedState;
import item.SeedType;

public class Game {
	private Player pg = new Player(new Pair<>(1, 1));
	private Map map = new Map();
	private Shop shop = new Shop();
	private GameState state = GameState.PLAY;
	private double unlockPrice = 50;

	public void loadGame(Map map, Player player) {
		this.pg = player;
		this.map = map;
	}

	public void loop() {
		pg.move();
		pg.checkCollision(map.getMapSet());
	}

	public Map getMap() {
		return this.map;
	}

	public Player getPlayer() {
		return this.pg;
	}

	public Shop getShop() {
		return this.shop;
	}

	public boolean buy(SeedType st, int quantity) {
		if (pg.getMoney() >= st.getPrice() * quantity) {
			pg.getInventory().addSeeds(st, quantity);
			pg.decrease(st.getPrice() * quantity);
			return true;
		}
		return false;
	}

	public double sellAll() {
		double money = shop.sellAll(pg.getInventory().getFood());
		pg.incrementMoney(money);
		pg.getInventory().removeAllFood();
		return money;
	}

	public GameState getState() {
		return this.state;
	}

	public void interact() {

		Block temp = pg.blockPosition(map.getMapSet());
		// controllo se il blocco è di tipo UnlockBlock
		if (temp instanceof UnlockableBlock) {
			// controllo se il blocco è bloccato

			if (((UnlockableBlock) temp).isLocked()) {
				// controllo se il Player ha abbastanza soldi per permettersi di sbloccare il
				// blocco
				int pay = JOptionPane.showConfirmDialog(null,
						"Do you want to unlock this block for " + unlockPrice + " money ?", "Purchase Locked Block",
						JOptionPane.YES_NO_OPTION);

				// dopo aver pagato per lo sbloccaggio del blocco trasformo il blocco da
				// UnlockBlock a FieldBlock
				if (pay == JOptionPane.YES_OPTION) {
					if (pg.getMoney() >= unlockPrice) {
						((UnlockableBlock) temp).unlockBlock();
						Pair<Integer, Integer> blockPos = map.getBlockPosition(temp);
						map.setBlock(blockPos.getX(), blockPos.getY(), BlockType.FIELD);

						pg.decrease(unlockPrice); // decremento i soldi del Player
						unlockPrice += 50; // aumento il prezzo del prossimo blocco
					} else {
						JOptionPane.showMessageDialog(null, "You don't have enough money!");
					}
				}
			}
		} else {
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
	}

	public void growAllSeed() {
		for (Block block : map.getMapSet()) {
			if(block instanceof FieldBlock) {
				FieldBlock field = (FieldBlock) block;
				if(!field.isEmpty()) {
					field.getSeed().Grow();
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
		if (state == GameState.INFO) {
			state = GameState.PLAY;
		} else {
			state = GameState.INFO;
		}
	}

}
