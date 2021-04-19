package gameMap;

import entity.Pair;
import item.FoodType;
import item.SeedImpl;
import item.SeedType;

public interface FieldBlock extends Block{
	public void plant(SeedType st);
	public Pair<FoodType, Integer> harvest();
	public boolean isEmpty();
	public SeedImpl getSeed();
}
