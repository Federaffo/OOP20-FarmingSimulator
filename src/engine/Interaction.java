package engine;



import entity.AnimalImpl;
import entity.player.Player;
import block.Block;
import block.FieldBlock;
import entity.Animal;
import gameMap.Map;
import gameShop.Shop;
import item.SeedType;

public interface Interaction {
	public void playerPlant(Player pg, FieldBlock block);
	public void playerHarvest(Player pg, FieldBlock block);
	public void unlockBlock(Player pg, Map map, Block block);
	public boolean playerBuy(Player pg, SeedType st, int quantity);
	public double playerSell(Shop shop, Player pg);
	void fieldInteraction(Player pg, FieldBlock block);
	public void playerAnimal(Player pg, Animal animal);
	
}
