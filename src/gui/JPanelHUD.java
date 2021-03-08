package gui;

import java.awt.Font;
import java.awt.Image;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Resources.texture;

public class JPanelHUD extends JPanel {

	private static final long serialVersionUID = -3672046053597996762L;
	private final int iconH;
	private final int iconW;
	private final texture txt;
	private final boolean changeFont;
	private String defaultText;

	public JPanelHUD(final int iconH, final int iconW, final texture txt,
			final boolean changeFont, final String defaultText) {
		this.iconH = iconH;
		this.iconW = iconW;
		this.txt = txt;
		this.changeFont = changeFont;
		this.defaultText = defaultText;
	}

	public JPanel createPanel() {
		JPanel myPanel = new JPanel();
		myPanel.setBounds(0, 0, iconW, iconH);
		JLabel myLabel = new JLabel();

		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Resources.getTextures(txt)).getImage()
				.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		myLabel.setIcon(imageIcon);
		myLabel.setText(defaultText);
		if (changeFont == true) {
			myLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
		}
		myPanel.add(myLabel);
		myPanel.setOpaque(true);
		return myPanel;
	}
}
