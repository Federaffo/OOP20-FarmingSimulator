package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import engine.Game;

public class ShopDrawer extends GameDrawer {
	private static final long serialVersionUID = 5108963132975063659L;
	
	
	public ShopDrawer(Game g) {
		super(g);
		final int HBORDERSIZE=(int) (this.getSize().getWidth());
		final int VBORDERSIZE=(int) (this.getHeight()/100)*75;

		System.out.println(HBORDERSIZE);
		setLayout(new GridLayout(2,3,10,10));
		setBorder(BorderFactory.createEmptyBorder(50,10,50,10));
		
		/* Pannello Title */
		JPanel titlePanel=new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
		JLabel title = new JLabel("WELCOME TO THE SHOP!");
		JLabel descr = new JLabel("Qui puoi compare materiali e vendere i tuoi oggetti");
		title.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		descr.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.add(title);
		titlePanel.add(descr);
		/* Fine Pannello Title */
		
		/* Pannello buy */
		JPanel buyPanel = new JPanel();
		String[] itemString = { "Manzo", "Doggo", "Fieno", "Pig" };
		buyPanel.setBackground(Color.PINK);
		//buyPanel.setLayout(new BoxLayout(buyPanel,BoxLayout.Y_AXIS));
		buyPanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		buyPanel.add(new JComboBox<Object>(itemString), FlowLayout.LEFT);
		buyPanel.add(new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1)));
		buyPanel.add(new JButton("COMPRAAAAA"), FlowLayout.RIGHT);
		/* Fine Pannello buy */

		/* Pannello sell */
		JPanel sellPanel = new JPanel();
		sellPanel.setBackground(Color.CYAN);	
		sellPanel.add(new JButton("SELL"));
		sellPanel.add(new JButton("SELL ALL"));
		/* Fine Pannello sell */
		
		/* Pannello Invetario */
		JPanel inventPanel=new JPanel();
		inventPanel.add(new JLabel(""));
		inventPanel.add(new JLabel("Inventario"));
		inventPanel.add(new JLabel(""));
		inventPanel.setBackground(Color.ORANGE);
		inventPanel.setLayout(new GridLayout(4,2));
		inventPanel.add(new JButton("10 x Patate"));
		inventPanel.add(new JButton("120 x Grano"));
		inventPanel.add(new JButton("250 x Carne"));
		inventPanel.add(new JButton(""));
		inventPanel.add(new JButton(""));
		inventPanel.add(new JButton(""));
		inventPanel.add(new JButton(""));
		inventPanel.add(new JButton(""));
		inventPanel.add(new JButton(""));
		/* Fine Pannello Inventario*/
		
		add(titlePanel);
		add(buyPanel);
		add(inventPanel);
		add(sellPanel);
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		// g2d.drawRect(50, 50, 100, 100);
		super.repaint();
	}
}
