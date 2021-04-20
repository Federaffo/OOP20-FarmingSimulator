package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;

public class JPanelHUD extends JPanel {

	private static final long serialVersionUID = -3672046053597996762L;
	private final int scaleDim;
	private final Texture txt;
	private final boolean changeFont;
	private String defaultText;
	private Game game;
	private JLabel myLabel;
	private JPanel myPanel;
	private ImageIcon imageIcon;
	private final long start;

	public JPanelHUD(final int scaleDim, final Texture txt, final boolean changeFont, final String defaultText) {
		this.scaleDim = scaleDim;
		this.txt = txt;
		this.changeFont = changeFont;
		this.defaultText = defaultText;
		myLabel = new JLabel();
		imageIcon = new ImageIcon(new ImageIcon(ResourcesLazy.getRes().getTextures(txt)).getImage().getScaledInstance(scaleDim,
				scaleDim, Image.SCALE_DEFAULT));
		myLabel.setIcon(imageIcon);
		myLabel.setText(defaultText);
		if (changeFont == true) {
			myLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
		}
		this.add(myLabel);
		this. start = System.currentTimeMillis();
	}

	public JPanelHUD(final int scaleDim, final Texture txt, final boolean changeFont, final String defaultText,
			Game game) {
		this.scaleDim = scaleDim;
		this.txt = txt;
		this.changeFont = changeFont;
		this.defaultText = defaultText;
		this.game = game;
		myLabel = new JLabel();
		imageIcon = new ImageIcon(new ImageIcon(ResourcesLazy.getRes().getTextures(txt)).getImage().getScaledInstance(scaleDim,
				scaleDim, Image.SCALE_DEFAULT));
		myLabel.setIcon(imageIcon);
		myLabel.setText(defaultText);
		if (changeFont == true) {
			myLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
		}
		this.add(myLabel);
		this. start = System.currentTimeMillis();
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (txt == Texture.MONEY) {
			myLabel.setText(Double.toString(game.getPlayer().getMoney()));
		} else if (txt == Texture.TIME) {
			long elapsedTimeMillis = System.currentTimeMillis() - start;
			String tmp=(new SimpleDateFormat("mm:ss")).format(new Date(elapsedTimeMillis));
			myLabel.setText(tmp);

		}else if(txt==Texture.LOCK) {
			myLabel.setText(Double.toString(game.getUnlockPrice()));
		}
	}
}
