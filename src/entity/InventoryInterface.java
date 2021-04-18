package entity;

import java.util.Map;
import java.util.Optional;

import item.FoodType;
import item.SeedType;

public interface InventoryInterface {
	
	//MANAGE SEEDS
	
	//set the next seedType as the activeSeed
	public Optional<Pair<SeedType, Integer>> nextSeed();
	//add seeds to inventory
	public void addSeeds(SeedType type, Integer number);
	//remove 1 seed from inventory
	public void removeSeed(SeedType type);
	//check if there's any seed of the given type and number in the inventory
	public boolean gotSeeds(SeedType type, Integer number);
	//return map of seeds
	public Map<SeedType, Integer> getSeeds();
	
	//MANAGE FOODS
	
	//add foods to inventory
	public void addFoods(FoodType type, Integer number);
	//remove 1 food from inventory
	public void removeFood(FoodType type);
	//check if there's any food of the given type and number in the inventory
	public boolean gotFoods(FoodType type, Integer number);
	//return map of foods
	public Map<FoodType, Integer> getFoods();
	//remove all the food from inventory
	public void removeAllFood();
}
