package entity;

import java.util.HashMap;
import java.util.Map;
import item.FoodType;
import item.SeedType;

public class Inventory {
	
	private Map<SeedType, Integer> seeds;
	private Map<FoodType, Integer> foods;
	
	//build a void inventory
	public Inventory() {
		seeds = new HashMap<>();
		foods = new HashMap<>();
		
		for(SeedType s : SeedType.values()) {
			seeds.put(s, 0);
		}
		for(FoodType s : FoodType.values()) {
			foods.put(s, 0);
		}
	}
	
	//add {number} seeds of {type} type to inventory
	public void addSeeds(SeedType type, Integer number) {
		seeds.put(type, seeds.get(type) + number);
	}
	
	//remove 1 seed of {type} type
	public void removeSeed(SeedType type) {
		seeds.put(type, seeds.get(type) - 1);
	}
	
	//check if there are {number} seeds of {type} type inside inventory
	public boolean gotSeeds(SeedType type, Integer number) {
		if(seeds.get(type) >= number)
			return true;
		else
			return false;
	}

	
	//add {number} foods of {type} type to inventory
	public void addFoods(FoodType type, Integer number) {
		foods.put(type, foods.get(type) + number);
	}
	
	//remove 1 food of {type} type
	public void removeFood(FoodType type) {
		foods.put(type, foods.get(type) - 1);
	}
	
	//check if there are {number} foods of {type} type inside inventory
	public boolean gotFoods(FoodType type, Integer number) {
		if(foods.get(type) >= number)
			return true;
		else
			return false;
	}
}
