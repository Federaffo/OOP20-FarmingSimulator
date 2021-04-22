package engine;

import entity.Player;
import block.Block;
import block.FieldBlock;
import entity.Animal;
import gameShop.Shop;
import item.SeedType;
import map.Map;

public interface Interaction {

    /**
     * @param pg
     * @param block
     */
    void playerPlant(Player pg, FieldBlock block);

    /**
     * @param pg
     * @param block
     */
    void playerHarvest(Player pg, FieldBlock block);

    /**
     * @param pg
     * @param map
     * @param block
     */
    void unlockBlock(Player pg, Map map, Block block);

    /**
     * @param pg
     * @param st
     * @param quantity
     * @return true if player can buy [quantity] of [st]
     */
    boolean playerBuy(Player pg, SeedType st, int quantity);

    /**
     * @param shop
     * @param pg
     * @return how much money that player have done
     */
    double playerSell(Shop shop, Player pg);

    /**
     * @param pg
     * @param block
     */
    void fieldInteraction(Player pg, FieldBlock block);

    /**
     * @param pg
     * @param animal
     */
    void playerAnimal(Player pg, Animal animal);

}
