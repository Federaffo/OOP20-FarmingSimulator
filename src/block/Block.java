package block;

public interface Block {
    boolean isWalkable();

    boolean isInteractable();

    BlockType getType();

    boolean isStall();

}
