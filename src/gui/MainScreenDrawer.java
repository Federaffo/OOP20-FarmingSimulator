package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import engine.Game;

public class MainScreenDrawer extends GameDrawer{
    private static final long serialVersionUID = -8051528011999726915L;

    public MainScreenDrawer(Game game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.drawRect(game.x, game.y, 50, 50);
        //super.repaint();
    }
}
