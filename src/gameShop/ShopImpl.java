package gameShop;

import java.util.*;

import item.FoodType;
import item.Seed;
import item.SeedImpl;
import item.SeedType;

public class ShopImpl implements Shop{

	//final private Set<Items> itemsList = new HashSet<>();
	final private Set<SeedType> seedItemList = new HashSet<>();
	final private Set<FoodType> foodItemList = new HashSet<>();

	public ShopImpl() {
		/* carico la set di Food(enum) */
		for (FoodType f : FoodType.values()) {
			foodItemList.add(f);
		}
		/*carico la set di SeedType(enum) */
		for (SeedType st : SeedType.values()) {
			seedItemList.add(st);
			
		}
	}
	
	public Set<SeedType> getSeedItemList(){	
		return seedItemList;
	}
	
	public Set<FoodType> getFoodItemList(){
		return foodItemList;
	}

	public double sell(FoodType fd) {
		return fd.getPrice();
	}

	//da cambiare quando verr� implementato l'inventario	
	public double sellAll(Map<FoodType, Integer> map) { 
		double somma = 0;
		for (FoodType f : map.keySet()) {
			for(int i=0; i<map.get(f) ; i++) {
				somma += f.getPrice();
			}
		}
		return somma;
	}

	public Seed buy(SeedType st) {
		return new SeedImpl((SeedType) st);
	}
}
