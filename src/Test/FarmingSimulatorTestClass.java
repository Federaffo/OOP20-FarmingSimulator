package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import block.BlockType;
import block.FieldBlock;
import block.UnlockableBlock;
import control.Game;
import control.GameImpl;
import engine.Engine;
import engine.GameSaver;
import engine.Interaction;
import engine.InteractionImpl;
import entity.Animal;
import entity.AnimalImpl;
import entity.AnimalType;
import entity.Pair;
import entity.Player;
import item.FoodType;
import item.SeedState;
import item.SeedType;
import map.Map;

public class FarmingSimulatorTestClass {
    private Game g = null;
    private Player pg = null;
    private Map map = null;
    private Engine engine = null;

    @BeforeEach
    public void initGame() {
        engine = new Engine();
        engine.update(false);
        g = engine.getGame();
        pg = g.getPlayer();
        map = g.getMap();
    }

    @Test
    public void testPlayerMovement() {
        // Muovo il personaggio verso l'alto e controllo la sua posizione
        pg.setUp(true);
        IntStream.range(0, 10).forEach(i -> g.loop());
        assertEquals(new Pair<>(50, 50), new Pair<>(pg.getPosX(), pg.getPosY()));
        pg.setUp(false);

        // Muovo il personaggio verso sinistra e controllo la sua posizione
        pg.setLeft(true);
        IntStream.range(0, 10).forEach(i -> g.loop());
        assertEquals(new Pair<>(50, 50), new Pair<>(pg.getPosX(), pg.getPosY()));
        pg.setLeft(false);

        // Muovo il personaggio verso il basso e controllo la sua posizione
        pg.setDown(true);
        IntStream.range(0, 10).forEach(i -> g.loop());
        assertEquals(new Pair<>(50, 100), new Pair<>(pg.getPosX(), pg.getPosY()));
        pg.setDown(false);

        // Muovo il personaggio verso destra e controllo la sua posizione
        pg.setRight(true);
        IntStream.range(0, 10).forEach(i -> g.loop());
        assertEquals(new Pair<>(100, 100), new Pair<>(pg.getPosX(), pg.getPosY()));
        pg.setRight(false);

    }

    @Test
    public void testCollision() {

        pg.moveTo(new Pair<Integer, Integer>(1, 1));
        // Premo il tasto A
        pg.setLeft(true);
        g.loop();

        // Verifico che non sia uscito dalla mappa
        assertEquals(new Pair<>(50, 50), new Pair<>(pg.getPosX(), pg.getPosY()));

    }

    @Test
    public void testPlayerPlant() {
        Pair<Integer, Integer> b = map
                .getBlockCoordinates(map.getRandomFilterBlock(x -> x.getType() == BlockType.FIELD));
        pg.moveTo(b);

        assertEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.FIELD);

        // Controllo che il blocco sia vuoto, cioè non ha nessun seme piantato
        assertTrue(((FieldBlock) (pg.getBlockPosition(map.getMapSet()))).isEmpty());

        // Pianto un seme nel blocco in posizione b
        g.interact();

