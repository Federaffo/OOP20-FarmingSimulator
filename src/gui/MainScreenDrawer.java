package gui;

import java.awt.BorderLayout;
import gui.JPanelHUD;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
		BLOCK_SIZE = (int) (BASE_SIZE * resizer);
		GenerateHUD(game, screenSize);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawMap(g);
		drawPg(g);
		drawHUD(g);
		
		repaint();
	}

	private void drawHUD(Graphics g) {
		
	}

	private void GenerateHUD(Game g, Dimension screenSize) {
		final int iconHeight = (int) (screenSize.height * 0.0001);
		final int iconWidth = (int) (screenSize.width * 0.0001);
		
		JPanel panelHUD= new JPanel();/*{
			protected void paintComponent(Graphics g) {
				setOpaque(true);
				g.drawImage(Resources.getTextures(texture.LABELHUD).getScaledInstance(10, 20, Image.SCALE_DEFAULT), 0, 0, null);
				super.paintComponent(g);
			}
		};*/	
		panelHUD.setOpaque(false);
		
		
		/* Panel Money */
		JPanel moneyPanel = new JPanelHUD(iconHeight,iconWidth,texture.MONEY,true,"50000").createPanel() ;
		/* fine */

		/* Panel Time */
		JPanel timePanel =new JPanelHUD(iconHeight,iconWidth,texture.TIME,true,"10:56").createPanel() ;
		/* Fine */

		/* Panel info */
		JPanel infoPanel = new JPanelHUD(iconHeight,iconWidth,texture.INFO,true,"Press X for info").createPanel();
		/* fine panel info */

		panelHUD.add(moneyPanel);
		panelHUD.add(timePanel);
		panelHUD.add(infoPanel);
		panelHUD.setVisible(true);

		/* HOTBAR */
		JPanel panelHB=new JPanel();
		for(int i=0;i<9;i++) {
			JPanel boxPanel = new JPanel();
			JLabel boxLabel= new JLabel();
			ImageIcon boxIcon = new ImageIcon(new ImageIcon(Resources.getTextures(texture.MONEY)).getImage()
					.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
			boxLabel.setIcon(boxIcon);
			boxPanel.add(boxLabel);
			panelHB.add(boxPanel);
		}
		
		panelHB.setOpaque(false);
		/*fine hotbar*/
		
		
		
		setLayout(new BorderLayout());
		add(panelHUD,BorderLayout.NORTH);
		add(panelHB,BorderLayout.SOUTH);
	}

	private void drawMap(Graphics g) {

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 18; j++) {
				Block block = game.getMap().getBlock(i, j);
				Player pg = game.getPlayer();

				Rectangle intersect = new Rectangle((int) (pg.getPosX() * resizer), (int) (pg.getPosY() * resizer), BLOCK_SIZE, BLOCK_SIZE)
						.intersection(new Rectangle(1 * BLOCK_SIZE, 1 * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE));
				if (!intersect.isEmpty()) {
					g.setColor(Color.CYAN);
					g.drawRect(intersect.x, intersect.y, intersect.width, intersect.height);
				}
				// System.out.println(intersect.width + "," + intersect.height);

				if (block.getType() == BlockType.TERRAIN) {
					g.drawImage(Resources.getTextures(BlockType.TERRAIN), i * BLOCK_SIZE, j * BLOCK_SIZE,
							BLOCK_SIZE, BLOCK_SIZE, null);
					
				} else if (block.getType() == BlockType.FIELD) {
					g.drawImage(Resources.getTextures(BlockType.FIELD), i * BLOCK_SIZE, j * BLOCK_SIZE,
							BLOCK_SIZE, BLOCK_SIZE, null);
					FieldBlock fieldBlock = (FieldBlock) block;
					if(!fieldBlock.isEmpty()) {
						Seed seed = fieldBlock.getSeed();
						if(seed.getSeedState()==SeedState.PLANTED) {
							g.drawImage(Resources.getTextures(texture.SEED), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}else if(seed.getSeedState()==SeedState.GROWN) {
							g.drawImage(Resources.getTextures(texture.MONEY), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}
					}
					
				} else if (block.getType() == BlockType.WALL) {
					g.drawImage(Resources.getTextures(BlockType.WALL), i * BLOCK_SIZE, j * BLOCK_SIZE,
							BLOCK_SIZE, BLOCK_SIZE, null);
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
		//System.out.println(posX);
		g.drawImage(Resources.getPlayerInDirection(dir), posX , posY, 40, 70, null);
		
//		if (dir.isRight()) {
//			g.drawImage(Resources.getAtIndx(textures.PLAYER_RIGHT.getIndx() + frame), game.getPlayer().getPosX(),
//					game.getPlayer().getPosY(), 40, 70, null);
//		} else if (dir.isLeft()) {
//			g.drawImage(Resources.getAtIndx(textures.PLAYER_LEFT.getIndx() + frame), game.getPlayer().getPosX(),
//					game.getPlayer().getPosY(), 40, 70, null);
//		} else if (dir.isDown()) {
//			g.drawImage(Resources.getAtIndx(textures.PLAYER_DOWN.getIndx() + frame), game.getPlayer().getPosX(),
//					game.getPlayer().getPosY(), 40, 70, null);
//		} else if (dir.isUp()) {
//			g.drawImage(Resources.getAtIndx(textures.PLAYER_UP.getIndx() + frame), game.getPlayer().getPosX(),
//					game.getPlayer().getPosY(), 40, 70, null);
//		} else {
//			g.drawImage(Resources.getAtIndx(textures.PLAYER.getIndx()), game.getPlayer().getPosX(),
//					game.getPlayer().getPosY(), 40, 70, null);
//		}

	}
}