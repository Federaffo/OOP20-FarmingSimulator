package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import item.Food;
import item.Seed;
import item.SeedType;

public class Inventory {
	
	private Map<SeedType, Integer> seeds;
	private List<Food> foods;
	
	//build a void inventory
	public Inventory() {
		seeds = new HashMap<>();
		foods = new ArrayList<>();
		
		for(SeedType s : SeedType.values()) {
			seeds.put(s, 0);
		}
	}
	
	//add {number} seeds of {type} type to inventory
	public void addSeeds(SeedType type, Integer number) {
		seeds.put(type, seeds.get(type) + number);
	}
	//remove and return 1 seed of {type} type
	public Seed removeSeed(SeedType type) {
		seeds.put(type, seeds.get(type) - 1);
		return new Seed(type);
	}
	//check if there are {number} seeds of {type} type inside inventory
	public boolean gotSeeds(SeedType type, Integer number) {
		if(seeds.get(type) >= number)
			return true;
		else
			return false;
	}
}
