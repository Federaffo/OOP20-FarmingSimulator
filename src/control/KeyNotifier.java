package control;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import entity.Player;

public class KeyNotifier {

    private Game game;
    private Player pg;

    public KeyNotifier(final Game game) {
        this.game = game;
        this.pg = game.getPlayer();
    }

    /**
     * @param e
     */
    public void keyPressed(final KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            pg.setUp(true);
            break;
        case KeyEvent.VK_A:
            pg.setLeft(true);
            break;
        case KeyEvent.VK_S:
            pg.setDown(true);
            break;
        case KeyEvent.VK_D:
            pg.setRight(true);
            break;
        case KeyEvent.VK_F:
            game.interact();
            break;
        case KeyEvent.VK_E:
            game.shop();
            break;
        case KeyEvent.VK_X:
            game.info();
        default:
            break;
        }

    }

    /**
     * @param e
     */
    public void keyReleased(final KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            pg.setUp(false);
            break;
        case KeyEvent.VK_A:
            pg.setLeft(false);
            break;
        case KeyEvent.VK_S:
            pg.setDown(false);
            break;
        case KeyEvent.VK_D:
            pg.setRight(false);
            break;
        default:
            break;
        }

    }

    /**
     * @param e
     */
    public void mouseWheelMoved(final MouseWheelEvent e) {
        pg.getInventory().nextSeed();
    }
}
