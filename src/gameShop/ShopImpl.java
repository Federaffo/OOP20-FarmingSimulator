package gameShop;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import item.FoodType;
import item.Seed;
import item.SeedImpl;
import item.SeedType;

public class ShopImpl implements Shop {

    private final Set<SeedType> seedItemList = new HashSet<>();
    private final Set<FoodType> foodItemList = new HashSet<>();

    public ShopImpl() {
        /* carico la set di Food(enum) */
        for (FoodType f : FoodType.values()) {
            foodItemList.add(f);
        }
        /* carico la set di SeedType(enum) */
        for (SeedType st : SeedType.values()) {
            seedItemList.add(st);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<SeedType> getSeedItemList() {
        return seedItemList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Set<FoodType> getFoodItemList() {
        return foodItemList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sell(final FoodType fd) {
        return fd.getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sellAll(final Map<FoodType, Integer> map) {
        double somma = 0;
        for (FoodType f : map.keySet()) {
            for (int i = 0; i < map.get(f); i++) {
                somma += f.getPrice();
            }
        }
        return somma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Seed buy(final SeedType st) {
        return new SeedImpl((SeedType) st);
    }
}
