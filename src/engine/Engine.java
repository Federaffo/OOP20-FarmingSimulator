package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.MainScreenDrawer;
import gui.ShopDrawer;
import gui.WindowManager;

public class Engine {
    
    private WindowManager window;
    private Timer timer;
    private Game game;
    
    public Engine() {
        game = new Game();
        window = new WindowManager(game);
        timer = new Timer(16, new GameLoop());
    }

    public void start() {
        timer.start();
    }
    

    private class GameLoop implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("switch");
                //window.switchPanel();
            	game.loop();
                //window.showShop();
            	window.showMainScreen();
        }
    }
    

}
