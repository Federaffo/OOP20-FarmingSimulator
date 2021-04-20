package block;

import java.awt.Rectangle;
import java.util.*;


import entity.Pair;
import item.FoodType;
import item.Seed;
import item.SeedImpl;
import item.SeedType;

public class FactoryBlock {

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

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
	
	public BlockImpl getStallBlock(int posx, int posy) {
		return new BlockImpl(BlockType.STALL, true, false, posx, posy);
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

		private FactoryBlock getEnclosingInstance() {
			return FactoryBlock.this;
		}
		
	}
	
	private class BlockFieldImpl extends BlockImpl implements FieldBlock {

		private static final long serialVersionUID = 1L;
		private Optional<Seed> myseed = Optional.empty();

		public BlockFieldImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super(bt, isWalkable, isInteractable, posx, posy);
		}

		public void plant(SeedType st) {
			myseed = Optional.of(new SeedImpl(st));
		}


		public Pair<FoodType, Integer> harvest() {
			FoodType food = myseed.get().harvest();
			myseed = Optional.empty();
			return new Pair<>(food, food.getQuantity());
		}

		public boolean isEmpty() {
			return myseed.isEmpty();
		}

		public Seed getSeed() {
			return myseed.get();
		}

		private FactoryBlock getEnclosingInstance() {
			return FactoryBlock.this;
		}

	}

	private class BlockImpl extends Rectangle implements Block {

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((blockType == null) ? 0 : blockType.hashCode());
			result = prime * result + (isInteractable ? 1231 : 1237);
			result = prime * result + (isWalkable ? 1231 : 1237);
			result = prime * result + posx;
			result = prime * result + posy;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			BlockImpl other = (BlockImpl) obj;
			if (blockType != other.blockType)
				return false;
			if (isInteractable != other.isInteractable)
				return false;
			if (isWalkable != other.isWalkable)
				return false;
			if (posx != other.posx)
				return false;
			if (posy != other.posy)
				return false;
			return true;
		}

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
		
		public boolean isStall() {
			if(this.blockType==BlockType.STALL) {
				return true;
			}
			return false;
		}

		private FactoryBlock getEnclosingInstance() {
			return FactoryBlock.this;
		}

	}

}