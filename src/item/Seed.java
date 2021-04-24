package item;

public interface Seed {
    /**
     * Grow the current Seed.
     */
    void grow();

    /**
     * @return The FoodType of the Seed return after harvest
     */
    FoodType harvest();

    /**
     * @return The state of the seed
     */
    SeedState getSeedState();

    /**
     * @return The grow time
     */
    double getGrowTime();

    /**
     * @return The FoodType related to the Seed
     */
    FoodType getFoodType();

    /**
     * @return The SeedType of the current Seed
     */
    SeedType getSeedType();
}
