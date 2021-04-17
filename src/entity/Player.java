package entity;

import java.util.Set;

import gameMap.Block;

public class Player extends Entity {
	
	private double money;
	private Inventory bag;
	
	{SPEED = 5;}
	//create a new player in the indicated position
	public Player(Pair<Integer, Integer> position) {
		super(position);

		bag = new Inventory();
		money = 100;
	}
	
	//manage player money
	public void incrementMoney(double moneyToAdd) {
		money += moneyToAdd;
	}
	public void decreaseMoney(double moneyToRemove) {
		money -= moneyToRemove;
	}
	
	//getter money
	public double getMoney() {
		return money;
	}
	
	//getter inventory
	public Inventory getInventory() {
		return this.bag;
	}
}
