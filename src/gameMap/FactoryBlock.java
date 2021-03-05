package gameMap;

import java.awt.Rectangle;
import java.util.*;

import entity.Pair;
import item.Food;
import item.Seed;
import item.SeedType;

public class FactoryBlock {

	public BlockImpl getTerrainBlock(int posx, int posy) {
		return new BlockImpl(BlockType.TERRAIN, true, false, posx, posy);
	}

	public BlockImpl getObstacleBlock(int posx, int posy) {
		return new BlockImpl(BlockType.WALL, false, false, posx, posy);
	}

	public BlockImpl getFieldBlock(int posx, int posy) {
		return new BlockFieldImpl(BlockType.FIELD, true, true, posx, posy);
	
	}
	
	private class BlockFieldImpl extends BlockImpl implements FieldBlock{

		public BlockFieldImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super(bt, isWalkable, isInteractable, posx, posy);
		}
		private Optional<Seed> seed = Optional.empty();

		public void plant(SeedType st) {
			seed = Optional.of(new Seed(st));
		}

		public Pair<Food, Integer> harvest() {
			Food food = seed.get().Harvest();
			seed = Optional.empty();
			return new Pair<>(food, 3);
		}

		public boolean isEmpty() {
			return seed.isEmpty();
		}
		
		public Seed getSeed() {
			return seed.get();
		}
		
	}
	
	private class BlockImpl extends Rectangle implements Block {

		public final static int SIZE = 50;
		private final BlockType blockType;
		private final boolean isWalkable;
		private final boolean isInteractable;
		private final int posx;
		private final int posy;

		public BlockImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super(posx * SIZE, posy * SIZE, SIZE, SIZE);
			this.blockType = bt;
			this.isWalkable = isWalkable;
			this.isInteractable = isInteractable;
			this.posx = posx;
			this.posy = posy;
		}

		public boolean isWalkable() {
			return this.isWalkable;
		}

		public boolean isInteractable() {
			return this.isInteractable;
		}

		public BlockType getType() {
			return this.blockType;
		}

	}

}