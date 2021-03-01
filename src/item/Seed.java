package item;

import java.util.*;

public class Seed {

	private final SeedType seedType;
	private SeedState state; /* guardo lo stato della mia pianta */
	private final long growTime; /* true= posso raccogliere, false = non raccoglibile */
	private final Food ofWhichFood; /* mi segno di quale cibo è il mio seme */
	private Timer timerPlant;
	
	public Seed(final SeedType st){
		this.seedType=st;
		this.state=SeedState.PLANTED;
		this.growTime=st.getGrowTime();
		this.ofWhichFood=st.getFoodType();
		Calendar scheduler = Calendar.getInstance();
		scheduler.add(Calendar.MILLISECOND, (int) this.growTime);
		timerPlant=new Timer();
		timerPlant.schedule(this.grower, scheduler.getTime());
	}
	
	protected TimerTask grower = new TimerTask() {
		public void run() {
			Grow();
		}
	};
	
	public void Grow() {
		this.state = SeedState.GROWN;
		timerPlant.cancel();
	}
	
	public Food Harvest() {
		this.state = SeedState.HARVESTED;
		return ofWhichFood;
	}
	
	public SeedState getSeedState() {
		return this.state;
	}
	
	public double getGrowTime() {
		return this.growTime;
	}
	
	public String getFoodType() {
		return this.ofWhichFood.getName();
	}
	
	public SeedType getSeedType() {
		return this.seedType;
	}
	
}
