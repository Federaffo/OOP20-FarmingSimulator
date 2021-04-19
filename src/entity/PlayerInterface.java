package entity;

public interface PlayerInterface {

	//manage money
	public void incrementMoney(double moneyToAdd);
	public void decreaseMoney(double moneyToRemove);
	public double getMoney();
	
	//get inventory
	public InventoryInterface getInventory();
}
