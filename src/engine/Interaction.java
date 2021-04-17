package engine;



import entity.Player;
import gameMap.Block;
import gameMap.FieldBlock;
import gameMap.Map;
import gameShop.Shop;
import item.SeedType;

public interface Interaction {
	public double checkInteraction(Player pg, Block block, double unlockPrice, Map map);
	public void playerPlant(Player pg, FieldBlock block);
	public void playerHarvest(Player pg, FieldBlock block);
	public void unlockBlock(Player pg, Map map, Block block);
	public boolean playerBuy(Player pg, SeedType st, int quantity);
	public double playerSell(Shop shop, Player pg);
	
}
