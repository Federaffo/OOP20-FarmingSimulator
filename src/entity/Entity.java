package entity;

import java.util.Set;
import gameMap.Block;

public interface Entity {
	public void move();
	public void checkCollision(Set<Block> map);
	
	public void setUp(boolean isMoving);
	public void setDown(boolean isMoving);
	public void setLeft(boolean isMoving);
	public void setRight(boolean isMoving);
	
	public Direction getDirection();
	public boolean isMoving();
	
	public int getPosX();
	public int getPosY();
	public Block blockPosition();
}

