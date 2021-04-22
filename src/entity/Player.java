package entity;

import java.util.List;
import java.util.Optional;

public interface Player extends Entity {

    // manage money
    /**
     * @param moneyToAdd
     * This method add {moneytoAdd} money.
     */
    void incrementMoney(double moneyToAdd);

    /**
     * @param moneyToRemove
     * This method remove {moneyToRemove} money.
     */
    void decreaseMoney(double moneyToRemove);

    /**
     * @return the money that player has got
     */
    double getMoney();

    // get inventory
    /**
     * @return the inventory
     */
    Inventory getInventory();

    // get animal
    /**
     * @param animals
     * @return the animal nearest the player
     */
    Optional<Animal> nearestAnimal(List<Animal> animals);
}
