package item;

public enum SeedType implements Texturable {

	WHEAT_SEED(Food.WHEAT, ItemConstants.SHORT_GROW_TIME, 10, "Wheat"),
	CARROT_SEED(Food.CARROT, ItemConstants.MEDIUM_GROW_TIME, 20, "Carrot"),
	POTATO_SEED(Food.POTATO, ItemConstants.MEDIUM_GROW_TIME, 20, "Potato"),
	TOMATO_SEED(Food.TOMATO, ItemConstants.LONG_GROW_TIME, 50, "Tomato"),

	APPLE_SEED(Food.APPLE, ItemConstants.MEDIUM_GROW_TIME, 30, "Apple"),
	ORANGE_SEED(Food.ORANGE, ItemConstants.MEDIUM_GROW_TIME, 70, "Orange"),
	CHERRY_SEED(Food.CHERRY, ItemConstants.LONG_GROW_TIME, 100, "Cherry");

	private final long growTime; /* true= posso raccogliere, false = non raccoglibile */
	private final Food ofWhichFood; /* mi segno di quale cibo � il mio seme */
	private final double price;
	private final String name;

	SeedType(final Food fd, final long growTime, final double pr, final String name) {
		this.ofWhichFood = fd;
		this.growTime = growTime;
		this.price = pr;
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public Food getFoodType() {
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
