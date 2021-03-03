package gui;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import engine.Game;
import entity.Direction;
import gui.Resources.textures;

public class MainScreenDrawer extends GameDrawer {
	private static final int ANIMATION_SPEED = 20;
	private static final long serialVersionUID = -8051528011999726915L;
	private int animationDelay = 0;
	private int frame = 0;
	private Resources res = new Resources();
	
	public MainScreenDrawer(Game game) {
		super(game);
		res.load();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawPg(g);

	}

	private void drawPg(Graphics g) {
		
		animationDelay += 1;
		animationDelay %= ANIMATION_SPEED;
		frame =(int) animationDelay / (ANIMATION_SPEED / 2);

		Direction dir = game.getPlayer().getDirection();
		
		if(dir.isRight()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_RIGHT.getIndx() + frame), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}else if(dir.isLeft()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_LEFT.getIndx() + frame), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}else if(dir.isDown()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_DOWN.getIndx() + frame), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}else if(dir.isUp()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_UP.getIndx() + frame), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}else {
			g.drawImage(Resources.getAtIndx(textures.PLAYER.getIndx()), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
		}

	}
}
