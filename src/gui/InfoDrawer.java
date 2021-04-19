package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import control.Game;
import gui.Resources.texture;

public class InfoDrawer extends GameDrawer {
	private Resources res = new Resources();

	public InfoDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		res.load();
		generateInfoPanel(game, screenSize);
		setOpaque(false);
	}

	private void generateInfoPanel(Game g, Dimension screenSize) {
		final int height = (int) (screenSize.height * 0.09);
		final int width = (int) (screenSize.width * 0.6);
		
		final int titleHeight = height/2;
		final int titleWidth = width/2;
		
		final int RGTB = (int) (screenSize.width * 0.22);
		final int LFTB = (int) (screenSize.width * 0.22);
		final int UPPB = (int) (screenSize.width * 0.03);
		final int BTMB = (int) (screenSize.width * 0.2);

		JPanel infoPanel = new JPanel();
		JPanel commands = new JPanel();
		JPanel gameInfo = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBackground(new Color(255, 255, 204, 100));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(UPPB, LFTB, BTMB, RGTB));

		infoPanel.add(new JPanelINFO("General Commands", titleHeight, titleWidth));
		infoPanel.add(new JPanelINFO(height, width, texture.WASD, "MOVEMENT ", "[W-A-S-D] type this button to move"));
		infoPanel.add(new JPanelINFO(height, width, texture.F_KEY, "INTERACT", "[F] press this to plant and harvest"));
		infoPanel.add(new JPanelINFO(height, width, texture.E_KEY, "MARKET", "[E] press to enter the shop menu view"));
		infoPanel.add(new JPanelINFO(height, width, texture.MOUSE_WHEEL, "HOTBAR", "[MOUSE_WHEEL] change hotbar selection"));

		infoPanel.add(new JPanelINFO("Game HUD Info", titleHeight, titleWidth));
		infoPanel.add(new JPanelINFO(height, width, texture.MONEY, "MONEY",
				"show the current amount of money the player have"));
		infoPanel.add(new JPanelINFO(height, width, texture.LOCK, "LOCK",
				"show the current unlock price of the Locked Field Block"));
		infoPanel.add(new JPanelINFO(height, width, texture.TIME, "CLOCK", "show time elapsed from the beginning"));
		// setOpaque(false);
		add(infoPanel);

	}
}
