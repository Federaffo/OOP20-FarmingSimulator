package entity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public abstract class growableEntity {
	
	private transient Timer timer;
	private long readyTime;
	
	protected TimerTask grower = new TimerTask() {
		public void run() {
			ready();
		}
	};
	
	abstract void ready();
	protected void schedule() {
		Calendar scheduler = Calendar.getInstance();
		scheduler.add(Calendar.MILLISECOND, (int) this.readyTime);
		timer = new Timer();
		timer.schedule(this.grower, scheduler.getTime());
	}
}
