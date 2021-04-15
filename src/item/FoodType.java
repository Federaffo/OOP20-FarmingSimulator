package item;

public enum FoodType implements Texturable{
	/* cibi animali*/
	PORK_MEET(20,"Pork Meet"),
	COW_MEET(25,"Cow Meet"),
	EGG(5,"Egg"),
	MILK(8,"Milk"),
	/* ortaggi e verdura */
	WHEAT(5,"Wheat"),
	CARROT(10,"Carrot"),
	POTATO(22,"Potato"),
	TOMATO(40,"Tomato"),
	/* frutta */
	APPLE(10,"Apple"),
	ORANGE(50,"Orange"),
	CHERRY(100,"Cherry");

	private final double price;
	private final String name;

	FoodType( final double price, final String name) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}

}
