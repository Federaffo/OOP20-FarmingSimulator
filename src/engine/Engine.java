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
    private JPanel drawer; 
    private Timer timer;
    private Game game;
    private JPanel shop;
    private JLayeredPane lpane = new JLayeredPane();

    //private JPanel mainPanel;
    
    public Engine() {
        window = new WindowManager();
        timer = new Timer(1000, new GameLoop());
    }

    public void start() {
        shop = new ShopDrawer(game);
        shop.setBackground(Color.red);
        shop.setBounds(0, 0, 600, 400);
        
        drawer = new MainScreenDrawer(game);
        drawer.setBackground(Color.blue);
        drawer.setBounds(0, 0, 600, 400);
        
        lpane.setBounds(0, 0, 600, 400);
        lpane.add(drawer, 0, 0);
        lpane.add(shop, 1, 0);
        
        drawer.addKeyListener(new MyKeyListener());
        
        window.addPanel(lpane);
        timer.start();
    }
    

    private class GameLoop implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("switch");
                window.switchPanel();
        }
    }
    
    // Cattura pressione tasti
    private class MyKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyCode());                
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());                
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyCode());                
        }
        
    }

}
