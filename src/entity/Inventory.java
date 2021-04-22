package entity;

import java.util.Map;
import java.util.Optional;

import item.FoodType;
import item.SeedType;

public interface Inventory {

    // MANAGE SEEDS

    /**
     * @return the next seedType as the activeSeed
     */
    Optional<Pair<SeedType, Integer>> nextSeed();

    /**
     * This method add seeds to inventory.
     * 
     * @param type
     * @param number
     */
    void addSeeds(SeedType type, Integer number);


    /**
     * This method remove 1 seed from inventory.
     * @param type
     */
    void removeSeed(SeedType type);


    /**
     * @param type
     * @param number
     * @return true if there's seed of the given type and number in the inventory
     */
    boolean gotSeeds(SeedType type, Integer number);


    /**
     * @return map of seeds
     */
    Map<SeedType, Integer> getSeeds();


    /**
     * @return current seed
     */
    Optional<Pair<SeedType, Integer>> getCurrentSeed();

    // MANAGE FOODS


    /**
     * This method add foods to inventory.
     * @param type
     * @param number
     */
    void addFoods(FoodType type, Integer number);


    /**
     * This method remove 1 food from inventory.
     * @param type
     */
    void removeFood(FoodType type);


    /**
     * @param type
     * @param number
     * @return if there's any food of the given type and number in the inventory
     */
    boolean gotFoods(FoodType type, Integer number);


    /**
     * @return map of foods
     */
    Map<FoodType, Integer> getFoods();


    /**
     * This method remove all the food from inventory.
     */
    void removeAllFood();
}
