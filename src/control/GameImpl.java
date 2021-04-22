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
import entity.Animal;
import entity.FactoryAnimal;
import entity.Pair;
import entity.Player;
import entity.PlayerImpl;
import gameShop.Shop;
import gameShop.ShopImpl;
import item.SeedType;
import map.Map;
import map.MapImpl;

/**
 *
 */
public class GameImpl implements Game {
    private static final double PRICE_START = 50.0;
    private static final int UNLOCK_STEP = 50;
    private Player pg = new PlayerImpl(new Pair<>(1, 1));
    private Map map = new MapImpl();
    private Shop shop = new ShopImpl();
    private Interaction interaction = new InteractionImpl();
    private List<Animal> animals = new ArrayList<>();
    private FactoryAnimal factoryAnimal = new FactoryAnimal();
    private GameState state = GameState.PLAY;
    private double unlockPrice = PRICE_START;

    public GameImpl() {
        generateAnimals();
        pg.getInventory().addSeeds(SeedType.WHEAT_SEED, 10);
        pg.incrementMoney(PRICE_START);
    }


    /**
     *@param map
     *@param player
     */
    public void loadGame(final MapImpl map, final PlayerImpl player) {
        this.pg = player;
        this.map = map;
    }

    /**
     *
     */
    public void loop() {
        pg.move();
        pg.checkCollision(map.getMapSet(), x -> x.isWalkable());
        animals.forEach(x -> x.randomMove(map.getMapSet()));
    }

 

    /**
     *@return Map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     *@return Player
     */
    public Player getPlayer() {
        return this.pg;
    }

    /**
     *@return Shop 
     */
    public Shop getShop() {
        return this.shop;
    }

    /**
     *@param st
     *@param quantity
     *@return The interaction to buy [quantity] of [st]
     */
    public boolean buy(SeedType st, int quantity) {
        return interaction.playerBuy(pg, st, quantity);
    }

    /**
     *@return The interaction to sell all food that player have got in his inventory
     */
    public double sellAll() {
        return interaction.playerSell(shop, pg);
    }

    /**
     *@return the state of the Game
     */
    public GameState getState() {
        return this.state;
    }

    /**
     *This method manage the interaction between Player and FieldBlock.
     */
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
        if (pg.nearestAnimal(animals).isPresent()) {
            interaction.playerAnimal(pg, pg.nearestAnimal(animals).get());
        }
    }

    /**
     *@return the Price to unlock LOCKED Block
     */
    public double getUnlockPrice() {
        return this.unlockPrice;
    }

    /**
     *This method grow all seed.
     */
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

    /**
     *
     */
    public void play() {
        state = GameState.PLAY;
    }

    /**
     *
     */
    public void shop() {
        if (state == GameState.SHOP) {
            state = GameState.PLAY;
        } else {
            state = GameState.SHOP;
        }
    }

    /**
     *
     */
    public void info() {
        if (state == GameState.INFO) {
            state = GameState.PLAY;
        } else {
            state = GameState.INFO;
        }
    }

    /**
     *
     */
    @Override
    public List<Animal> getAllAnimals() {
        return this.animals;
    }

    /**
     *
     */
    public void resetAnimals() {
        animals.clear();
        generateAnimals();
    }

    private void generateAnimals() {
        animals.add(factoryAnimal.getChicken(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall()))));
        animals.add(factoryAnimal.getCow(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall()))));
        animals.add(factoryAnimal.getPig(map.getBlockCoordinates(map.getRandomFilterBlock(x -> x.isStall()))));
    }

}
