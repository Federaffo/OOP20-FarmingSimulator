package entity;

import item.FoodType;
import item.ItemConstants;
import item.Texturable;

public enum AnimalType implements Texturable {

    /**
     * this is a cow, the food that cow made is milk.
     */
    COW(FoodType.MILK, ItemConstants.HYPER_LONG_GROW_TIME, ItemConstants.SLOW_SPEED, "Cow"),

    /**
     * this is a chicken, the food that chicken made is egg.
     */
    CHICKEN(FoodType.EGG, ItemConstants.MEDIUM_GROW_TIME, ItemConstants.FAST_SPEED, "Chicken"),

    /**
     * this is a pig, the food that pig made is pork meat.
     */
    PIG(FoodType.PORK_MEAT, ItemConstants.VERY_LONG_GROW_TIME, ItemConstants.MEDIUM_SPEED, "Pig");

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
