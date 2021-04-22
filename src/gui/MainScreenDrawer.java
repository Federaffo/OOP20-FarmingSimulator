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
    private static int blockSize;
    private double resizer;
    private int columns;
    private int rows;

    public MainScreenDrawer(final Game game, final Dimension screenSize) {
        super(game, screenSize);
        this.columns = game.getMap().getColumns();
        this.rows = game.getMap().getRows();
        this.resizer = screenSize.getWidth() / (BASE_SIZE * columns);
        generateHUD(game, screenSize);
        blockSize = (int) (BASE_SIZE * resizer);

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

            if (a.isReady()) {
                g.setColor(Color.YELLOW);
                ((Graphics2D) g).setStroke(new java.awt.BasicStroke(3));
                g.drawRect(posX, posY, blockSize, blockSize);
            }

            g.drawImage(Resources.getRes().getTextures(a.getType()), posX, posY, (int) (BASE_SIZE * resizer),
                    (int) (BASE_SIZE * resizer), null);
        }
    }

    private void generateHUD(final Game g, final Dimension screenSize) {
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
        JPanel unlockPanel = new JPanelHUD(iconScaleDim, Texture.LOCK, true, "0", g);
        /* fine panel price */

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

        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                Block block = game.getMap().getBlock(new Pair<Integer, Integer>(i, j));
                game.getPlayer();

                g.drawImage(Resources.getRes().getTextures(block.getType()), i * blockSize, j * blockSize, blockSize,
                        blockSize, null);

                if (block.getType() == BlockType.FIELD) {
                    FieldBlock fieldBlock = (FieldBlock) block;
                    if (!fieldBlock.isEmpty()) {
                        Seed seed = fieldBlock.getSeed();
                        if (seed.getSeedState() == SeedState.PLANTED) {
                            if (seed.getSeedType().equals(SeedType.APPLE_SEED)
                                    || seed.getSeedType().equals(SeedType.ORANGE_SEED)
                                    || seed.getSeedType().equals(SeedType.CHERRY_SEED)) {
                                g.drawImage(Resources.getRes().getTextures(Texture.TREE), i * blockSize, j * blockSize,
                                        blockSize, blockSize, null);
                            } else {
                                g.drawImage(Resources.getRes().getTextures(Texture.SEED), i * blockSize, j * blockSize,
                                        blockSize, blockSize, null);
                            }
                        } else if (seed.getSeedState() == SeedState.GROWN) {
                            g.drawImage(Resources.getRes().getTextures(seed.getFoodType()), i * blockSize,
                                    j * blockSize, blockSize, blockSize, null);
                        }
                    }

                }
            }
        }
    }

    private void drawPg(Graphics g) {
        final int offsetY = 20;
        final int playerXSize = 40;
        final int playerYSize = 70;
        Direction dir = game.getPlayer().getDirection();
        int posX = (int) (game.getPlayer().getPosX() * resizer);
        int posY = (int) (game.getPlayer().getPosY() * resizer);

        g.drawImage(Resources.getRes().getPlayerInDirection(dir), posX, posY - (int) (offsetY * resizer),
                (int) (playerXSize * resizer), (int) (playerYSize * resizer), null);
    }

    private class JPanelHB extends JPanel {
        private JLabel label = new JLabel();

        JPanelHB() {
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
                    .getScaledInstance(BASE_SIZE, BASE_SIZE, Image.SCALE_DEFAULT));
            label.setIcon(boxIcon);
            label.setBackground(new Color(255, 255, 255, 50));
        }
    }

}
