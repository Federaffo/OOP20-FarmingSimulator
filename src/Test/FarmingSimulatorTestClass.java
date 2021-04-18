package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		//Muovo il personaggio verso l'alto e controllo la sua posizione
		pg.setUp(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setUp(false);
		
		//Muovo il personaggio verso sinistra e controllo la sua posizione
		pg.setLeft(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,50),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setLeft(false);
		
		//Muovo il personaggio verso il basso e controllo la sua posizione
		pg.setDown(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(50,100),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setDown(false);
		
		//Muovo il personaggio verso destra e controllo la sua posizione
		pg.setRight(true);
		IntStream.range(0,10).forEach(i->g.loop());
		assertEquals(new Pair<>(100,100),new Pair<>(pg.getPosX(),pg.getPosY()));
		pg.setRight(false);
		
	}

	@Test
	public void testPlayerPlant() {	
		//muovo il personaggio in diagonale verso il blocco (2,2)
		pg.setDown(true);
		pg.setRight(true);
		IntStream.range(0,10).forEach(i->g.loop());
		//Controllo che il player sia sul blocco (2,2)
		assertEquals(map.getBlock(2, 2),pg.blockPosition(map.getMapSet()));
		//Controllo che il blocco (2,2) sia di tipo FIELD
		assertEquals(map.getBlock(2, 2).getType(),BlockType.FIELD);
		//Controllo che il blocco sia vuoto, cioè non ha nessun seme piantato
		assertTrue(((FieldBlock)map.getBlock(2,2)).isEmpty());
		//Pianto un seme nel blocco (2,2)
		g.interact();
		//Controllo che il blocco abbia il seme dopo averci interagito e aver inserito un seme
		assertFalse(((FieldBlock)map.getBlock(2,2)).isEmpty());
	}

	public void testPlayerAnimalInteraction() {

	}
	
	public void testUnlockBlock() {
		
	}
}
