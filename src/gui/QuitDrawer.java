package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import engine.Game;

public class QuitDrawer extends GameDrawer {
	private Resources res = new Resources();

	public QuitDrawer(Game game, Dimension screenSize) {
		super(game, screenSize);
		res.load();
		generateQuitPanel(game,screenSize);
	
	}

	private void generateQuitPanel(Game g, Dimension screenSize) {
		final int RIGHTB=(int) -(screenSize.width*0.90);
		
		JButton quit=new JButton();
		quit.setText("X");
		quit.setBackground(Color.red);
		quit.setFont(new Font("Arial", Font.BOLD, 30));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.play();
			}
		});
		setBorder(BorderFactory.createEmptyBorder(0,0,0,RIGHTB));
		
		add(quit);
		
		setOpaque(false);
	}
}
