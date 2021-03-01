package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import engine.Game;
public abstract class GameDrawer extends JPanel {
    
    private Game game;
    
    public GameDrawer(Game game) {
        this.game = game;
        addKeyListener(new MyKeyListener());
    }
    
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
    
    private class MyKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("grabbed ");
            game.keyPressed(e);          
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("released ");
            game.keyReleased(e);                      
        }
    }
}
