package gameShop;

import java.util.*;

import item.Food;
import item.Seed;
import item.SeedType;

public class Shop {

	final private Set<SeedType> seedItemList = new HashSet<>();
	final private Set<Food> foodItemList = new HashSet<>();

	public Shop() {
		/* carico la set di Food(enum) */
		for (Food f : Food.values()) {
			foodItemList.add(f);
		}
		/*carico la set di SeedType(enum) */
		for (SeedType st : SeedType.values()) {
			seedItemList.add(st);
		}
	}

	public double sell(Food fd) {
		return fd.getPrice();
	}

	//da cambiare quando verrà implementato l'inventario	
	public double sellAll(Set<Food> foodToSell) { 
		double somma = 0;
		for (Food f : foodToSell) {
			somma += f.getPrice();
		}
		return somma;
	}

	public Seed buy(SeedType st) {
		return new Seed(st);
	}
}
