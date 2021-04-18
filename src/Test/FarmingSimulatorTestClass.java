package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.Engine;
import engine.Game;
import entity.Pair;
import entity.Player;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gameMap.Map;

public class FarmingSimulatorTestClass {
	private final int BLOCKSIZE=50;
	private Game g = null;
	private Player pg = null;
	private Map map= null;
	
	@BeforeEach
	public void initGame() {
		Engine engine = new Engine();
        engine.update(false);
		g=engine.getGame();
		pg=g.getPlayer();
		map=g.getMap();	
	}
	
	@Test
	public void testPlayerMovement() {
		pg.setUp(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setUp(false);
		
		pg.setLeft(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setLeft(false);
		
		pg.setDown(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,100),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setDown(false);
		
		pg.setRight(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(100,100),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setRight(false);
		
	}

	@Test
	public void testPlayerPlant() {		
		assertEquals(map.getBlock(2, 2).getType(),BlockType.FIELD);
		assertEquals(((FieldBlock)map.getBlock(2,2)).isEmpty(),true);
		g.interact();
		assertEquals(((FieldBlock)map.getBlock(2,2)).isEmpty(),false);
	}

	public void testPlayerAnimalInteraction() {

	}
	
	public void testUnlockBlock() {
		
	}
}
