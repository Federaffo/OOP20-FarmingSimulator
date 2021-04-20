package control;

import java.util.ArrayList;
import java.util.List;

import block.Block;
import block.BlockType;
import block.FieldBlock;
import block.UnlockableBlock;
import engine.GameState;
import engine.Interaction;
import engine.InteractionImpl;
import entity.AnimalImpl;
import entity.Animal;
import entity.AnimalType;
import entity.FactoryAnimal;
import entity.Pair;
import entity.Player;
import entity.PlayerImpl;
import gameMap.MapImpl;
import gameMap.Map;
import gameShop.Shop;
import gameShop.ShopImpl;
import item.SeedType;

public class GameImpl implements Game{
	private static final int UNLOCK_STEP = 50;
	private Player pg = new PlayerImpl(new Pair<>(1, 1));
	private Map map = new MapImpl();
	private Shop shop = new ShopImpl();
	private Interaction interaction = new InteractionImpl();
	private List<Animal> animals = new ArrayList<>();
	private FactoryAnimal factoryAnimal = new FactoryAnimal();
	private GameState state = GameState.PLAY;
	private double unlockPrice = 50.0;
	
	public GameImpl() {
		generateAnimals();
		pg.getInventory().addSeeds(SeedType.WHEAT_SEED, 5);
		pg.getInventory().addSeeds(SeedType.WHEAT_SEED, 5);

	}


	public void loadGame(MapImpl map, PlayerImpl player) {
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
			unlockPrice += UNLOCK_STEP; // aumento il prezzo del prossimo blocco
		}
		if(pg.nearestAnimal(animals).isPresent()) {
			interaction.playerAnimal(pg, pg.nearestAnimal(animals).get());
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
		return this.animals;
	}
	
	public void generateAnimal(Pair<Integer, Integer> pos, AnimalType type) {
        animals.add(factoryAnimal.generateAnimal(pos, type));
    }
	
	public void resetAnimals() {
		animals.clear();
		generateAnimals();
	}
	
	private void generateAnimals() {
		this.generateAnimal(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall())), AnimalType.CHICKEN);
		this.generateAnimal(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall())), AnimalType.COW);
		this.generateAnimal(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall())), AnimalType.PIG);
	}

}
