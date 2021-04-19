package item;

public interface Seed {
	public void grow();
	public FoodType harvest();
	public SeedState getSeedState();
	public double getGrowTime();
	public FoodType getFoodType();
	public SeedType getSeedType();
}
