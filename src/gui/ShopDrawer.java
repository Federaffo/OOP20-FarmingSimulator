package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import engine.Game;

public class ShopDrawer extends GameDrawer {
    private static final long serialVersionUID = 5108963132975063659L;

    public ShopDrawer(Game g) {
        super(g);

        add(new JLabel("BENVENUTO ALLO SHOP"), BorderLayout.NORTH);
        
        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        
        //setLayout(new BoxLayout(new Container(), 2));
        add(new JComboBox<Object>(petStrings), BorderLayout.NORTH);
        add(new JTextArea(), BorderLayout.CENTER);
        add(new JButton("COMPRAAAAA"),BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawRect(50, 50, 100, 100);
        super.repaint();
    }
}
