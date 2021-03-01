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

import gui.WindowManager;

public class Engine {
    
    private WindowManager window;
    private GameDrawer drawer; 
    private Timer timer;
    //private Game game;
    private ShopPanel shop;
    private JLayeredPane lpane = new JLayeredPane();

    //private JPanel mainPanel;
    
    public Engine() {
        window = new WindowManager();
        timer = new Timer(1000, new GameLoop());
    }

    public void start() {
        //mainPanel = new JPanel();
        //mainPanel.setFocusable(true);
        
        shop = new ShopPanel();
        shop.setBackground(Color.red);
        shop.setBounds(0, 0, 600, 400);
        //shop.setOpaque(true);
//        shop.setFocusable(true);
//        shop.setVisible(true);
//        shop.requestFocusInWindow();
        
        drawer = new GameDrawer();
        drawer.setBackground(Color.blue);
        drawer.setBounds(0, 0, 600, 400);
        //drawer.setOpaque(true);
//        drawer.setFocusable(true);
//        drawer.setVisible(true);

        //mainPanel.add(drawer);
        //mainPanel.add(shop);
        //drawer.requestFocusInWindow();
        
        lpane.setBounds(0, 0, 600, 400);
        lpane.add(drawer, 0, 0);
        lpane.add(shop, 1, 0);
        
        drawer.addKeyListener(new MyKeyListener());
        

        //window.addShopPanel(shop);
        window.addPanel(lpane);
        timer.start();
    }
    

    private class GameLoop implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("switch");
                window.switchPanel();
        }
    }
    
    private class ShopPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            var g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            g2d.drawRect(50, 50, 100, 100);
            super.repaint();
        }
    }
    
    private class GameDrawer extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            var g2d = (Graphics2D) g;
            g2d.setColor(Color.blue);
            g2d.drawRect(10, 10, 100, 100);
            super.repaint();
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
