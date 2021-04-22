package entity;

import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;

public class PlayerImpl extends EntityImpl implements Player {

    private double money;
    private Inventory bag;

    {
        SPEED = 5;
    }

    // create a new player in the indicated position
    public PlayerImpl(final Pair<Integer, Integer> position) {
        super(position);

        bag = new InventoryImpl();
    }

    /**
     *@param moneyToAdd
     */
    public void incrementMoney(final double moneyToAdd) {
        money += moneyToAdd;
    }

    /**
     *@param moneyToRemove
     */
    public void decreaseMoney(final double moneyToRemove) {
        money -= moneyToRemove;
    }

    /**
     *@return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     *@return the inventory
     */
    public Inventory getInventory() {
        return this.bag;
    }

    // animal to interact with (nearest)
    /**
     *@param animals
     *@return the nearest animal
     */
    public Optional<Animal> nearestAnimal(final List<Animal> animals) {
        float area = 0;
        Optional<Animal> animalChoosen = Optional.empty();

        for (Animal animal : animals) {
            Rectangle temp = this.intersection((Rectangle) animal);
            float tempArea = temp.width * temp.height;

            if (tempArea > area && temp.width > 0 && temp.height > 0) {
                animalChoosen = Optional.of(animal);
                area = tempArea;
            }
        }

        return animalChoosen;
    }
}