        // Controllo che il blocco abbia il seme dopo averci interagito e aver inserito
        // un seme
        assertFalse(((FieldBlock) pg.getBlockPosition(map.getMapSet())).isEmpty());
    }

    @Test
    public void testUnlockBlock() {
        // muovo il player verso il blocco da sbloccare
        Pair<Integer, Integer> b = map
                .getBlockCoordinates(map.getRandomFilterBlock(x -> x.getType() == BlockType.LOCKED));
        pg.moveTo(b);

        // controllo che il blocco sia effettivamente di tipo LOCKED
        assertEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.LOCKED);

        // controllo se di default il blocco LOCKED è bloccato
        assertTrue(((UnlockableBlock) pg.getBlockPosition(map.getMapSet())).isLocked());

        // interagisco per sbloccare il blocco
        g.interact();

        // dopo aver interagito controllo che venga sbloccato e diventi di tipo FIELD
        assertEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.FIELD);
    }

    @Test
    public void testUnlockBlockWithoutMoney() {
        // gli prosciugo il portafoglio
        pg.decreaseMoney(pg.getMoney());

        // muovo il player verso il blocco da sbloccare
        Pair<Integer, Integer> b = map
                .getBlockCoordinates(map.getRandomFilterBlock(x -> x.getType() == BlockType.LOCKED));
        pg.moveTo(b);

        // controllo che il blocco sia effettivamente di tipo LOCKED
        assertEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.LOCKED);

        // controllo se di default il blocco LOCKED è bloccato
        assertTrue(((UnlockableBlock) pg.getBlockPosition(map.getMapSet())).isLocked());

        // interagisco per sbloccare il blocco
        g.interact();

        // dopo aver interagito controllo che non venga sbloccato perché non ho
        // abbastanza soldi per farlo
        assertNotEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.FIELD);

        // quindi controllo che il blocco sia rimasto di tipo LOCKED
        assertEquals(pg.getBlockPosition(map.getMapSet()).getType(), BlockType.LOCKED);

        // e che sia ancora Bloccato
        assertTrue(((UnlockableBlock) pg.getBlockPosition(map.getMapSet())).isLocked());
    }

    @Test
    public void testPlayerAnimalInteraction() throws InterruptedException {
        Interaction interaction = new InteractionImpl();

        //Creo un animale nella stessa posizione del pg
        Animal animal = new AnimalImpl(new Pair<Integer, Integer>(1, 1), AnimalType.CHICKEN);
        pg.moveTo(new Pair<Integer, Integer>(1, 1));

        //aspetto che l'animale sia pronto
        do {
            Thread.sleep(1000);
        } while (!animal.isReady());

        //lancio l'interazione
        interaction.playerAnimal(pg, animal);
        //controllo che mi abbia dato i frutti
        assertTrue(pg.getInventory().getFoods().get(FoodType.EGG) > 0);
    }

    @Test
    public void testBuy() {
        pg.incrementMoney(Double.MAX_VALUE);
        g.buy(SeedType.CHERRY_SEED, 1);
        // dopo aver riempito i soldi del Player provo a comprare un seme, poi controllo
        // che nell'inventario esso sia presente poiché ho abbastanza soldi
        assertTrue(pg.getInventory().getSeeds().get(SeedType.CHERRY_SEED) == 1);
    }

    @Test
    public void testBuyWithoutMoney() {
        pg.decreaseMoney(pg.getMoney());
        g.buy(SeedType.CHERRY_SEED, 1);
        // dopo aver settato i soldi a 0 provo a comprare, poi controllo che
        // nell'inventario io non abbia alcun seme poiché non posso permettermelo
        assertTrue(pg.getInventory().getSeeds().get(SeedType.CHERRY_SEED) == 0);
    }

    @Test
    public void testSell() throws InterruptedException {
        Pair<Integer, Integer> b = map
                .getBlockCoordinates(map.getRandomFilterBlock(x -> x.getType() == BlockType.FIELD));
        pg.moveTo(b);
        if (pg.getInventory().getSeeds().get(SeedType.WHEAT_SEED) == 0) {
            pg.incrementMoney(Double.MAX_VALUE);
            g.buy(SeedType.WHEAT_SEED, 1);
        }

        g.interact();
        // aspetto che il grano cresca
        do {
            Thread.sleep(1000);
        } while (!(((FieldBlock) pg.getBlockPosition(map.getMapSet())).getSeed().getSeedState() == SeedState.GROWN));

        g.interact();
        pg.decreaseMoney(pg.getMoney());
        g.sellAll();
        // setto i soldi a 0 poi vendo il ricavato del raccolto, controllo di avere
        // soldi > 0
        assertTrue(pg.getMoney() > 0);
    }

    @Test
    public void testSaver() {
        // g=new GameImpl();
        GameSaver gs = new GameSaver();
        gs.save(g);

        Engine eng = new Engine();
        eng.update(true);
        Game newGame = eng.getGame();

        assertTrue(newGame.getPlayer().equals(pg));

        assertTrue(newGame.getMap().equals(map));

    }

    @Test
    public void testSaverPlayer() {
        // g=new GameImpl();
        GameSaver gs = new GameSaver();
        gs.save(g);

        Pair<Integer, Integer> b = map
                .getBlockCoordinates(map.getRandomFilterBlock(x -> x.getType() == BlockType.FIELD));
        pg.moveTo(b);

        Engine eng = new Engine();
        eng.update(true);
        Game newGame = eng.getGame();

        assertFalse(newGame.getPlayer().equals(pg));

    }
}
