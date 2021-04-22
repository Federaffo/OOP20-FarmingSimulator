package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;

public class JPanelHUD extends JPanel {

    private static final long serialVersionUID = -3672046053597996762L;
    private static final int FONT_DIMENSION = 20;
    private final Texture txt;
    private Game game;
    private JLabel myLabel;
    private ImageIcon imageIcon;
    private final long start;

    public JPanelHUD(final int scaleDim, final Texture txt, final boolean changeFont, final String defaultText) {
        this.txt = txt;
        myLabel = new JLabel();
        imageIcon = new ImageIcon(new ImageIcon(Resources.getRes().getTextures(txt)).getImage()
                .getScaledInstance(scaleDim, scaleDim, Image.SCALE_DEFAULT));
        myLabel.setIcon(imageIcon);
        myLabel.setText(defaultText);
        if (changeFont) {
            myLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, FONT_DIMENSION));
        }
        this.add(myLabel);
        this.start = System.currentTimeMillis();
    }

    public JPanelHUD(final int scaleDim, final Texture txt, final boolean changeFont, final String defaultText,
            final Game game) {
        this.txt = txt;
        this.game = game;
        myLabel = new JLabel();
        imageIcon = new ImageIcon(new ImageIcon(Resources.getRes().getTextures(txt)).getImage()
                .getScaledInstance(scaleDim, scaleDim, Image.SCALE_DEFAULT));
        myLabel.setIcon(imageIcon);
        myLabel.setText(defaultText);
        if (changeFont) {
            myLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, FONT_DIMENSION));
        }
        this.add(myLabel);
        this.start = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (txt == Texture.MONEY) {
            myLabel.setText(Double.toString(game.getPlayer().getMoney()));
        } else if (txt == Texture.TIME) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            String tmp = (new SimpleDateFormat("mm:ss")).format(new Date(elapsedTimeMillis));
            myLabel.setText(tmp);

        } else if (txt == Texture.LOCK) {
            myLabel.setText(Double.toString(game.getUnlockPrice()));
        }
    }
}
