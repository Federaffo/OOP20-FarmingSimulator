package gui;

import java.awt.Graphics;
import javax.swing.JPanel;

import engine.Game;

public abstract class GameDrawer extends JPanel{
    
    private Game game;
    
    public GameDrawer(Game game) {
        this.game = game;
    }
    
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
    
}
