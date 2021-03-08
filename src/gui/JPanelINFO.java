package gui;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Resources.texture;

public class JPanelINFO extends JPanel{

//	private final int iconH;
//	private final int iconW;
//	private final int scaleDim;
	private final texture txt;
	private final boolean changeFont;
	private String defaultText;

	public JPanelINFO(/*final int iconH, final int iconW, final int scaleDim,*/ final texture txt,
			final boolean changeFont, final String defaultText) {
//		this.iconH = iconH;
//		this.iconW = iconW;
//		this.scaleDim=scaleDim;
		this.txt = txt;
		this.changeFont = changeFont;
		this.defaultText = defaultText;
	}
	
	private JPanel createPanel() {
		JPanel myPanel = new JPanel();
		//myPanel.setBounds(0, 0, iconW, iconH);
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
