package engine;

import entity.Pair;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gameMap.Map;
import gameMap.UnlockableBlock;
import gameShop.Shop;
import item.Food;
import item.SeedState;
import item.SeedType;

public class InteractionImpl implements Interaction {

	@Override
	public void playerPlant(Player pg, FieldBlock block) {
		// TODO Auto-generated method stub
		SeedType st = pg.getInventory().getCurrentSeed().get().getX();
		block.plant(st);
		pg.getInventory().removeSeed(st);
	}

	@Override
	public void playerHarvest(Player pg, FieldBlock block) {
		// TODO Auto-generated method stub
		Pair<Food, Integer> food = block.harvest();
		pg.getInventory().addFoods(food.getX(), food.getY());
	}

	@Override
	public void unlockBlock(Player pg, Map map, Block block) {
		// TODO Auto-generated method stub
		((UnlockableBlock) block).unlockBlock();
		Pair<Integer, Integer> blockPos = new Pair<>((int)pg.getX(),(int) pg.getY());
		map.setBlock(blockPos, BlockType.FIELD);
	}

	@Override
	public boolean playerBuy(Player pg, SeedType st, int quantity) {
		// TODO Auto-generated method stub
		if (pg.getMoney() >= st.getPrice() * quantity) {
			pg.getInventory().addSeeds(st, quantity);
			pg.decreaseMoney(st.getPrice() * quantity);
			return true;
		}
		return false;
	}

	public double playerSell(Shop shop, Player pg) {
		double money = shop.sellAll(pg.getInventory().getFoods());
		pg.incrementMoney(money);
		pg.getInventory().removeAllFood();
		return money;
	}

	@Override
	public double checkInteraction(Player pg, Block block, double unlockPrice, Map map) {
		// TODO Auto-generated method stub
		if (!(block instanceof UnlockableBlock)) {

			if (block.getType() == BlockType.FIELD) {
				FieldBlock myBlock = (FieldBlock) block;
				if (myBlock.isEmpty()) {
					if (pg.getInventory().getCurrentSeed().isPresent()) {
						this.playerPlant(pg, myBlock);
					}
				} else {
					if (myBlock.getSeed().getSeedState() == SeedState.GROWN) {
						this.playerHarvest(pg, myBlock);
					}
				}
			}
		} else if (pg.getMoney() >= unlockPrice) {
			unlockBlock(pg, map, block);
			pg.decreaseMoney(unlockPrice); // decremento i soldi del Player
			unlockPrice += 25; // aumento il prezzo del prossimo blocco
		}
		return unlockPrice;
	}

}
