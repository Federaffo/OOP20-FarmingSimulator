package block;

public interface UnlockableBlock extends FieldBlock {
    /**
     * @return true id the Block is locked, false otherwise
     */
    boolean isLocked();

    /**
     * unlock the block.
     */
    void unlockBlock();

    /**
     * lock the bloc.
     */
    void lockBlock();
}
