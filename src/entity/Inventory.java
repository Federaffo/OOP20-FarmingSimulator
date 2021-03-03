package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;

import item.FoodType;
import item.SeedType;

public class Inventory {
	
	private Map<SeedType, Integer> seeds;
	private Map<FoodType, Integer> foods;
	private Queue<SeedType> activeSeed;
	
	
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
		
		activeSeed = new LinkedBlockingDeque<SeedType>();
		Stream.of(SeedType.values()).forEach(x->activeSeed.add(x));
	}
	
	//return a Pair of <SeedType, number of seeds> of the next seed
	public Optional<Pair<SeedType, Integer>> nextSeed(){
		boolean isThereASeed = false;
		moveBottom(activeSeed.element());
		
		for (SeedType seedType : activeSeed) {
			if(seeds.get(seedType) > 0) {
				moveTop(seedType);
				isThereASeed = true;
				break;
			}
		}
		
		if(isThereASeed == false) {
			return Optional.empty();
		}else {
			return Optional.of(new Pair<SeedType, Integer>(activeSeed.element(), seeds.get(activeSeed.element())));
		}
	}
	
	//return the current seed selected
	public Optional<Pair<SeedType, Integer>> getCurrentSeed(){
		if(seeds.get(activeSeed.element()) > 0) {
			return Optional.of(new Pair<SeedType, Integer>(activeSeed.element(), seeds.get(activeSeed.element())));
		}else {
			return Optional.empty();
		}
	}
	
	//move the object to the bottom
	private void moveBottom(SeedType type) {
		activeSeed.remove(type);
		activeSeed.add(type);
	}
	
	//move the object to the top
	private void moveTop(SeedType type) {
		for (SeedType seedType : List.copyOf(activeSeed)) {
			if(!seedType.equals(type)) {
				moveBottom(seedType);
			}
		}
	}
	
	//add {number} seeds of {type} type to inventory
	public void addSeeds(SeedType type, Integer number) {
		seeds.put(type, seeds.get(type) + number);
		moveTop(type); //set the activeSeed to the seed added to inventory	
	}
	
	
	//remove 1 seed of {type} type
	public void removeSeed(SeedType type) {
		seeds.put(type, seeds.get(type) - 1);
		if(seeds.get(type) == 0) {
			
		}
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
