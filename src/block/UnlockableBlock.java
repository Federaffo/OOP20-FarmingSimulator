package block;

public interface UnlockableBlock extends FieldBlock {
    boolean isLocked();
    void unlockBlock();
    void lockBlock();
}
