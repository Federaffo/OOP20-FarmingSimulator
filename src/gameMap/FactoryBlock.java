package gameMap;

import java.awt.Rectangle;
import java.util.*;

import com.google.gson.annotations.Expose;

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
	
	public BlockImpl getLockedBlock(int posx, int posy) {
		return new UnlockableBlockImpl(BlockType.LOCKED, true, true, posx, posy);
	}
	
	public BlockImpl getWaterBlock(int posx, int posy) {
		return new BlockImpl(BlockType.WATER, true, false, posx, posy);
	}
	
	private class UnlockableBlockImpl extends BlockFieldImpl implements UnlockableBlock{
		private boolean locked=true;

		public UnlockableBlockImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super(bt, isWalkable, isInteractable, posx, posy);
		}

		@Override
		public boolean isLocked() {
			return this.locked;
		}

		@Override
		public void unlockBlock() {
			this.locked=false;
		}

		@Override
		public void lockBlock() {
			this.locked=true;
		}
		
	}
	
	private class BlockFieldImpl extends BlockImpl implements FieldBlock {
		private static final long serialVersionUID = 1L;
		private Optional<Seed> myseed = Optional.empty();

		public BlockFieldImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super(bt, isWalkable, isInteractable, posx, posy);
		}

		public void plant(SeedType st) {
			myseed = Optional.of(new Seed(st));
		}

		public Pair<Food, Integer> harvest() {
			Food food = myseed.get().Harvest();
			myseed = Optional.empty();
			return new Pair<>(food, 3);
		}

		public boolean isEmpty() {
			return myseed.isEmpty();
		}

		public Seed getSeed() {
			return myseed.get();
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