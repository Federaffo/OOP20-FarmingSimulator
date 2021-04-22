package entity;

public class Direction {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Direction() {

    }

    /**
     * @return true if the entity is going up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * This method set true if the direction is up.
     * @param up
     */
    public void setUp(final boolean up) {
        this.up = up;
    }

    /**
     * @return true if the direction is down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * This method set true if the direction is down.
     * @param down
     */
    public void setDown(final boolean down) {
        this.down = down;
    }

    /**
     * @return true if the direction is left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * This method set true if the direction is left.
     * @param left
     */
    public void setLeft(final boolean left) {
        this.left = left;
    }

    /**
     * @return true if the direction is right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * This method set true if the direction is right.
     * @param right
     */
    public void setRight(final boolean right) {
        this.right = right;
    }

    /**
     * @return true if the entity is not moving
     */
    public boolean isAllFalse() {
        if (!up && !right && !down && !left) {
            return true;
        }
        return false;
    }
}
