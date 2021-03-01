package item;


public enum SeedType {
	
	WHEAT_SEED(Food.WHEAT,ItemConstants.SHORT_GROW_TIME),
	CARROT_SEED(Food.CARROT,ItemConstants.MEDIUM_GROW_TIME),
	POTATO_SEED(Food.POTATO,ItemConstants.MEDIUM_GROW_TIME),
	TOMATO_SEED(Food.TOMATO,ItemConstants.LONG_GROW_TIME),
	
	APPLE_SEED(Food.APPLE,ItemConstants.MEDIUM_GROW_TIME),
	ORANGE_SEED(Food.ORANGE,ItemConstants.MEDIUM_GROW_TIME),
	CHERRY_SEED(Food.CHERRY,ItemConstants.LONG_GROW_TIME);

	private final long growTime; /* true= posso raccogliere, false = non raccoglibile */
	private final Food ofWhichFood; /* mi segno di quale cibo è il mio seme */
	
	SeedType (final Food fd, final long growTime){
		this.ofWhichFood = fd;
		this.growTime = growTime;
	}
	
	public Food getFoodType() {
		return this.ofWhichFood;
	}
	
	public long getGrowTime() {
		return this.growTime;
	}
	
}

