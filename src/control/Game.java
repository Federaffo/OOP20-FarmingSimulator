package control;


import java.util.List;

import engine.GameState;
import entity.AnimalImpl;
import entity.Animal;
import entity.AnimalType;
import entity.Pair;
import entity.Player;
import entity.PlayerImpl;
import gameMap.MapImpl;
import gameMap.Map;
import gameShop.Shop;
import item.SeedType;


public interface Game {
	public void loadGame(MapImpl map, PlayerImpl player);
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
	public List<Animal> getAllAnimals();
	public void resetAnimals();
}
	
