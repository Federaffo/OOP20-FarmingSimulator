package entity;

public class Player implements Entity{
	
	private final static Integer SPEED = 5;
	private Pair<Integer, Integer> pos;
	private Inventory bag;
	private Direction direction;
	private boolean facingRight;
	private boolean isMoving;
	
	//create a new player in the indicated position
	public Player(Pair<Integer, Integer> position) {
		this.pos = position;
		bag = new Inventory();
		direction = new Direction();
	}
	
	//method for setting player direction
	public void setUp(boolean isMoving) {
		direction.setUp(isMoving);
		checkIsMoving();
	}
	public void setDown(boolean isMoving) {
		direction.setDown(isMoving);
		checkIsMoving();
	}
	public void setLeft(boolean isMoving) {
		direction.setLeft(isMoving);
		facingRight = false;
		checkIsMoving();
	}
	public void setRight(boolean isMoving) {
		direction.setRight(isMoving);
		facingRight = true;
		checkIsMoving();
	}
	
	private void checkIsMoving() {
		if(direction.isAllFalse()) {
			isMoving = false;
		}else {
			isMoving = true;
		}
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	//getter coordinates
	public int getX() {
		return pos.getX();
	}
	public int getY() {
		return pos.getY();
	}
	
	//return facing direction
	public boolean isFacingRight() {
		return facingRight;
	}

	//move the player in the current direction
	public void move(){
		if(direction.isUp()) pos.setY(pos.getY()-SPEED);
		if(direction.isDown()) pos.setY(pos.getY()+SPEED);
		if(direction.isLeft()) pos.setX(pos.getX()-SPEED);
		if(direction.isRight()) pos.setX(pos.getX()+SPEED);
	}
}