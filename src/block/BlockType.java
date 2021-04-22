package block;

import item.Texturable;

public enum BlockType implements Texturable {
    /**
     * walkable Block.
     */
    TERRAIN(),
    /**
     * like a TERRAIN but i can plant & harvest seeds.
     */
    FIELD(),
    /**
     * obstacle Block.
     */
    WALL(),
    /**
     * like a FIELD but locked, need to pay to unlock.
     */
    LOCKED(),
    /**
     * aesthetic Block, do nothing.
     */
    WATER(),
    /**
     * like TERRAIN, the only block where Animals can stay.
     */
    STALL();
}
