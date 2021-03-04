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
		return new BlockImpl(BlockType.OBSTACLE, false, false, posx, posy);
	}

	public BlockImpl getFieldBlock(int posx, int posy) {
		return new BlockImpl(BlockType.FIELD, true, true, posx, posy) {
			private Optional<Seed> seed = Optional.empty();

			public void plant() {
				seed = Optional.of(new Seed(SeedType.POTATO_SEED));
			}

			public Pair<Food, Integer> harvest() {
				Food food = seed.get().Harvest();
				return new Pair<>(food, 3);
			}
		};
	}

	private class BlockImpl extends Rectangle implements Block {

		public final static int SIZE = 50;
		private final BlockType blockType;
		private final boolean isWalkable;
		private final boolean isInteractable;
		private final int posx;
		private final int posy;

		public BlockImpl(BlockType bt, boolean isWalkable, boolean isInteractable, int posx, int posy) {
			super();
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