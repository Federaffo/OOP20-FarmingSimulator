package gameMap;

import java.util.*;

import item.Seed;





public class FactoryBlock {
	
	public Block getTerrainBlock(){
		return new Block(BlockType.TERRAIN, true, false);
	}
	
	public Block getObstacleBlock(){
		return new Block(BlockType.OBSTACLE, false, false);
	}
	
	public Block getFieldBlock(){
		return new Block(BlockType.FIELD, true, true){
			private Optional<Seed> seed = Optional.empty();
			public void interact() {
				
			}
		};
	}
	

	private class Block {
		
		public final static int SIZE = 50;
		private final BlockType blockType;
		private final boolean isWalkable;
		private final boolean isInteractable;

		public Block(BlockType bt, boolean isWalkable, boolean isInteractable) {
			this.blockType = bt;
			this.isWalkable = isWalkable;
			this.isInteractable = isInteractable;
		}
		

		public boolean isWalkable() {
			return this.isWalkable();
		}
		
		public boolean isInteractable() {
			return this.isInteractable();
		}
		
		public BlockType getType() {
			return this.blockType;
		}

	}

}