package block;

import java.awt.Rectangle;
import java.util.*;

import entity.Pair;
import item.FoodType;
import item.Seed;
import item.SeedImpl;
import item.SeedType;

public final class FactoryBlock {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    /**
     * @param posx
     * @param posy
     * @return new Block of TERRAIN type
     */
    public BlockImpl getTerrainBlock(final int posx, final int posy) {
        return new BlockImpl(BlockType.TERRAIN, true, false, posx, posy);
    }

    /**
     * @param posx
     * @param posy
     * @return new Block of WALL type
     */
    public BlockImpl getObstacleBlock(final int posx, final int posy) {
        return new BlockImpl(BlockType.WALL, false, false, posx, posy);
    }

    /**
     * @param posx
     * @param posy
     * @return new BlockField
     */
    public BlockImpl getFieldBlock(final int posx, final int posy) {
        return new BlockFieldImpl(BlockType.FIELD, true, true, posx, posy);
    }

    /**
     * @param posx
     * @param posy
     * @return new Block of LOCKED type
     */
    public BlockImpl getLockedBlock(final int posx, final int posy) {
        return new UnlockableBlockImpl(BlockType.LOCKED, true, true, posx, posy);
    }

    /**
     * @param posx
     * @param posy
     * @return a new Block of WATER type
     */
    public BlockImpl getWaterBlock(final int posx, final int posy) {
        return new BlockImpl(BlockType.WATER, true, false, posx, posy);
    }

    /**
     * @param posx
     * @param posy
     * @return a new Block of STALL type
     */
    public BlockImpl getStallBlock(final int posx, final int posy) {
        return new BlockImpl(BlockType.STALL, true, false, posx, posy);
    }

    private class UnlockableBlockImpl extends BlockFieldImpl implements UnlockableBlock {

        private boolean locked = true;

        UnlockableBlockImpl(final BlockType bt, final boolean isWalkable, final boolean isInteractable, final int posx,
                final int posy) {
            super(bt, isWalkable, isInteractable, posx, posy);
        }

        @Override
        public boolean isLocked() {
            return this.locked;
        }

        @Override
        public void unlockBlock() {
            this.locked = false;
        }

        @Override
        public void lockBlock() {
            this.locked = true;
        }

        private FactoryBlock getEnclosingInstance() {
            return FactoryBlock.this;
        }

    }

    private class BlockFieldImpl extends BlockImpl implements FieldBlock {

        private static final long serialVersionUID = 1L;
        private Optional<Seed> myseed = Optional.empty();

        BlockFieldImpl(final BlockType bt, final boolean isWalkable, final boolean isInteractable, final int posx,
                final int posy) {
            super(bt, isWalkable, isInteractable, posx, posy);
        }

        public void plant(final SeedType st) {
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
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            BlockImpl other = (BlockImpl) obj;
            if (blockType != other.blockType) {
                return false;
            }
            if (isInteractable != other.isInteractable) {
                return false;
            }
            if (isWalkable != other.isWalkable) {
                return false;
            }
            if (posx != other.posx) {
                return false;
            }
            if (posy != other.posy) {
                return false;
            }
            return true;
        }

        public static final int SIZE = 50;
        private final BlockType blockType;
        private final boolean isWalkable;
        private final boolean isInteractable;
        private final int posx;
        private final int posy;

        BlockImpl(final BlockType bt, final boolean isWalkable, final boolean isInteractable, final int posx,
                final int posy) {
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
            if (this.blockType == BlockType.STALL) {
                return true;
            }
            return false;
        }

    }

}
