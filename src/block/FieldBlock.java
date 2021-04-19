package block;

import entity.Pair;
import item.FoodType;
import item.Seed;
import item.SeedType;

public interface FieldBlock extends Block{
	public void plant(SeedType st);
	public Pair<FoodType, Integer> harvest();
	public boolean isEmpty();
	public Seed getSeed();
}
