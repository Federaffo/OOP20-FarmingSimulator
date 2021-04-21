package block;

import entity.Pair;
import item.FoodType;
import item.Seed;
import item.SeedType;

public interface FieldBlock extends Block {
    void plant(SeedType st);
    Pair<FoodType, Integer> harvest();
    boolean isEmpty();
    Seed getSeed();
}
