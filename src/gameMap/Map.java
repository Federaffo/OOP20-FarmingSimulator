package gameMap;

import java.util.Set;
import java.util.function.Predicate;

import block.Block;
import block.BlockType;
import entity.Pair;

public interface Map {

	//return set of the map
	public Set<Block> getMapSet();
	//return the block in the given position
	public Block getBlock(Pair<Integer, Integer> pos);
	//set the BlockType in the given position
	public void setBlock(Pair<Integer, Integer> pos, BlockType bt);
	//get coordinates of the given block
	public Pair<Integer, Integer> getBlockCoordinates(Block b);
	//get a random block of the given type
	public Block getRandomFilterBlock(Predicate<Block> blockFilter);
}
