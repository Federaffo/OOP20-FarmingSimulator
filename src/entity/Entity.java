package entity;

import java.awt.Rectangle;
import java.util.Set;
import java.util.function.Predicate;

import gameMap.Block;

public abstract class Entity extends Rectangle implements EntityInterface {
	
	protected Integer SPEED;
	private final static Integer SIZE = 50;
	private Direction direction;
	private boolean isMoving;

	public Entity(Pair<Integer, Integer> position) {
		super(position.getX()*SIZE, position.getY()*SIZE, SIZE, SIZE);
		direction = new Direction();
	}
	
	//update isMoving variable
	private void updateIsMoving() {
		if (direction.isAllFalse()) {
			isMoving = false;
		} else {
			isMoving = true;
		}
	}

	public void setUp(boolean isMoving) {
		direction.setUp(isMoving);
		updateIsMoving();
	}
	public void setDown(boolean isMoving) {
		direction.setDown(isMoving);
		updateIsMoving();
	}
	public void setLeft(boolean isMoving) {
		direction.setLeft(isMoving);
		updateIsMoving();
	}
	public void setRight(boolean isMoving) {

		direction.setRight(isMoving);
		updateIsMoving();
	}

	public void move() {
		if(direction.isUp()) super.y -= SPEED;
		if(direction.isDown()) super.y += SPEED;
		if(direction.isLeft()) super.x -= SPEED;
		if(direction.isRight()) super.x += SPEED;
		
	}
	public <Block> void checkCollision(Set<Block> map, Predicate<Block> rightBlockFilter) {
		for (Block block : map) {
			Rectangle temp = this.intersection((Rectangle) block);
			
			if(temp.width>0 && temp.height>0) {
				if(!rightBlockFilter.test(block)) {
					if(temp.width >= temp.height) {
						if(this.y < temp.y) {
							this.y -= temp.height;  
						}else {
							this.y += temp.height;  
						}
					}else {
						if(this.x < temp.x) {
							this.x -= temp.width;  
						}else {
							this.x += temp.width;  
						}
					}
				}
			}
		}
	}
	
	public int getPosX() {
		return super.x;
	}
	public int getPosY() {
		return super.y;
	}

	public boolean isMoving() {
		return isMoving;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Block blockPosition(Set<Block> array) {
		float area = 0;
		Block currentBlockPos = null;
			
		for (Block b : array) {
			Rectangle temp = this.intersection((Rectangle) b);
			float tempArea = temp.width * temp.height;
				
				
			if(tempArea > area && temp.width>0 && temp.height>0) {
				currentBlockPos = b;
				area = tempArea;
			}
		}
			
		return currentBlockPos;
	}
}
