package block;

public interface Block {
	public boolean isWalkable();
	public boolean isInteractable();
	public BlockType getType();
	public boolean isStall();

}
