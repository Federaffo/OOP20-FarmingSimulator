package item;

public enum FoodType implements Texturable {
    /* cibi animali */

    /** 
     * Meat
     */
    PORK_MEAT(20, "Pork", 1), EGG(5, "Egg", 1), MILK(8, "Milk", 1),

    /**
     *  vegetables
     */
    WHEAT(4, "Wheat", 3), CARROT(13, "Carrot", 3), POTATO(22, "Potato", 3), TOMATO(40, "Tomato", 3),

    /**
     *  Fruits
     */
    APPLE(100, "Apple", 4), ORANGE(200, "Orange", 4), CHERRY(500, "Cherry", 4);

    private final double price;
    private final String name;
    private final int quantity;

    FoodType(final double price, final String name, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

}
