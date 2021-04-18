package engine;

import java.util.ArrayList;
import java.util.List;

import entity.Animal;
import entity.AnimalType;
import entity.FactoryAnimal;
import entity.Pair;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gameMap.Map;
import gameMap.UnlockableBlock;
import gameShop.Shop;
import gameShop.ShopImpl;
import item.SeedType;

public class GameImpl implements Game{
	private Player pg = new Player(new Pair<>(1, 1));
	private Map map = new Map();
	private Shop shop = new ShopImpl();
	private Interaction interaction = new InteractionImpl();
	private List<Animal> animals = new ArrayList<Animal>();
	private FactoryAnimal factoryAnimal = new FactoryAnimal();
	private GameState state = GameState.PLAY;
	private double unlockPrice = 50.0;

	public void loadGame(Map map, Player player) {
		this.pg = player;
		this.map = map;
	}

	public void loop() {
		pg.move();
		pg.checkCollision(map.getMapSet(), x -> x.isWalkable());
		animals.forEach(x -> x.randomMove(map.getMapSet()));
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
		return interaction.playerBuy(pg, st, quantity);
	}

	public double sellAll() {
		return interaction.playerSell(shop, pg);
	}

	public GameState getState() {
		return this.state;
	}

	public void interact() {
		Block temp = pg.blockPosition(map.getMapSet());
		if (!(temp instanceof UnlockableBlock)) {

			if (temp.getType() == BlockType.FIELD) {
				FieldBlock myBlock = (FieldBlock) temp;
				interaction.fieldInteraction(pg, myBlock);
			}
		} else if (pg.getMoney() >= unlockPrice) {
			interaction.unlockBlock(pg, map, temp);
			pg.decreaseMoney(unlockPrice); // decremento i soldi del Player
			unlockPrice += 25; // aumento il prezzo del prossimo blocco
		}
		if(pg.whichAnimalWithPlayer(animals).isPresent()) {
			interaction.playerAnimal(pg, pg.whichAnimalWithPlayer(animals).get());
		}
	}
	
	public double getUnlockPrice() {
		return this.unlockPrice;
	}

	public void growAllSeed() {
		for (Block block : map.getMapSet()) {
			if (block instanceof FieldBlock) {
				FieldBlock field = (FieldBlock) block;
				if (!field.isEmpty()) {
					field.getSeed().grow();
				}
			}
		}
	}

	public void play() {
		state = GameState.PLAY;
	}

	public void shop() {
		this.generateAnimal(new Pair<>(30, 16), AnimalType.CHICKEN);
		this.generateAnimal(new Pair<>(30, 16), AnimalType.COW);
		this.generateAnimal(new Pair<>(30, 16), AnimalType.PIG);
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

	@Override
	public List<Animal> getAllAnimals() {
		// TODO Auto-generated method stub
		return this.animals;
	}
	
	public void generateAnimal(Pair<Integer, Integer> pos, AnimalType type) {
        animals.add(factoryAnimal.generateAnimal(pos, type));
    }

}
