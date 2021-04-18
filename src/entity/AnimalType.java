package entity;


import item.FoodType;
import item.ItemConstants;
import item.Texturable;

public enum AnimalType implements Texturable {
	
	COW(FoodType.MILK, ItemConstants.LONG_GROW_TIME, 2, "Cow"),
	CHICKEN(FoodType.EGG, ItemConstants.MEDIUM_GROW_TIME, 4, "Chicken"),
	PIG(FoodType.PORK_MEAT, ItemConstants.LONG_GROW_TIME, 2, "Pig");
	
	private final long readyTime; 
	private final FoodType returnFood; 
	private final String name;
	private final int speed;
	
	AnimalType(final FoodType fd, final long growTime, final int speed, final String name) {
		this.readyTime = growTime;
		this.returnFood = fd;
		this.name = name;
		this.speed = speed;
	}
	
	public FoodType getReturnFood() {
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
