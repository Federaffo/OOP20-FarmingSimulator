package entity;

import java.util.Set;

import gameMap.Block;
import item.FoodType;

public interface Animal extends Entity {
	
	//return the food of the animal
	public Pair<FoodType, Integer> collect();
	//move the animal in a random direction
	public void randomMove(Set<Block> map);
	
	//getter
	public AnimalType getType();
	public boolean isReady();
}
