package engine;

import block.Block;
import block.BlockType;
import block.FieldBlock;
import block.UnlockableBlock;
import entity.Animal;
import entity.Pair;
import entity.player.Player;
import gameMap.Map;
import gameShop.Shop;
import item.FoodType;
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
		Pair<FoodType, Integer> food = block.harvest();
		pg.getInventory().addFoods(food.getX(), food.getY());
	}

	@Override
	public void unlockBlock(Player pg, Map map, Block block) {
		// TODO Auto-generated method stub
		((UnlockableBlock) block).unlockBlock();
		Pair<Integer, Integer> blockPos = map.getBlockCoordinates(block);
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

	public void fieldInteraction(Player pg, FieldBlock block) {
		// TODO Auto-generated method stub

		if (block.isEmpty()) {
			if (pg.getInventory().getCurrentSeed().isPresent()) {
				this.playerPlant(pg, block);
			}
		} else {
			if (block.getSeed().getSeedState() == SeedState.GROWN) {
				this.playerHarvest(pg, block);
			}

		}

	}

	@Override
	public void playerAnimal(Player pg, Animal animal) {
		// TODO Auto-generated method stub
		if(animal.isReady()) {
			Pair<FoodType, Integer> temp = animal.collect();
			pg.getInventory().addFoods(temp.getX(), temp.getY());
		}
		
	}
}
