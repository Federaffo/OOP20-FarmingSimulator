package entity;

import java.util.Set;
import gameMap.Block;

public interface EntityInterface {
	
	//move entity in the current direction
	public void move();
	//check if the entity is on a blocked block
	//if it is, then this take the entity back to the walkable block
	public void checkCollision(Set<Block> map);
	
	//set entity directions
	public void setUp(boolean isMoving);
	public void setDown(boolean isMoving);
	public void setLeft(boolean isMoving);
	public void setRight(boolean isMoving);
	
	//get entity direction
	public Direction getDirection();
	//getter isMoving
	public boolean isMoving();
	
	//getter entity coordinates
	public int getPosX();
	public int getPosY();
	//get the block the entity is standing on
	public Block blockPosition(Set<Block> array);
}

