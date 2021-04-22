package gameShop;

import java.util.Map;
import java.util.Set;

import item.FoodType;
import item.Seed;
import item.SeedImpl;
import item.SeedType;

public interface Shop {
    /**
     * @return the list of the seed
     */
    Set<SeedType> getSeedItemList();

    /**
     * @return the list of the food
     */
    Set<FoodType> getFoodItemList();

    /**
     * @param fd
     * @return the money that player have done
     */
    double sell(FoodType fd);

    /**
     * @param foodToSell
     * @return the money that the Player have done
     */
    double sellAll(Map<FoodType, Integer> foodToSell);

    /**
     * @param st
     * @return the Seed that player buy
     */
    Seed buy(SeedType st);
}
