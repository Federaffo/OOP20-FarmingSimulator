package entity;

import java.util.Set;
import java.util.function.Predicate;

import block.Block;

public interface Entity {

    // move entity in the current direction
    void move();

    // check if the entity is on a blocked block
    // if it is, then this take the entity back to the walkable block
    <Block> void checkCollision(Set<Block> map, Predicate<Block> filter);

    // set entity directions
    void setUp(boolean isMoving);

    void setDown(boolean isMoving);

    void setLeft(boolean isMoving);

    void setRight(boolean isMoving);

    // get entity direction
    Direction getDirection();

    // getter isMoving
    boolean isMoving();

    // move entity to that position
    void moveTo(Pair<Integer, Integer> pos);

    // getter entity coordinates
    int getPosX();

    int getPosY();

    // get the block the entity is standing on
    Block blockPosition(Set<Block> array);
}
