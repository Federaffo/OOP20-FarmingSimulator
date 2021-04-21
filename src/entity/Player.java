package entity;

import java.util.List;
import java.util.Optional;

public interface Player extends Entity {

    // manage money
    void incrementMoney(double moneyToAdd);

    void decreaseMoney(double moneyToRemove);

    double getMoney();

    // get inventory
    Inventory getInventory();

    // get animal
    Optional<Animal> nearestAnimal(List<Animal> animals);
}
