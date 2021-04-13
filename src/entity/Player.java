package entity;

import java.awt.Rectangle;
import java.util.Set;

import com.google.gson.annotations.Expose;

import gameMap.Block;

public class Player extends Rectangle implements Entity{
	
	private final static Integer SPEED = 5;
	private final static Integer PLAYER_SIZE = 50;
	private Direction direction;
	private boolean facingRight;
	private boolean isMoving;
	private double money;
	private Inventory bag;
	
	//create a new player in the indicated position
	public Player(Pair<Integer, Integer> position) {
		super(position.getX()*PLAYER_SIZE, position.getY()*PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);

		bag = new Inventory();
		direction = new Direction();
		money = 100;
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
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	//getter coordinates
	public int getPosX() {
		return super.x;
	}
	public int getPosY() {
		return super.y;
	}
	
	//return facing direction
	public boolean isFacingRight() {
		return facingRight;
	}
	
	public Inventory getInventory() {
		return this.bag;
	}

	//move the player in the current direction
	public void move(){
		if(direction.isUp()) super.y -= SPEED;
		if(direction.isDown()) super.y += SPEED;
		if(direction.isLeft()) super.x -= SPEED;
		if(direction.isRight()) super.x += SPEED;
	}
	
	public void checkCollision(Set<Block> map) {
		for (Block block : map) {
			Rectangle temp = this.intersection((Rectangle) block);
			
			if(temp.width>0 && temp.height>0) {
				if(!block.isWalkable()) {
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
	
	
	//return the block the player is standing on
	public Block blockPosition(Set<Block> array ) {
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
	
	
	public double getMoney() {
		return money;
	}
	public void incrementMoney(double moneyToAdd) {
		money += moneyToAdd;
	}
	public void decrease(double moneyToRemove) {
		money -= moneyToRemove;
	}
	
}
