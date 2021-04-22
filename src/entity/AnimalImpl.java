package entity;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import block.Block;
import item.FoodType;

public class AnimalImpl extends EntityImpl implements Animal {

    private final AnimalType type;
    private final long readyTime;
    private boolean readyState;
    private Random rnd;
    private transient Timer timer = new Timer();

    public AnimalImpl(final Pair<Integer, Integer> position, final AnimalType animalType) {
        super(position);
        type = animalType;
        SPEED = type.getSpeed();
        readyTime = type.readyTime();
        rnd = new Random();
        readyState = false;
        reSchedule();
    }

    /**
     * 
     */
    private TimerTask grower = new TimerTask() {
        public void run() {
            ready();
        }
    };

    private void ready() {
        readyState = true;
    }

    /**
     *@return the food of the animal
     */
    public Pair<FoodType, Integer> collect() {
        readyState = false;
        return new Pair<>(type.getReturnFood(), type.getReturnFood().getQuantity());
    }

    private void reSchedule() {
        Calendar scheduler = Calendar.getInstance();
        scheduler.add(Calendar.MILLISECOND, (int) this.readyTime);
        timer.schedule(this.grower, type.readyTime(), type.readyTime());
    }

    private void setDirectionFalse() {
        setUp(false);
        setDown(false);
        setLeft(false);
        setRight(false);
    }

    private void setRandomDirection() {

        if (readyState) {
            setDirectionFalse();
        } else {

            int x = rnd.nextInt(100);

            if (x <= 5 && x >= 0) {
                setUp(rnd.nextBoolean());
                setDown(rnd.nextBoolean());
                setLeft(rnd.nextBoolean());
                setRight(rnd.nextBoolean());
            }
        }

    }

    /**
     * move the animal in a random direction.
     * 
     * @param map
     * 
     */
    public void randomMove(final Set<Block> map) {
        setRandomDirection();
        move();
        checkCollision(map, x -> x.isStall());
    }

    /**
     * @return true if the animal is ready
     */
    public boolean isReady() {
        return readyState;
    }

    /**
     * @return the type of the animal
     */
    public AnimalType getType() {
        return type;
    }
}
