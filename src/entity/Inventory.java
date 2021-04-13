package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;

import com.google.gson.annotations.Expose;

import item.FoodType;
import item.Items;
import item.SeedType;
import jdk.jfr.Category;

public class Inventory {
	private Map<Items,Integer> itemsMap;
//	private Map<SeedType, Integer> seeds;
//	private Map<FoodType, Integer> foods;
	private Queue<Items> activeSeed;
	
	
	//build a void inventory
	public Inventory() {
		itemsMap = new HashMap<>();
//		seeds = new HashMap<>();
//		foods = new HashMap<>();
		
		for(SeedType s : SeedType.values()) {
			//seeds.put(s, 0);
			itemsMap.put(s, 0);
		}
		for(FoodType f : FoodType.values()) {
			//foods.put(f, 0);
			itemsMap.put(f, 0);
		}
		
		activeSeed = new LinkedBlockingDeque<Items>();
		Stream.of(SeedType.values()).forEach(x->activeSeed.add(x));
	}
	
	//return a Pair of <SeedType, number of seeds> of the next seed
	public Optional<Pair<SeedType, Integer>> nextSeed(){
		boolean isThereASeed = false;
		moveBottom((SeedType)activeSeed.element());
		
		for (Items seedType : activeSeed) {
			if(itemsMap.get(seedType) > 0) {
				moveTop((SeedType)seedType);
				isThereASeed = true;
				break;
			}
		}
		
		if(isThereASeed == false) {
			return Optional.empty();
		}else {
			return Optional.of(new Pair<SeedType, Integer>((SeedType)activeSeed.element(), itemsMap.get((SeedType)activeSeed.element())));
		}
	}
	
	//return the current seed selected
	public Optional<Pair<SeedType, Integer>> getCurrentSeed(){
		if(itemsMap.get(activeSeed.element()) > 0) {
			return Optional.of(new Pair<SeedType, Integer>((SeedType)activeSeed.element(), itemsMap.get((SeedType)activeSeed.element())));
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
		for (Items seedType : List.copyOf(activeSeed)) {
			if(!seedType.equals(type)) {
				moveBottom((SeedType)seedType);
			}
		}
	}
	
	//add {number} seeds of {type} type to inventory
	public void addSeeds(SeedType type, Integer number) {
		itemsMap.put(type, itemsMap.get(type) + number);
		moveTop(type); //set the activeSeed to the seed added to inventory	
	}
	
	
	//remove 1 seed of {type} type
	public void removeSeed(SeedType type) {
		itemsMap.put(type, itemsMap.get(type) - 1);
		if(itemsMap.get(type) == 0) {
			
		}
	}
	
	//check if there are {number} seeds of {type} type inside inventory
	public boolean gotSeeds(SeedType type, Integer number) {
		if(itemsMap.get(type) >= number)
			return true;
		else
			return false;
	}
	
	//add {number} foods of {type} type to inventory
	public void addFoods(FoodType type, Integer number) {
		itemsMap.put(type, itemsMap.get(type) + number);
	}
	
	//remove 1 food of {type} type
	public void removeFood(FoodType type) {
		itemsMap.put(type, itemsMap.get(type) - 1);
	}
	
	//check if there are {number} foods of {type} type inside inventory
	public boolean gotFoods(FoodType type, Integer number) {
		if(itemsMap.get(type) >= number)
			return true;
		else
			return false;
	}
	
	//restituisce tutto il cibo nell'inventario
	public Map<FoodType, Integer> getFood(){
		Map<FoodType, Integer> temp= new HashMap<>();
		for (Entry<Items,Integer> food : itemsMap.entrySet()) {
			if(food.getKey() instanceof FoodType) {
				temp.put((FoodType) food.getKey(), food.getValue());				
			}
		}
		return temp;
	}
	
	//restituisce tutti i semi nell'inventario
	public Map<SeedType, Integer> getSeeds(){
		Map<SeedType, Integer> temp= new HashMap<>();
		for (Entry<Items,Integer> seed : itemsMap.entrySet()) {
			if(seed.getKey() instanceof SeedType) {
				temp.put((SeedType) seed.getKey(), seed.getValue());				
			}
		}
		return temp;
	}
	
	
	//rimuove tutto il cibo nell'inventario
	public void  removeAllFood() {
		for (var food : itemsMap.keySet()) {
			if(food instanceof FoodType) {
				itemsMap.put(food, 0);				
			}
		}
	}
	private void updateMap() {

		
	}
	

}
