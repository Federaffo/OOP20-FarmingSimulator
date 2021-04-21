package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics2D;

import block.Block;
import block.BlockType;
import block.FieldBlock;
import control.Game;
import entity.Animal;
import entity.Direction;
import entity.Pair;
import item.Seed;
import item.SeedState;
import item.SeedType;
import item.Texturable;

public class MainScreenDrawer extends GameDrawer {
	private static final int BASE_SIZE = 50;
	private static final int ANIMATION_SPEED = 20;
	private static final long serialVersionUID = -8051528011999726915L;
	private int BLOCK_SIZE;
	private double resizer;
	
	public MainScreenDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		this.resizer = screenSize.getWidth() / (BASE_SIZE * 32);
		GenerateHUD(game, screenSize);
		BLOCK_SIZE = (int) (BASE_SIZE * resizer);
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMap(g);
		drawPg(g);
		drawAnimals(g);
		revalidate();
		repaint();
		
	}
	
	private void drawAnimals(Graphics g) {
		for (Animal a : game.getAllAnimals()) {
			int posX = (int) (a.getPosX() * resizer);
			int posY = (int) (a.getPosY() * resizer);
			
			if(a.isReady()) {
				g.setColor(Color.YELLOW);
				((Graphics2D) g).setStroke(new java.awt.BasicStroke(3));
				g.drawRect(posX, posY, BLOCK_SIZE, BLOCK_SIZE);
			}
			
			g.drawImage(Resources.getRes().getTextures(a.getType()), posX, posY, (int) (BASE_SIZE * resizer),
					(int) (BASE_SIZE * resizer), null);
		}
	}

	private void GenerateHUD(Game g, Dimension screenSize) {
		final int iconScaleDim = (int) (screenSize.width * 0.02);

		JPanel panelHUD = new JPanel();
		JPanel panelHB = new JPanel();
		JPanel tmpHUD = new JPanel();
		JPanel tmpHB = new JPanel();

		panelHUD.setOpaque(false);

		/* Panel Money */
		JPanel moneyPanel = new JPanelHUD(iconScaleDim, Texture.MONEY, true, "0", g);
		/* fine */

		/* Panel Time */
		JPanel timePanel = new JPanelHUD(iconScaleDim, Texture.TIME, true, "00:00");
		/* Fine */

		/* Panel info */
		JPanel infoPanel = new JPanelHUD(iconScaleDim, Texture.INFO, true, "Press X for info");
		/* fine panel info */
		
		/* Panel UnlockPrice */
		JPanel unlockPanel =new JPanelHUD(iconScaleDim, Texture.LOCK,true,"0",g);
		/* fine panel price*/

		/* Add HUD compoments to a temporary Panel */
		tmpHUD.add(moneyPanel); // serve così possiamo
		tmpHUD.add(unlockPanel);
		tmpHUD.add(timePanel); // settare l'opacità
		tmpHUD.add(infoPanel); // e il colore su questo panel
		tmpHUD.setOpaque(true);
		tmpHUD.setBackground(new Color(204, 136, 0, 180));
		panelHUD.add(tmpHUD);
		panelHUD.setVisible(true);

		/* add HotBar components to a temporary Panel */
		tmpHB.add(new JPanelHB());
		tmpHB.setOpaque(true);
		tmpHB.setBackground(new Color(255, 200, 200, 180));
		panelHB.add(tmpHB);
		panelHB.setOpaque(false);

		setLayout(new BorderLayout());
		add(panelHUD, BorderLayout.NORTH);
		add(panelHB, BorderLayout.SOUTH);
	}

	private void drawMap(Graphics g) {

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 18; j++) {
				Block block = game.getMap().getBlock(new Pair<Integer, Integer>(i, j));
				game.getPlayer();

				g.drawImage(Resources.getRes().getTextures(block.getType()), i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE,
						BLOCK_SIZE, null);

				if (block.getType() == BlockType.FIELD) {
					FieldBlock fieldBlock = (FieldBlock) block;
					if (!fieldBlock.isEmpty()) {
						Seed seed = fieldBlock.getSeed();
						if (seed.getSeedState() == SeedState.PLANTED) {
							if(seed.getSeedType().equals(SeedType.APPLE_SEED) || seed.getSeedType().equals(SeedType.ORANGE_SEED) || seed.getSeedType().equals(SeedType.CHERRY_SEED)) {
								g.drawImage(Resources.getRes().getTextures(Texture.TREE), i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE,								
										BLOCK_SIZE, null);
							}else {
								g.drawImage(Resources.getRes().getTextures(Texture.SEED), i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE,								
										BLOCK_SIZE, null);
							}
						} else if (seed.getSeedState() == SeedState.GROWN) {
							g.drawImage(Resources.getRes().getTextures(seed.getFoodType()), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}
					}

				}
			}
		}
	}

	private void drawPg(Graphics g) {
		int offsetY = 20;
		int playerXSize = 40;
		int playerYSize = 70;
		Direction dir = game.getPlayer().getDirection();
		int posX = (int) (game.getPlayer().getPosX() * resizer);
		int posY = (int) (game.getPlayer().getPosY() * resizer);

		g.drawImage(Resources.getRes().getPlayerInDirection(dir), posX, posY - (int) (offsetY * resizer), (int) (playerXSize * resizer),
				(int) (playerYSize * resizer), null);
	}


	private class JPanelHB extends JPanel {
		private JLabel label = new JLabel();

		public JPanelHB() {
			add(label, new BorderLayout().SOUTH);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Texturable currSeed;
			if (game.getPlayer().getInventory().getCurrentSeed().isPresent()) {
				currSeed = game.getPlayer().getInventory().getCurrentSeed().get().getX();
				label.setText(game.getPlayer().getInventory().getCurrentSeed().get().getY().toString());
			} else {
				game.getPlayer().getInventory().nextSeed();
				if (game.getPlayer().getInventory().getCurrentSeed().isPresent()) {
					currSeed = game.getPlayer().getInventory().getCurrentSeed().get().getX();
					label.setText(game.getPlayer().getInventory().getCurrentSeed().get().getY().toString());
				} else {
					currSeed = Texture.EMPTY;
					label.setText(Integer.toString(0));

				}
			}

			ImageIcon boxIcon = new ImageIcon(new ImageIcon(Resources.getRes().getTextures(currSeed)).getImage()
					.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			label.setIcon(boxIcon);
			label.setBackground(new Color(255, 255, 255, 50));
		}
	}

}