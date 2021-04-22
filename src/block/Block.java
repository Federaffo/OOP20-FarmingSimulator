package block;

public interface Block {
	
    /**
     * @return true if the block is walkable, false otherwise
     */
    boolean isWalkable();

    /**
     * @return true if I can interact with the block, false otherwise
     */
    boolean isInteractable();

    /**
     * @return the type(BlockType) of the Block
     */
    BlockType getType();

    /**
     * @return true if the block is BlockType.STALL, false otherwise
     */
    boolean isStall();
}
