package gui;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import engine.Game;

public class MainScreenDrawer extends GameDrawer {
	private static final long serialVersionUID = -8051528011999726915L;
	private int indx = 0;
	private String right = "res/right";
	private String left = "res/left";
	private String png = ".png";

	public MainScreenDrawer(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
		try {
			if (game.getPlayer().isMoving()) {
				
				if (game.getPlayer().isFacingRight()) {
					g.drawImage(ImageIO.read(new File(right + x + png)), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
				} else {
					g.drawImage(ImageIO.read(new File(left + x + png)), game.getPlayer().getX(),
							game.getPlayer().getY(), 40, 70, null);
				}
			}else {
				g.drawImage(ImageIO.read(new File("res/stop.png")), game.getPlayer().getX(),game.getPlayer().getY(), 40, 70, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
