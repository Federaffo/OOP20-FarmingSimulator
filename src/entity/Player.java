package entity;

import java.util.Set;

import gameMap.Block;

public class Player extends Entity implements PlayerInterface {
	
	private double money;
	private InventoryInterface bag;
	
	{SPEED = 5;}
	//create a new player in the indicated position
	public Player(Pair<Integer, Integer> position) {
		super(position);

		bag = new Inventory();
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
	
	public InventoryInterface getInventory() {
		return this.bag;
	}
}
