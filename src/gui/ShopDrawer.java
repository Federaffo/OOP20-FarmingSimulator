package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import engine.Game;

public class ShopDrawer extends GameDrawer {
    private static final long serialVersionUID = 5108963132975063659L;

    public ShopDrawer(Game g) {
        super(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.drawRect(50, 50, 100, 100);
        super.repaint();
    }

}
