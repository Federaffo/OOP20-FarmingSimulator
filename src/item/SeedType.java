package item;

public enum SeedType implements Texturable {

    /**
     * This is Wheat.
     */
    WHEAT_SEED(FoodType.WHEAT, ItemConstants.SHORT_GROW_TIME, 10, "Wheat"),
    /**
     * This is CARROT.
     */
    CARROT_SEED(FoodType.CARROT, ItemConstants.MEDIUM_GROW_TIME, 30, "Carrot"),
    /**
     * This is POTATO.
     */
    POTATO_SEED(FoodType.POTATO, ItemConstants.MEDIUM_GROW_TIME, 50, "Potato"),
    /**
     * This is TOMATO.
     */
    TOMATO_SEED(FoodType.TOMATO, ItemConstants.LONG_GROW_TIME, 100, "Tomato"),
    /**
     * This is APPLE.
     */
    APPLE_SEED(FoodType.APPLE, ItemConstants.MEDIUM_GROW_TIME, 300, "Apple"),
    /**
     * This is ORANGE.
     */
    ORANGE_SEED(FoodType.ORANGE, ItemConstants.MEDIUM_GROW_TIME, 500, "Orange"),

    /**
     * This is CHERRY.
     */
    CHERRY_SEED(FoodType.CHERRY, ItemConstants.LONG_GROW_TIME, 1000, "Cherry");

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

    /**
     * @return the price of the current seed
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return the type (FoodType) of the current seed
     */
    public FoodType getFoodType() {
        return this.ofWhichFood;
    }

    /**
     * @return the time the seed takes to grow
     */
    public long getGrowTime() {
        return this.growTime;
    }

    /**
     * @return the name of the selected seed (String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     * @return the type (SeedType) of the seed which name is {name}
     */
    public static SeedType getSeedType(final String name) {
        for (SeedType st : SeedType.values()) {
            if (st.getName() == name) {
                return st;
            }
        }
        return null;
    }

}
