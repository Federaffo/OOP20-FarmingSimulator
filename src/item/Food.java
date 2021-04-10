package item;

public enum Food implements Texturable{
	/* cibi animali*/
	PORK_MEET(FoodType.MEET,20,"Pork Meet"),
	COW_MEET(FoodType.MEET,25,"Cow Meet"),
	EGG(FoodType.MEET,5,"Egg"),
	MILK(FoodType.MEET,8,"Milk"),
	/* ortaggi e verdura */
	WHEAT(FoodType.VEGETABLE,5,"Wheat"),
	CARROT(FoodType.VEGETABLE,10,"Carrot"),
	POTATO(FoodType.VEGETABLE,22,"Potato"),
	TOMATO(FoodType.VEGETABLE,40,"Tomato"),
	/* frutta */
	APPLE(FoodType.FRUIT,10,"Apple"),
	ORANGE(FoodType.FRUIT,12,"Orange"),
	CHERRY(FoodType.FRUIT,15,"Cherry");

	private final double price;
	private final FoodType type;
	private final String name;

	Food(final FoodType type, final double price, final String name) {
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public FoodType getFoodType() {
		return this.type;
	}
	
	public double getPrice() {
		return this.price;
	}

}
