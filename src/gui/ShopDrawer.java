package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import engine.Game;
import gameShop.Shop;

public class ShopDrawer extends GameDrawer {
	private static final long serialVersionUID = 5108963132975063659L;
	private Game game;

	public ShopDrawer(Game g, Dimension screenSize) {
		super(g,screenSize);
		this.game = g;
		final int HGAP=(int) (screenSize.width*0.005);
		final int VGAP=(int) (screenSize.height*0.01);
		final int LEFTB=(int) (screenSize.width*0.08);
		final int RIGHTB=(int) (screenSize.width*0.08);
		final int TOPB=(int) (screenSize.height*0.08);
		final int BOTTOMB=(int) (screenSize.height*0.08);

		setLayout(new GridLayout(2,3,HGAP,VGAP));
		//setBorder(BorderFactory.createEmptyBorder(50,10,50,10)); //padding sullo shopPanel
		
		/* Pannello Title */
		JPanel titlePanel=new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
		JTextArea title = new JTextArea("WELCOME TO THE SHOP!");
		JTextArea descr = new JTextArea("Qui puoi compare materiali e vendere i tuoi oggetti");
		title.setBorder(BorderFactory.createEmptyBorder(TOPB,LEFTB,0,RIGHTB));
		descr.setBorder(BorderFactory.createEmptyBorder(0,LEFTB,BOTTOMB,RIGHTB));
		titlePanel.setBackground(Color.ORANGE);
		title.setBackground(titlePanel.getBackground());
		descr.setBackground(titlePanel.getBackground());
		titlePanel.add(title);
		titlePanel.add(descr);
		/* Fine Pannello Title */
		
		/* Pannello buy */
		JPanel buyPanel = new JPanel();
		String[] itemString = { "Manzo", "Doggo", "Fieno", "Pig" };
		buyPanel.setBackground(Color.PINK);
		buyPanel.setLayout(new BoxLayout(buyPanel,BoxLayout.Y_AXIS));
		buyPanel.setBorder(BorderFactory.createEmptyBorder(TOPB,LEFTB,BOTTOMB,RIGHTB));
		buyPanel.add(new JComboBox<Object>(itemString));
		int startValue=0, minValue=0, maxValue=1000, step=1;
		buyPanel.add(new JSpinner(new SpinnerNumberModel(startValue,minValue,maxValue,step)));
		buyPanel.add(new JButton("COMPRAAAAA"));
		/* Fine Pannello buy */

		/* Pannello sell */
		JPanel sellPanel = new JPanel();
		sellPanel.setLayout(new BoxLayout(sellPanel,BoxLayout.Y_AXIS));
		sellPanel.setBorder(BorderFactory.createEmptyBorder(TOPB,LEFTB,BOTTOMB,RIGHTB));
		sellPanel.setBackground(Color.CYAN);	
		sellPanel.add(new JButton("SELL"));
		JButton sellAll = new JButton("SELL ALL");
		sellPanel.add(sellAll);
		
		sellAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.sellAll();
			}
		});
		
		/* Fine Pannello sell */
		
		/* Pannello Invetario */
		JPanel inventPanel=new JPanel();
		inventPanel.add(new JLabel(""));
		inventPanel.add(new JLabel("Inventario"));
		inventPanel.add(new JLabel(""));
		inventPanel.setBackground(Color.ORANGE);
		

		
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