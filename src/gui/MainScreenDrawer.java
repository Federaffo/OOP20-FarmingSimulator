package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import entity.Direction;
import entity.Player;
import gameMap.Block;
import gameMap.BlockType;
import gameMap.FieldBlock;
import gui.Resources.textures;
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
	}

	private void drawHUD(Graphics g) {
		
	}

	private void GenerateHUD(Game g, Dimension screenSize) {
		final int iconHeight = (int) (screenSize.height * 0.0001);
		final int iconWidth = (int) (screenSize.width * 0.0001);
		
		JPanel labelHUD= new JPanel(){
			protected void paintComponent(Graphics g) {
				setOpaque(true);
				g.drawImage(Resources.getAtIndx(textures.LABELHUD.getIndx()).getScaledInstance(10, 20, Image.SCALE_DEFAULT), 0, 0, null);
				super.paintComponent(g);
			}
		};

		/* Panel Money */
		JPanel moneyPanel = new JPanel() ;
		moneyPanel.setBounds(0, 0, iconWidth, iconHeight);
		JLabel moneyLabel = new JLabel();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(Resources.getAtIndx(textures.MONEY.getIndx())).getImage()
				.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		moneyLabel.setIcon(imageIcon);
		moneyLabel.setText("50000");
		moneyLabel.setFont(new Font("Sans", Font.ITALIC | Font.BOLD, 20));


		moneyPanel.add(moneyLabel);
		moneyPanel.setOpaque(false);
		/* fine panel money */

		/* Panel Time */
		JPanel timePanel = new JPanel();
		timePanel.setBounds(0, 0, iconWidth, iconHeight);
		JLabel timeLabel = new JLabel();

		ImageIcon timeIcon = new ImageIcon(new ImageIcon(Resources.getAtIndx(textures.TIME.getIndx())).getImage()
				.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		timeLabel.setIcon(timeIcon);
		timeLabel.setText("50000");
		timePanel.add(timeLabel);
		/* Fine panel time */

		/* Panel info */
		JPanel infoPanel = new JPanel();
		/* fine panel info */

		labelHUD.add(moneyPanel);
		labelHUD.add(timePanel);
		labelHUD.add(infoPanel);
		labelHUD.setVisible(true);

		add(labelHUD);
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
					g.drawImage(Resources.getAtIndx(textures.GRASS.getIndx()), i * BLOCK_SIZE, j * BLOCK_SIZE,
							BLOCK_SIZE, BLOCK_SIZE, null);
					
				} else if (block.getType() == BlockType.FIELD) {
					g.drawImage(Resources.getAtIndx(textures.FARMLAND.getIndx()), i * BLOCK_SIZE, j * BLOCK_SIZE,
							BLOCK_SIZE, BLOCK_SIZE, null);
					FieldBlock fieldBlock = (FieldBlock) block;
					if(!fieldBlock.isEmpty()) {
						Seed seed = fieldBlock.getSeed();
						if(seed.getSeedState()==SeedState.PLANTED) {
							g.drawImage(Resources.getAtIndx(textures.SEED.getIndx()), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}else if(seed.getSeedState()==SeedState.GROWN) {
							g.drawImage(Resources.getAtIndx(textures.MONEY.getIndx()), i * BLOCK_SIZE, j * BLOCK_SIZE,
									BLOCK_SIZE, BLOCK_SIZE, null);
						}
					}
					
				} else if (block.getType() == BlockType.OBSTACLE) {
					g.drawImage(Resources.getAtIndx(textures.WALL.getIndx()), i * BLOCK_SIZE, j * BLOCK_SIZE,
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
		if (dir.isRight()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_RIGHT.getIndx() + frame), game.getPlayer().getPosX(),
					game.getPlayer().getPosY(), 40, 70, null);
		} else if (dir.isLeft()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_LEFT.getIndx() + frame), game.getPlayer().getPosX(),
					game.getPlayer().getPosY(), 40, 70, null);
		} else if (dir.isDown()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_DOWN.getIndx() + frame), game.getPlayer().getPosX(),
					game.getPlayer().getPosY(), 40, 70, null);
		} else if (dir.isUp()) {
			g.drawImage(Resources.getAtIndx(textures.PLAYER_UP.getIndx() + frame), game.getPlayer().getPosX(),
					game.getPlayer().getPosY(), 40, 70, null);
		} else {
			g.drawImage(Resources.getAtIndx(textures.PLAYER.getIndx()), game.getPlayer().getPosX(),
					game.getPlayer().getPosY(), 40, 70, null);
		}

	}
}