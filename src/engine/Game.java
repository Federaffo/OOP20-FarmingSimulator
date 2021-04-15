package engine;

import entity.Player;
import gameMap.Map;
import gameShop.Shop;
import item.SeedType;

public interface Game {
	public void loadGame(Map map, Player player);
	public void loop();
	public Map getMap();
	public Player getPlayer();
	public Shop getShop();
	public boolean buy(SeedType st, int quantity);
	public double sellAll();
	public GameState getState();
	public void interact();
	public double getUnlockPrice();
	public void growAllSeed();
	public void play();
	public void shop();
	public void info();
}
	
