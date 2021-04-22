package control;

import java.util.List;

import engine.GameState;
import entity.Animal;
import entity.Player;
import entity.PlayerImpl;
import gameShop.Shop;
import item.SeedType;
import map.Map;
import map.MapImpl;

public interface Game {

    /**
     * @param map
     * @param player
     */
    void loadGame(MapImpl map, PlayerImpl player);

    /**
     * update all entities
     */
    void loop();

    /**
     * @return Map
     */
    Map getMap();

    /**
     * @return Player
     */
    Player getPlayer();

    /**
     * @return Shop
     */
    Shop getShop();

    /**
     * @param st
     * @param quantity
     * @return The interaction to buy [quantity] of [st]
     */
    boolean buy(SeedType st, int quantity);

    /**
     * @return The interaction to sell all food that player have got in his
     *         inventory
     */
    double sellAll();

    /**
     * @return the state of the Game
     */
    GameState getState();

    /**
     * This method manage the interaction between Player and FieldBlock.
     */
    void interact();

    /**
     * @return the Price to unlock LOCKED Block
     */
    double getUnlockPrice();

    /**
     * This method grow all seed.
     */
    void growAllSeed();

    /**
     * updates GameState and put it on PLAY.
     */
    void play();

    /**
     * updates GameState and put it on SHOP, or PLAY if was already SHOP.
     */
    void shop();

    /**
     * updates GameState and put it on INFO, or PLAY if was already INFO.
     */
    void info();

    /**
     * @return a List of the current Animals.
     */
    List<Animal> getAllAnimals();

    /**
     * clear current animals and generate new animals.
     */
    void resetAnimals();

}
