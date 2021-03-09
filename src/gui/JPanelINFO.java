package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Resources.texture;

public class JPanelINFO extends JPanel {

	private final texture txt;
	private final String title;
	private final String descr;
	private final int height;
	private final int width;

	public JPanelINFO(final int h, final int w, final texture txt,
			final String title, final String descr) {
		this.txt = txt;
		this.title = title;
		this.descr = descr;
		this.height = h;
		this.width = w;

		JPanel myPanel = new JPanel();
		// myPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		myPanel.setPreferredSize(new Dimension(width, height));
		// myPanel.setMaximumSize(new Dimension(100, 500));
		// myPanel.setBorder(BorderFactory.createTitledBorder(title));

		JLabel titleLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Resources.getTextures(txt)).getImage()
				.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		titleLabel.setIcon(imageIcon);
		titleLabel.setText(title);
		titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 30));

		JLabel descrLabel = new JLabel();
		descrLabel.setText(descr);
		descrLabel.setFont(new Font("Arial", Font.ITALIC, 20));

		myPanel.add(titleLabel);
		myPanel.add(descrLabel);
		myPanel.setOpaque(true);
		myPanel.setBackground(Color.white);
		add(myPanel);
		setOpaque(false);
	}
}
