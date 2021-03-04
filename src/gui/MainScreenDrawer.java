package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import engine.Game;
import gui.Resources.textures;

public class MainScreenDrawer extends GameDrawer {
	private static final long serialVersionUID = -8051528011999726915L;
	private int indx = 0;

	private Resources res = new Resources();
	
	public MainScreenDrawer(Game game, Dimension screenSize) {
		super(game,screenSize);
		res.load();
	}

	@Override
	public void paintComponent(Graphics g) {


		super.paintComponent(g);
		drawPg(g);

		// super.repaint();
	}

	private void drawPg(Graphics g) {
		indx += 1;
		indx %= 20;
		int x = indx / 10;
		if (game.getPlayer().isMoving()) {
			if (game.getPlayer().isFacingRight()) {
				g.drawImage(res.getAtIndx(textures.PLAYER_RIGHT.getIndx() + x), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
			} else {
				g.drawImage(res.getAtIndx(textures.PLAYER_LEFT.getIndx() + x), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
			}
		}else {
			g.drawImage(res.getAtIndx(textures.PLAYER.getIndx()), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}
	}
}
