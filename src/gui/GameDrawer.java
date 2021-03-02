package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import engine.Game;
public abstract class GameDrawer extends JPanel {
    private static final long serialVersionUID = -7700514648149727065L;

    protected Game game;
    
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
            game.keyPressed(e);          
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e);                      
        }
    }
}
