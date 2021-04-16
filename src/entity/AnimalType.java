package entity;

import item.Food;
import item.ItemConstants;
import item.Texturable;

public enum AnimalType implements Texturable {
	
	COW(Food.MILK, ItemConstants.LONG_GROW_TIME, "Cow"),
	CHICKEN(Food.EGG, ItemConstants.MEDIUM_GROW_TIME, "Chicken"),
	PIG(Food.PORK_MEET, ItemConstants.LONG_GROW_TIME, "Pig");
	
	private final long readyTime; 
	private final Food returnFood; 
	private final String name;
	
	AnimalType(final Food fd, final long growTime, final String name) {
		this.readyTime = growTime;
		this.returnFood = fd;
		this.name = name;
	}
	
	public Food getReturnFood() {
		return this.returnFood;
	}
	
	public String getName() {
		return this.name;
	}
	
	public long readyTime() {
		return this.readyTime;
	}
}
