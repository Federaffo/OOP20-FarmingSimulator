package entity;

import utils.Way;

public class Player {
	
	private String name;
	private Pair<Integer, Integer> pos;
	private Object bag;
	
	public Player(Pair<Integer, Integer> position) {
		pos = position;
	}
	public Player(Object inventory) {
		bag = inventory;
	}
	
	public void move(Direction d){
		if(d.isUp()) pos.setY(pos.getY()+1);
		if(d.isDown()) pos.setY(pos.getY()-1);
		if(d.isLeft()) pos.setX(pos.getX()-1);
		if(d.isRight()) pos.setX(pos.getX()+1);
	}
	public void setDir(Way way) {}
	public void resetDir(Way way) {}
}
