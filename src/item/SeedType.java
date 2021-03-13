package item;

public enum SeedType implements Texturable {
	 
	WHEAT_SEED(Food.WHEAT,ItemConstants.SHORT_GROW_TIME,10),
	CARROT_SEED(Food.CARROT,ItemConstants.MEDIUM_GROW_TIME,20),
	POTATO_SEED(Food.POTATO,ItemConstants.MEDIUM_GROW_TIME,20),
	TOMATO_SEED(Food.TOMATO,ItemConstants.LONG_GROW_TIME,50),
	
	APPLE_SEED(Food.APPLE,ItemConstants.MEDIUM_GROW_TIME,30),
	ORANGE_SEED(Food.ORANGE,ItemConstants.MEDIUM_GROW_TIME,70),
	CHERRY_SEED(Food.CHERRY,ItemConstants.LONG_GROW_TIME,100);

	private final long growTime; /* true= posso raccogliere, false = non raccoglibile */
	private final Food ofWhichFood; /* mi segno di quale cibo � il mio seme */
	private final double price;
	
	SeedType (final Food fd, final long growTime, final double pr){
		this.ofWhichFood = fd;
		this.growTime = growTime;
		this.price=pr;
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
	
}

