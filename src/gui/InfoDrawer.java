package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import gui.Resources.texture;

public class InfoDrawer extends GameDrawer{
	private Resources res = new Resources();

	public InfoDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		res.load();
		generateInfoPanel(game,screenSize);
	
	}
	
	private void generateInfoPanel(Game g,Dimension screenSize) {
		final int height = (int) (screenSize.height * 0.01);
		final int width = (int) (screenSize.width * 0.01);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		JLabel myLabel = new JLabel();

		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Resources.getTextures(texture.PLAYER)).getImage()
				.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		myLabel.setIcon(imageIcon);
		myLabel.setText("WASD - il personaggio si muove per la mappa");
		
		infoPanel.add(myLabel);
		add(infoPanel);		
	}

}
