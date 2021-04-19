package block;

public interface UnlockableBlock extends FieldBlock{
	public boolean isLocked();
	public void unlockBlock();
	public void lockBlock();
}
