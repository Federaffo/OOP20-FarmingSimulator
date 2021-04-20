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

	private texture txt;
	private String title;
	private String descr;
	private final int height;
	private final int width;
	private final int imageDim;

	public JPanelINFO(final String title, final int h, final int w) {
		this.title = title;
		this.height = h;
		this.width = w;
		this.imageDim = (int) (this.width*0.07);

		createTitle();
	}

	public JPanelINFO(final int h, final int w, final texture txt, final String title, final String descr) {
		this.txt = txt;
		this.title = title;
		this.descr = descr;
		this.height = h;
		this.width = w;
		this.imageDim = (int) (this.width*0.07);

		createDescr();
	}

	private void createTitle() {
		int titleDim = this.height/2;
		JPanel myPanel = new JPanel();
		myPanel.setPreferredSize(new Dimension(width, height));
		JLabel titleLabel = new JLabel();
		titleLabel.setText(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, titleDim));
		
		myPanel.add(titleLabel);
		myPanel.setOpaque(true);
		myPanel.setBackground(Color.cyan);
		add(myPanel);
		setOpaque(false);
	}

	private void createDescr() {
		int titleDim = this.height/2;
		int descrDim = this.height/3;
		JPanel myPanel = new JPanel();
		myPanel.setPreferredSize(new Dimension(width, height));

		JLabel titleLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Resources.getTextures(txt)).getImage().getScaledInstance(imageDim, imageDim, Image.SCALE_DEFAULT));
		titleLabel.setIcon(imageIcon);
		titleLabel.setText(title);
		titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, titleDim));

		JLabel descrLabel = new JLabel();
		descrLabel.setText(descr);
		descrLabel.setFont(new Font("Arial", Font.ITALIC, descrDim));

		myPanel.add(titleLabel);
		myPanel.add(descrLabel);
		myPanel.setOpaque(true);
		myPanel.setBackground(Color.white);
		add(myPanel);
		setOpaque(false);
	}

}
