package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import engine.Game;

public class ShopDrawer extends GameDrawer {
    private static final long serialVersionUID = 5108963132975063659L;

    public ShopDrawer(Game g) {
        super(g);

        add(new JLabel("BENVENUTO ALLO SHOP"));
        
        String[] itemString = { "Manzo", "Doggo", "Fieno", "Pig" };
     
        JPanel buyPanel = new JPanel();
        JPanel sellPanel = new JPanel();
        
        setLayout(new FlowLayout());
        
        //setLayout(new BoxLayout(buyPanel, BoxLayout.PAGE_AXIS));

        buyPanel.setLayout(new FlowLayout(1, 50, 50));
        buyPanel.setBackground(Color.CYAN);
        buyPanel.add(new JComboBox<Object>(itemString), FlowLayout.LEFT);
        buyPanel.add(new JSpinner());
        buyPanel.add(new JButton("COMPRAAAAA"),  FlowLayout.RIGHT);

        sellPanel.setLayout(new FlowLayout(1,150,50));
        sellPanel.setBackground(Color.GRAY);
        sellPanel.add(new JButton("SELL ALL"));
        
        add(buyPanel, FlowLayout.CENTER);
        add(sellPanel, FlowLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        //g2d.drawRect(50, 50, 100, 100);
        super.repaint();
    }
}
