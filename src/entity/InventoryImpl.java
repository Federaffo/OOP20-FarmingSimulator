package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;

import item.FoodType;
import item.SeedType;

public class InventoryImpl implements Inventory {
    private Map<SeedType, Integer> seeds;
    private Map<FoodType, Integer> foods;
    private Queue<SeedType> activeSeed;

    // build a void inventory
    public InventoryImpl() {
        seeds = new HashMap<>();
        foods = new HashMap<>();

        for (SeedType s : SeedType.values()) {
            seeds.put(s, 0);
        }
        for (FoodType f : FoodType.values()) {
            foods.put(f, 0);
        }

        activeSeed = new LinkedBlockingDeque<SeedType>();
        Stream.of(SeedType.values()).forEach(x -> activeSeed.add(x));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<SeedType, Integer>> nextSeed() {
        boolean isThereASeed = false;
        moveBottom(activeSeed.element());

        for (SeedType seedType : activeSeed) {
            if (seeds.get(seedType) > 0) {
                moveTop(seedType);
                isThereASeed = true;
                break;
            }
        }

        if (!isThereASeed) {
            return Optional.empty();
        } else {
            return Optional.of(new Pair<SeedType, Integer>(activeSeed.element(), seeds.get(activeSeed.element())));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<SeedType, Integer>> getCurrentSeed() {
        if (seeds.get(activeSeed.element()) > 0) {
            return Optional.of(new Pair<SeedType, Integer>(activeSeed.element(), seeds.get(activeSeed.element())));
        } else {
            return Optional.empty();
        }
    }

    // move the object to the bottom
    private void moveBottom(final SeedType type) {
        activeSeed.remove(type);
        activeSeed.add(type);
    }

    // move the object to the top
    private void moveTop(final SeedType type) {
        for (SeedType seedType : List.copyOf(activeSeed)) {
            if (!seedType.equals(type)) {
                moveBottom((SeedType) seedType);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSeeds(final SeedType type, final Integer number) {
        seeds.put(type, seeds.get(type) + number);
        moveTop(type); // set the activeSeed to the seed added to inventory
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSeed(final SeedType type) {
        seeds.put(type, seeds.get(type) - 1);
        if (seeds.get(type) == 0) {

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean gotSeeds(final SeedType type, final Integer number) {
        if (seeds.get(type) >= number) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFoods(final FoodType type, final Integer number) {
        foods.put(type, foods.get(type) + number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFood(final FoodType type) {
        foods.put(type, foods.get(type) - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean gotFoods(final FoodType type, final Integer number) {
        if (foods.get(type) >= number) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<FoodType, Integer> getFoods() {
        return this.foods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<SeedType, Integer> getSeeds() {
        return this.seeds;
    }

    // rimuove tutto il cibo nell'inventario

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllFood() {
        for (var food : foods.keySet()) {
            if (food instanceof FoodType) {
                foods.put(food, 0);
            }
        }
    }
}
