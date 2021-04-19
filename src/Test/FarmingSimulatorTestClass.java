package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import block.BlockType;
import block.FieldBlock;
import block.UnlockableBlock;
import control.Game;
import engine.Engine;
import entity.Pair;
import entity.player.Player;
import gameMap.Map;

public class FarmingSimulatorTestClass {
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
		pg.setDown(false);
		pg.setRight(false);
		
		//Controllo che il player sia sul blocco (2,2)
		assertEquals(map.getBlock(new Pair<>(2, 2)),pg.blockPosition(map.getMapSet()));
		
		//Controllo che il blocco (2,2) sia di tipo FIELD
		assertEquals(map.getBlock(new Pair<>(2, 2)).getType(),BlockType.FIELD);
		
		//Controllo che il blocco sia vuoto, cioè non ha nessun seme piantato
		assertTrue(((FieldBlock)map.getBlock(new Pair<>(2, 2))).isEmpty());
		
		//Pianto un seme nel blocco (2,2)
		g.interact();
		
		//Controllo che il blocco abbia il seme dopo averci interagito e aver inserito un seme
		assertFalse(((FieldBlock)map.getBlock(new Pair<>(2, 2))).isEmpty());
	}
	
	@Test
	public void testUnlockBlock() {
		//muovo il player verso il blocco da sbloccare
		pg.setRight(true);
		IntStream.range(0,90).forEach(i->g.loop());
		pg.setRight(false);
		pg.setDown(true);
		IntStream.range(0,10).forEach(i->g.loop());
		
		//controllo che il Player sia nel blocco (10,2)
		assertEquals(map.getBlock(new Pair<>(10, 2)),pg.blockPosition(map.getMapSet()));
		
		//controllo che il blocco sia effettivamente di tipo LOCKED
		assertEquals(map.getBlock(new Pair<>(10, 2)).getType(),BlockType.LOCKED);
		
		//controllo se di default il blocco LOCKED è bloccato
		assertTrue(((UnlockableBlock)map.getBlock(new Pair<>(10, 2))).isLocked());
		
		//interagisco per sbloccare il blocco
		g.interact();
		
		//dopo aver interagito controllo che venga sbloccato e diventi di tipo FIELD
		assertEquals(map.getBlock(new Pair<>(10, 2)).getType(),BlockType.FIELD);
	}
	
	public void testPlayerAnimalInteraction() {

	}
	
}
