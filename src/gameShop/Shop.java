package gameShop;

import java.util.Map;
import java.util.Set;

import item.FoodType;
import item.Seed;
import item.SeedType;

public interface Shop {
	public Set<SeedType> getSeedItemList();
	public Set<FoodType> getFoodItemList();
	public double sell(FoodType fd);
	public double sellAll(Map<FoodType, Integer> foodToSell);
	public Seed buy(SeedType st);
}
