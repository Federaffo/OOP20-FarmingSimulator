package gui;

import java.awt.BorderLayout;
import gui.JPanelHUD;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import engine.Game;
import entity.Direction;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gui.Resources.texture;
import item.Seed;
import item.SeedState;
import item.SeedType;
import item.Texturable;

public class MainScreenDrawer extends GameDrawer {
	private static final int BASE_SIZE = 50;
	private static final int ANIMATION_SPEED = 20;
	private static final long serialVersionUID = -8051528011999726915L;
	private int animationDelay = 0;
	private int frame = 0;
	private int BLOCK_SIZE;
	private double resizer;
	private Resources res = new Resources();

	public MainScreenDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		res.load();
		this.resizer = screenSize.getWidth() / (50 * 32);
		GenerateHUD(game, screenSize);
		BLOCK_SIZE = (int) (BASE_SIZE * resizer);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawMap(g);
		drawPg(g);
		setIcon();
		repaint();
	}

	private void setIcon() {

	}

	private void GenerateHUD(Game g, Dimension screenSize) {
		final int iconScaleDim = (int) (screenSize.width * 0.02);
		
		JPanel panelHUD = new JPanel() ;
		JPanel panelHB=new JPanel();
		JPanel tmpHUD=new JPanel();
		JPanel tmpHB=new JPanel();

		panelHUD.setOpaque(false);


		/* Panel Money */
		JPanel moneyPanel = new JPanelHUD(iconScaleDim, texture.MONEY, true, "50", g);
		/* fine */

		/* Panel Time */
		JPanel timePanel = new JPanelHUD(iconScaleDim, texture.TIME, true, "10:56");
		/* Fine */

		/* Panel info */
		JPanel infoPanel = new JPanelHUD(iconScaleDim, texture.INFO, true, "Press X for info");
		/* fine panel info */
		
		/* Add HUD compoments to a temporary Panel */
		tmpHUD.add(moneyPanel);			//serve così possiamo 
		tmpHUD.add(timePanel);			//settare l'opacità
		tmpHUD.add(infoPanel);			//e il colore su questo panel
		tmpHUD.setOpaque(true);
		tmpHUD.setBackground(new Color(204, 136, 0,180));
		panelHUD.add(tmpHUD);
		panelHUD.setVisible(true);
		 
		/* add HotBar components to a temporary Panel */
		tmpHB.add(new PanelHB());
		tmpHB.setOpaque(true);
		tmpHB.setBackground(new Color(255,200,200,180));
		panelHB.add(tmpHB);
		panelHB.setOpaque(false);

		setLayout(new BorderLayout());
		add(panelHUD, BorderLayout.NORTH);
		add(panelHB, BorderLayout.SOUTH);
	}

	private void drawMap(Graphics g) {

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 18; j++) {
				Block block = game.getMap().getBlock(i, j);
				Player pg = game.getPlayer();

				Rectangle intersect = new Rectangle((int) (pg.getPosX() * resizer), (int) (pg.getPosY() * resizer),
						BLOCK_SIZE, BLOCK_SIZE)
								.intersection(new Rectangle(1 * BLOCK_SIZE, 1 * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE));
				if (!intersect.isEmpty()) {
					g.setColor(Color.CYAN);
					g.drawRect(intersect.x, intersect.y, intersect.width, intersect.height);
				}
				// System.out.println(intersect.width + "," + intersect.height);

				g.drawImage(Resources.getTextures(block.getType()), i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE,
						BLOCK_SIZE, null);

				if (block.getType() == BlockType.FIELD) {
					FieldBlock fieldBlock = (FieldBlock) block;
					if (!fieldBlock.isEmpty()) {
						Seed seed = fieldBlock.getSeed();
						if (seed.getSeedState() == SeedState.PLANTED) {
							g.drawImage(Resources.getTextures(texture.SEED), i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE,
									BLOCK_SIZE, null);
						} else if (seed.getSeedState() == SeedState.GROWN) {
							g.drawImage(Resources.getTextures(seed.getSeedType()), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}
					}

				}
			}
		}
	}

	private void drawPg(Graphics g) {

		animationDelay += 1;
		animationDelay %= ANIMATION_SPEED;
		frame = (int) animationDelay / (ANIMATION_SPEED / 2);

		Direction dir = game.getPlayer().getDirection();
		int posX = (int) (game.getPlayer().getPosX() * resizer);
		int posY = (int) (game.getPlayer().getPosY() * resizer);

		// g.drawRect(posX, posY, 50 * molt, 50 * molt);
		g.drawRect(posX, posY, BLOCK_SIZE, BLOCK_SIZE);
		g.drawImage(Resources.getPlayerInDirection(dir), posX, posY, 40, 70, null);
	}

	private class PanelHB extends JPanel {
		private JLabel label = new JLabel();

		public PanelHB() {
			add(label, new BorderLayout().SOUTH);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Texturable currSeed;
			if (game.getPlayer().getInventory().getCurrentSeed().isPresent()) {
				currSeed = game.getPlayer().getInventory().getCurrentSeed().get().getX();
//				System.out.println(currSeed);
				label.setText(game.getPlayer().getInventory().getCurrentSeed().get().getY().toString());
			} else {
				currSeed = Resources.texture.MONEY;
			}

			ImageIcon boxIcon = new ImageIcon(new ImageIcon(Resources.getTextures(currSeed)).getImage()
					.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			label.setIcon(boxIcon);
			label.setBackground(new Color(255, 255, 255, 50));
		}
	}

}