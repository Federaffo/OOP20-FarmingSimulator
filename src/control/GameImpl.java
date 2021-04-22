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
     * {@inheritDoc}
     */
    @Override
    public void loadGame(final MapImpl map, final PlayerImpl player) {
        this.pg = player;
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loop() {
        pg.move();
        pg.checkCollision(map.getMapSet(), x -> x.isWalkable());
        animals.forEach(x -> x.randomMove(map.getMapSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map getMap() {
        return this.map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.pg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shop getShop() {
        return this.shop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buy(final SeedType st, final int quantity) {
        return interaction.playerBuy(pg, st, quantity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sellAll() {
        return interaction.playerSell(shop, pg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interact() {
        Block temp = pg.getBlockPosition(map.getMapSet());
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
     * {@inheritDoc}
     */
    @Override
    public double getUnlockPrice() {
        return this.unlockPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public void play() {
        state = GameState.PLAY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shop() {
        if (state == GameState.SHOP) {
            state = GameState.PLAY;
        } else {
            state = GameState.SHOP;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info() {
        if (state == GameState.INFO) {
            state = GameState.PLAY;
        } else {
            state = GameState.INFO;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Animal> getAllAnimals() {
        return this.animals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
