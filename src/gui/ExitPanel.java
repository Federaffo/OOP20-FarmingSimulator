package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import engine.GameSaver;

public class ExitPanel extends JPanel {
	private static final long serialVersionUID = 9191221380609401080L;

	
	public ExitPanel(Game game) {
		JLabel exit = new JLabel("Stai uscendo dal gioco, se non salvi tutti i tuoi dati andranno persi");
		JLabel question = new JLabel("Vuoi salvare la partita corrente?");
		JButton yes = new JButton("Si");
		JButton no = new JButton("No");

		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameSaver().save(game);
				System.exit(0); 
			}
		});
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(exit);
		add(question);
		add(yes);
		add(no);
	}

	
}
