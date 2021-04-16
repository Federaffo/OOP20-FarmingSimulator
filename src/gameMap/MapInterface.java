package gameMap;

import java.util.Set;

import entity.Pair;

public interface MapInterface {

	//return set of the map
	public Set<Block> getMapSet();
	//return the block in the given position
	public Block getBlock(Pair<Integer, Integer> pos);
	//set the BlockType in the given position
	public void setBlock(Pair<Integer, Integer> pos, BlockType bt);
	//get coordinates of the given block
	public Pair<Integer, Integer> getBlockCoordinates(Block b);
}
