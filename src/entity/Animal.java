package entity;

import java.util.Set;

import block.Block;
import item.FoodType;

public interface Animal extends Entity {

    /**
     * @return the food of the animal
     */
    Pair<FoodType, Integer> collect();

    /**
     * move the animal in a random direction.
     * 
     * @param map
     * 
     */
    void randomMove(Set<Block> map);


    /**
     * @return the type of the animal
     */
    AnimalType getType();

    /**
     * @return true if the animal is ready
     */
    boolean isReady();
}
