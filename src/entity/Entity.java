package entity;

import java.util.Set;
import java.util.function.Predicate;

import block.Block;

public interface Entity {

    /**
     * move entity in the current direction.
     */
    void move();

    /**
     * @param <Block>
     * @param map
     * @param filter  check if the entity is on a blocked block if it is, then this
     *                take the entity back to the walkable block
     */
    <Block> void checkCollision(Set<Block> map, Predicate<Block> filter);

    /**
     * @param isMoving set the current direction of the entity
     */
    void setUp(boolean isMoving);

    /**
     * @param isMoving set the current direction of the entity
     */
    void setDown(boolean isMoving);

    /**
     * @param isMoving set the current direction of the entity
     */
    void setLeft(boolean isMoving);

    /**
     * @param isMoving set the current direction of the entity
     */
    void setRight(boolean isMoving);

    /**
     * @return the entity direction
     */
    Direction getDirection();

    /**
     * @return a true if the entity has some current direction, false otherwise
     */
    boolean isMoving();

    /**
     * @param pos moves the entity to the current position
     */
    void moveTo(Pair<Integer, Integer> pos);

    /**
     * @return entity X position
     */
    int getPosX();

    /**
     * @return entity Y position
     */
    int getPosY();

    /**
     * @param array
     * @return the Block where the entity is standing on
     */
    Block blockPosition(Set<Block> array);
}
