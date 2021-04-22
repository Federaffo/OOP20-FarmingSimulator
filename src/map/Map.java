package map;

import java.util.Set;
import java.util.function.Predicate;

import block.Block;
import block.BlockType;
import entity.Pair;

public interface Map {

    /**
     * @return set of the map
     */
    Set<Block> getMapSet();

    /**
     * @param pos
     * @return the block in the given position
     */
    Block getBlock(Pair<Integer, Integer> pos);

    /**
     * This method set the BlockType in the given position.
     * 
     * @param pos
     * @param bt
     */
    void setBlock(Pair<Integer, Integer> pos, BlockType bt);

    /**
     * @param b
     * @return the coordinates of the given block
     */
    Pair<Integer, Integer> getBlockCoordinates(Block b);

    /**
     * @param blockFilter
     * @return a random block of the given type
     */
    Block getRandomFilterBlock(Predicate<Block> blockFilter);

    /**
     * @return the number of rows
     */
    int getRows();

    /**
     * @return the number of columns
     */
    int getColumns();
}
