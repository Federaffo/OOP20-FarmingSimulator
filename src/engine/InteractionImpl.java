package engine;

import block.Block;
import block.BlockType;
import block.FieldBlock;
import block.UnlockableBlock;
import entity.Animal;
import entity.Pair;
import entity.Player;
import gameShop.Shop;
import item.FoodType;
import item.SeedState;
import item.SeedType;
import map.Map;

public final class InteractionImpl implements Interaction {

    /**
     *{@inheritDoc}
     */
    @Override
    public void playerPlant(final Player pg, final FieldBlock block) {
        // TODO Auto-generated method stub
        SeedType st = pg.getInventory().getCurrentSeed().get().getX();
        block.plant(st);
        pg.getInventory().removeSeed(st);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void playerHarvest(final Player pg, final FieldBlock block) {
        // TODO Auto-generated method stub
        Pair<FoodType, Integer> food = block.harvest();
        pg.getInventory().addFoods(food.getX(), food.getY());
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void unlockBlock(final Player pg, final Map map, final Block block) {
        // TODO Auto-generated method stub
        ((UnlockableBlock) block).unlockBlock();
        Pair<Integer, Integer> blockPos = map.getBlockCoordinates(block);
        map.setBlock(blockPos, BlockType.FIELD);

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean playerBuy(final Player pg, final SeedType st, final int quantity) {
        // TODO Auto-generated method stub
        if (pg.getMoney() >= st.getPrice() * quantity) {
            pg.getInventory().addSeeds(st, quantity);
            pg.decreaseMoney(st.getPrice() * quantity);
            return true;
        }
        return false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public double playerSell(final Shop shop, final Player pg) {
        double money = shop.sellAll(pg.getInventory().getFoods());
        pg.incrementMoney(money);
        pg.getInventory().removeAllFood();
        return money;
    }


    /**
     *{@inheritDoc}
     */
    @Override
    public void fieldInteraction(final Player pg, final FieldBlock block) {
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

    /**
     *{@inheritDoc}
     */
    @Override
    public void playerAnimal(final Player pg, final Animal animal) {
        // TODO Auto-generated method stub
        if (animal.isReady()) {
            Pair<FoodType, Integer> temp = animal.collect();
            pg.getInventory().addFoods(temp.getX(), temp.getY());
        }

    }
}
