package gameShop;

import java.util.*;

import item.FoodType;
import item.Items;
import item.Seed;
import item.SeedType;

public class Shop {

	final private Set<Items> itemsList = new HashSet<>();
	//final private Set<SeedType> seedItemList = new HashSet<>();
	//final private Set<FoodType> foodItemList = new HashSet<>();

	public Shop() {
		/* carico la set di Food(enum) */
		for (FoodType f : FoodType.values()) {
			//foodItemList.add(f);
			itemsList.add(f);
		}
		/*carico la set di SeedType(enum) */
		for (SeedType st : SeedType.values()) {
			//seedItemList.add(st);
			itemsList.add(st);
		}
	}
	
	public Set<SeedType> getSeedItemList(){
		final Set<SeedType> seedItemList = new HashSet<>();
		for(Items i : itemsList) {
			if(i instanceof SeedType) {
				seedItemList.add((SeedType) i);
			}
		}
		return seedItemList;
	}
	
	public Set<FoodType> getFoodItemList(){
		final Set<FoodType> foodItemList = new HashSet<>();
		for(Items i : itemsList) {
			if(i instanceof FoodType) {
				foodItemList.add((FoodType) i);
			}
		}
		return foodItemList;
	}

	public double sell(FoodType fd) {
		return fd.getPrice();
	}

	//da cambiare quando verr� implementato l'inventario	
	public double sellAll(Map<FoodType, Integer> foodToSell) { 
		double somma = 0;
		for (FoodType f : foodToSell.keySet()) {
			for(int i=0; i<foodToSell.get(f) ; i++) {
				somma += f.getPrice();
			}
		}
		return somma;
	}

	public Seed buy(SeedType st) {
		return new Seed(st);
	}
}
