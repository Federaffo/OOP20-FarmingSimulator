package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import control.Game;

public class InfoDrawer extends GameDrawer {

    public InfoDrawer(final Game game, final Dimension screenSize) {
        super(game, screenSize);
        generateInfoPanel(game, screenSize);
        setOpaque(false);
    }

    private void generateInfoPanel(final Game g, final Dimension screenSize) {
        final int height = (int) (screenSize.height * 0.09);
        final int width = (int) (screenSize.width * 0.6);

        final int titleHeight = height / 2;
        final int titleWidth = width / 2;

        final int rgtb = (int) (screenSize.width * 0.22);
        final int lftb = (int) (screenSize.width * 0.22);
        final int uppb = (int) (screenSize.width * 0.03);
        final int btmb = (int) (screenSize.width * 0.2);

        JPanel infoPanel = new JPanel();
        JPanel commands = new JPanel();
        JPanel gameInfo = new JPanel();
        Color colorBackground = new Color(255, 255, 204, 100);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(colorBackground);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(uppb, lftb, btmb, rgtb));

        infoPanel.add(new JPanelINFO("General Commands", titleHeight, titleWidth));
        infoPanel.add(new JPanelINFO(height, width, Texture.WASD, "MOVEMENT [W-A-S-D]", " type this button to move"));
        infoPanel.add(new JPanelINFO(height, width, Texture.F_KEY, "INTERACT [F]",
                " press this to interact with plant and animals"));
        infoPanel.add(new JPanelINFO(height, width, Texture.E_KEY, "MARKET [E]", " press to enter the shop menu view"));
        infoPanel.add(
                new JPanelINFO(height, width, Texture.MOUSE_WHEEL, "HOTBAR [MOUSE_WHEEL]", " change hotbar selection"));

        infoPanel.add(new JPanelINFO("Game HUD Info", titleHeight, titleWidth));
        infoPanel.add(new JPanelINFO(height, width, Texture.MONEY, "MONEY",
                "show the current amount of money the player have"));
        infoPanel.add(new JPanelINFO(height, width, Texture.LOCK, "LOCK",
                "show the current unlock price of the Locked Field Block"));
        infoPanel.add(new JPanelINFO(height, width, Texture.TIME, "CLOCK", "show time elapsed from the beginning"));
        // setOpaque(false);
        add(infoPanel);

    }
}
