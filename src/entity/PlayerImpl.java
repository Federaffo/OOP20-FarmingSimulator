package entity;

import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import gameMap.Block;

public class PlayerImpl extends EntityImpl implements Player {

	private double money;
	private Inventory bag;

	{
		SPEED = 5;
	}

	// create a new player in the indicated position
	public PlayerImpl(Pair<Integer, Integer> position) {
		super(position);

		bag = new InventoryImpl();
		money = 100;
	}

	public void incrementMoney(double moneyToAdd) {
		money += moneyToAdd;
	}

	public void decreaseMoney(double moneyToRemove) {
		money -= moneyToRemove;
	}

	public double getMoney() {
		return money;
	}

	public Inventory getInventory() {
		return this.bag;
	}

	// animal to interact with (nearest)
	public Optional<Animal> nearestAnimal(List<Animal> animals) {
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
