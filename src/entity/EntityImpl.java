package entity;

import java.awt.Rectangle;
import java.util.Set;
import java.util.function.Predicate;

import block.Block;

public abstract class EntityImpl extends Rectangle implements Entity {


    /**
     * 
     */
    protected Integer SPEED;
    private static final Integer SIZE = 50;
    private Direction direction;
    private boolean isMoving;

    public EntityImpl(final Pair<Integer, Integer> position) {
        super(position.getX() * SIZE, position.getY() * SIZE, SIZE, SIZE);
        direction = new Direction();
    }

    // update isMoving variable
    private void updateIsMoving() {
        if (direction.isAllFalse()) {
            isMoving = false;
        } else {
            isMoving = true;
        }
    }

    /**
     *@param isMoving
     */
    public void setUp(final boolean isMoving) {
        direction.setUp(isMoving);
        updateIsMoving();
    }

    /**
     *@param isMoving
     */
    public void setDown(final boolean isMoving) {
        direction.setDown(isMoving);
        updateIsMoving();
    }

    /**
     *@param isMoving
     */
    public void setLeft(final boolean isMoving) {
        direction.setLeft(isMoving);
        updateIsMoving();
    }

    /**
     *@param isMoving
     */
    public void setRight(final boolean isMoving) {

        direction.setRight(isMoving);
        updateIsMoving();
    }

    /**
     *@param pos
     */
    public void moveTo(final Pair<Integer, Integer> pos) {
        super.x = pos.getX() * SIZE;
        super.y = pos.getY() * SIZE;
    }

    /**
     *
     */
    public void move() {
        if (direction.isUp())
            super.y -= SPEED;
        if (direction.isDown())
            super.y += SPEED;
        if (direction.isLeft())
            super.x -= SPEED;
        if (direction.isRight())
            super.x += SPEED;

    }

    /**
     *@param <Block>
     *@param map
     *@param rightBlockFilter
     */
    public <Block> void checkCollision(final Set<Block> map, final Predicate<Block> rightBlockFilter) {
        for (Block block : map) {
            Rectangle temp = this.intersection((Rectangle) block);

            if (temp.width > 0 && temp.height > 0) {
                if (!rightBlockFilter.test(block)) {
                    if (temp.width >= temp.height) {
                        if (this.y < temp.y) {
                            this.y -= temp.height;
                        } else {
                            this.y += temp.height;
                        }
                    } else {
                        if (this.x < temp.x) {
                            this.x -= temp.width;
                        } else {
                            this.x += temp.width;
                        }
                    }
                }
            }
        }
    }

    /**
     *@return the x position
     */
    public int getPosX() {
        return super.x;
    }

    /**
     *@return the y position
     */
    public int getPosY() {
        return super.y;
    }

    /**
     *@return true if the entity is moving
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     *@return the direction of the movement
     */
    public Direction getDirection() {
        return this.direction;
    }

 
    /**
     * @param array
     *@return the block where the player is standing on
     */
    public Block blockPosition(final Set<Block> array) {
        float area = 0;
        Block currentBlockPos = null;

        for (Block b : array) {
            Rectangle temp = this.intersection((Rectangle) b);
            float tempArea = temp.width * temp.height;

            if (tempArea > area && temp.width > 0 && temp.height > 0) {
                currentBlockPos = b;
                area = tempArea;
            }
        }

        return currentBlockPos;
    }

}
