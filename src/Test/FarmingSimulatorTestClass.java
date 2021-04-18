package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import engine.Engine;
import engine.Game;
import engine.GameImpl;
import entity.Pair;
import entity.Player;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gameMap.Map;

public class FarmingSimulatorTestClass {
	private final int BLOCKSIZE=50;
	private GameImpl g = null;
	private Player pg = null;
	private Map map= null;
	
	@BeforeEach
	public void initGame() {
		Engine engine = new Engine();
        engine.CreateGame();
		Game g=engine.getGame();
		pg=g.getPlayer();
		map=g.getMap();
		System.out.println(g+" init");
		
	}
	
	@Test
	public void testPlayerMovement() {
		pg.setUp(true);
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setLeft(true);
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setDown(true);
		assertEquals(new Pair<>(50,100),new Pair<>(pg.getPosX(),pg.getPosY()+BLOCKSIZE));
		pg.setRight(true);
		assertEquals(new Pair<>(100,100),new Pair<>(pg.getPosX()+BLOCKSIZE,pg.getPosY()+BLOCKSIZE));
		
		
	}

	@Test
	public void testPlayerPlant() {
		System.out.println(g+" plant");
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
