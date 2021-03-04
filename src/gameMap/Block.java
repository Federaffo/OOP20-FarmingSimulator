package gameMap;


public interface Block {
	
	public boolean isWalkable();
	public boolean isInteractable();
	public BlockType getType();

}
