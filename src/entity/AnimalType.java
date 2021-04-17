package entity;

import item.Food;
import item.ItemConstants;
import item.Texturable;

public enum AnimalType implements Texturable {
	
	COW(Food.MILK, ItemConstants.LONG_GROW_TIME, 2, "Cow"),
	CHICKEN(Food.EGG, ItemConstants.MEDIUM_GROW_TIME, 4, "Chicken"),
	PIG(Food.PORK_MEET, ItemConstants.LONG_GROW_TIME, 2, "Pig");
	
	private final long readyTime; 
	private final Food returnFood; 
	private final String name;
	private final int speed;
	
	AnimalType(final Food fd, final long growTime, final int speed, final String name) {
		this.readyTime = growTime;
		this.returnFood = fd;
		this.name = name;
		this.speed = speed;
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
	
	public int getSpeed() {
		return speed;
	}
}
