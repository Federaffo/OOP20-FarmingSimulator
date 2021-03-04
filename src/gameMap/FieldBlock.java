package gameMap;

import entity.Pair;
import item.Food;
import item.Seed;
import item.SeedType;

public interface FieldBlock{
	public void plant(SeedType st);
	public Pair<Food, Integer> harvest();
	public boolean isEmpty();
	public Seed getSeed();
}
