package entity;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import gameMap.Block;
import item.Food;

public class Animal extends Entity {

	private final AnimalType type;
	private final long readyTime;
	private boolean readyState;
	private Random rnd;
	private transient Timer timer;
	
	public Animal(Pair<Integer, Integer> position, AnimalType animalType) {
		super(position);
		type = animalType;
		SPEED = type.getSpeed();
		readyTime = type.readyTime();
		
		rnd = new Random();
		readyState = false;
		reSchedule();
	}
	
	protected TimerTask grower = new TimerTask() {
		public void run() {
			ready();
		}
	};
	
	private void ready() {
		readyState = true;
	}

	public Food returnFood() {
		readyState = false;
		return type.getReturnFood();
	}
	
	public void reSchedule() {
		Calendar scheduler = Calendar.getInstance();
		scheduler.add(Calendar.MILLISECOND, (int) this.readyTime);
		timer = new Timer();
		timer.schedule(this.grower, scheduler.getTime());
	}
	
	private void setRandomDirection() {
		int x = rnd.nextInt(100);
		
		if(x<=5 && x>=0) {
			setUp(rnd.nextBoolean());
			setDown(rnd.nextBoolean());
			setLeft(rnd.nextBoolean());
			setRight(rnd.nextBoolean());
		}
	}
	
	public void randomMove(Set<Block> map) {
		setRandomDirection();
		move();
		checkCollision(map, x-> x.isInteractable());
	}
	
	public boolean isReady() {
		return readyState;
	}
}
