package item;

public interface Seed {
    void grow();

    FoodType harvest();

    SeedState getSeedState();

    double getGrowTime();

    FoodType getFoodType();

    SeedType getSeedType();
}
