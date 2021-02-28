package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import gui.WindowManager;

public class Engine {
    
    private WindowManager window;
    private GameDrawer drawer; 
    private Timer timer;
    private Game game;
    
    public Engine() {
        window = new WindowManager();
        timer = new Timer(20, new GameLoop());
    }

    public void start() {
        drawer = new GameDrawer();
        drawer.setFocusable(true);
        drawer.addKeyListener(new MyKeyListener());
        window.addPanel(drawer);
        timer.start();
    }
    

    private class GameLoop implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            
            
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
