package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import engine.Game;
import gui.Resources.texture;

public class InfoDrawer extends GameDrawer{
	private Resources res = new Resources();

	public InfoDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		res.load();
		generateInfoPanel(game,screenSize);
		setOpaque(false);
	}
	
	private void generateInfoPanel(Game g,Dimension screenSize) {
		final int height = (int) (screenSize.height * 0.1);
		final int width = (int) (screenSize.width * 0.5);
		final int RGTB =(int) (screenSize.width * 0.22);
		final int LFTB =(int) (screenSize.width * 0.22);
		final int UPPB =(int) (screenSize.width * 0.10);
		final int BTMB =(int) (screenSize.width * 0.2);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setBackground(new Color(255, 255, 204,100));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(UPPB,LFTB,BTMB,RGTB));
		
		infoPanel.add(new JPanelINFO(height,width,texture.PLAYER,"MOVEMENT ","[W-A-S-D] type this button to move"));
		infoPanel.add(new JPanelINFO(height,width,texture.PLAYER,"INTERACT","[F] press this to plant and harvest"));
		infoPanel.add(new JPanelINFO(height,width,texture.PLAYER,"MARKET","[E] press to enter the shop menu view"));
		infoPanel.add(new JPanelINFO(height,width,texture.PLAYER,"HOTBAR","[MOUSE_WHEEL] change hotbar selection"));
		//setOpaque(false);
		add(infoPanel);		
	}

}
