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
//    private JPanel drawer; 
//    private JPanel shop;
//    private JLayeredPane lpane = new JLayeredPane();

    //private JPanel mainPanel;
    
    public Engine() {
        game = new Game();
        window = new WindowManager(game);
        timer = new Timer(1000, new GameLoop());
    }

    public void start() {
        timer.start();
    }
    

    private class GameLoop implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("switch");
                //window.switchPanel();
            	game.loop();
                window.showShop();
        }
    }
    
//    public void print() {
//        System.out.println("main");
//    }
//    public void printShop() {
//        System.out.println("SHOP");
//    }
    
    // Cattura pressione tasti
//    private class MyKeyListener implements KeyListener{
//        @Override
//        public void keyTyped(KeyEvent e) {
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            print();               
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//        }
//        
//    }

}
