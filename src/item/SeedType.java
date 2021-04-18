package item;

public enum SeedType implements Texturable {

	WHEAT_SEED(FoodType.WHEAT, ItemConstants.SHORT_GROW_TIME, 10, "Wheat"),
	CARROT_SEED(FoodType.CARROT, ItemConstants.MEDIUM_GROW_TIME, 15, "Carrot"),
	POTATO_SEED(FoodType.POTATO, ItemConstants.MEDIUM_GROW_TIME, 25, "Potato"),
	TOMATO_SEED(FoodType.TOMATO, ItemConstants.LONG_GROW_TIME, 45, "Tomato"),

	APPLE_SEED(FoodType.APPLE, ItemConstants.MEDIUM_GROW_TIME, 30, "Apple"),
	ORANGE_SEED(FoodType.ORANGE, ItemConstants.MEDIUM_GROW_TIME, 70, "Orange"),
	CHERRY_SEED(FoodType.CHERRY, ItemConstants.LONG_GROW_TIME, 100, "Cherry");

	private final long growTime; /* true= posso raccogliere, false = non raccoglibile */
	private final FoodType ofWhichFood; /* mi segno di quale cibo � il mio seme */
	private final double price;
	private final String name;

	SeedType(final FoodType fd, final long growTime, final double pr, final String name) {
		this.ofWhichFood = fd;
		this.growTime = growTime;
		this.price = pr;
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public FoodType getFoodType() {
		return this.ofWhichFood;
	}

	public long getGrowTime() {
		return this.growTime;
	}

	public String getName() {
		return this.name;
	}
	
	//dalla stringa capisco che tipo è
	public static SeedType getSeedType(String name) {
		for (SeedType st : SeedType.values()) {
			if(st.getName() == name) {
				return st;
			}
		}
		return null;
	}

}
