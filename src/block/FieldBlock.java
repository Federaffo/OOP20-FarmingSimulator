package block;

import entity.Pair;
import item.FoodType;
import item.Seed;
import item.SeedType;

public interface FieldBlock extends Block {

    /**
     * @param st A SeedType we want to plant
     */
    void plant(SeedType st);

    /**
     * @return a Pair<FoodType,Integer> giving the relative FoodType and quantity of
     *         the harvested plant
     */
    Pair<FoodType, Integer> harvest();

    /**
     * @return true if there no seed planted int this block, false otherwise
     */
    boolean isEmpty();

    /**
     * @return the Seed currently planted in the Block
     */
    Seed getSeed();
}
