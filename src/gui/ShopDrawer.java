package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import engine.Game;
import item.SeedType;

public class ShopDrawer extends GameDrawer {
	private static final long serialVersionUID = 5108963132975063659L;
	private Game game;
	private JTextArea invTAfood = new JTextArea();
	private JTextArea invTAseed = new JTextArea();

	public ShopDrawer(Game g, Dimension screenSize) {
		super(g, screenSize);
		this.game = g;
		final int HGAP = (int) (screenSize.width * 0.005);
		final int VGAP = (int) (screenSize.height * 0.01);
		final int LEFTB = (int) (screenSize.width * 0.08);
		final int RIGHTB = (int) (screenSize.width * 0.08);
		final int TOPB = (int) (screenSize.height * 0.08);
		final int BOTTOMB = (int) (screenSize.height * 0.08);
		final Color sfondo = new Color(17, 96, 98);
		final int countSeed = g.getShop().getSeedItemList().size();

		setLayout(new GridLayout(2, 3, HGAP, VGAP));


		/* Pannello Title */
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(sfondo);

		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		JTextArea title = new JTextArea();
		Font font = new Font("Garamond", Font.BOLD, 50);
		List<String> titleContent = List.of("Welcome", "to", "the", "Shop!");
		for (String s : titleContent) {
			title.append(s + " ");
		}
		title.setForeground(Color.WHITE);
		title.setFont(font);
		title.setLineWrap(true);
		title.setEditable(false);

		JTextArea descr = new JTextArea();
		Font font2 = new Font("Segoe Script", Font.BOLD, 20);
		List<String> descrContent = List.of("Here", "you", "can", "buy", "and", "sell", "your", "items");
		for (String s : descrContent) {
			descr.append(s + " ");
		}
		descr.setForeground(Color.WHITE);
		descr.setFont(font2);
		descr.setLineWrap(true);
		descr.setEditable(false);

		title.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, 0, RIGHTB));
		title.setBackground(titlePanel.getBackground());
		descr.setBorder(BorderFactory.createEmptyBorder(0, LEFTB, BOTTOMB, RIGHTB));
		descr.setBackground(titlePanel.getBackground());

		titlePanel.add(title);
		titlePanel.add(descr);
		/* Fine Pannello Title */

		
		/* Pannello Invetario */
		JPanel inventPanel = new JPanel();
		JPanel scrollPanel = new JPanel();
		Font font3 = new Font("Garamond", Font.BOLD, 40);
		JLabel titleInv = new JLabel("Inventario");
		JScrollPane jspF = new JScrollPane(invTAfood);
		JScrollPane jspS = new JScrollPane(invTAseed);
		
		titleInv.setForeground(Color.white);
		titleInv.setFont(font3);
		titleInv.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB/2, RIGHTB));

		inventPanel.setLayout(new BoxLayout(inventPanel, BoxLayout.Y_AXIS));
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
		inventPanel.add(titleInv);
		inventPanel.setBackground(sfondo);
		
		scrollPanel.add(jspF);
		scrollPanel.add(jspS);
		jspF.setBackground(sfondo);
		jspF.setBorder(BorderFactory.createEmptyBorder(0, LEFTB/4, BOTTOMB/2, RIGHTB/4));
		jspS.setBackground(sfondo);
		jspS.setBorder(BorderFactory.createEmptyBorder(0, LEFTB/4, BOTTOMB/2, RIGHTB/4));
		inventPanel.add(scrollPanel);
		inventPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		inventoryUpdate();
		/* Fine Pannello Inventario */

		
		/* Pannello buy */
		JPanel buyPanel = new JPanel();
		buyPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		String[] itemString = new String[countSeed];
		int i = 0;
		for (SeedType seed : g.getShop().getSeedItemList()) {
			itemString[i++] = seed.getName();
		}

		buyPanel.setBackground(sfondo);
		buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.PAGE_AXIS));
		buyPanel.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB, RIGHTB));

		JComboBox<Object> selectSeed = new JComboBox<>(itemString);
		buyPanel.add(selectSeed);

		int startValue = 0, minValue = 0, maxValue = 1000, step = 1;
		JSpinner quantity = new JSpinner(new SpinnerNumberModel(startValue, minValue, maxValue, step));
		buyPanel.add(quantity);

		JTextField prezzoTot = new JTextField(
				"TOT: " + (SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
						* ((Integer) quantity.getValue()));
		prezzoTot.setEditable(false);
		prezzoTot.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		buyPanel.add(prezzoTot);
		buyPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		JButton buy = new JButton("COMPRAAAAA");
		buy.setBorder(BorderFactory.createEmptyBorder(0, LEFTB, 0, RIGHTB));
		buyPanel.add(buy);

		quantity.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				prezzoTot.setText("TOT: "
						+ Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
								* ((Integer) quantity.getValue())));
			}
		});
		selectSeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezzoTot.setText("TOT: "
						+ Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
								* ((Integer) quantity.getValue())));
			}
		});
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (g.buy(SeedType.getSeedType(selectSeed.getSelectedItem().toString()),
						(Integer) quantity.getValue())) {
					JOptionPane.showMessageDialog(buyPanel, "Nice! Purchase made!");
				} else {
					JOptionPane.showMessageDialog(buyPanel, "You haven't got enough  money!");
				}
				inventoryUpdate();
			}
		});
		/* Fine Pannello buy */

		
		/* Pannello sell */
		JPanel sellPanel = new JPanel();
		sellPanel.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB, RIGHTB));
		sellPanel.setBackground(sfondo);
		JButton sellAll = new JButton("SELL ALL YOUR ITEMS");
		sellAll.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB, RIGHTB));

		sellPanel.add(sellAll, BorderLayout.CENTER);

		sellAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double money = g.sellAll();
				JOptionPane.showMessageDialog(sellPanel, "You earned " + money);
				inventoryUpdate();
			}
		});
		/* Fine Pannello sell */

		add(titlePanel);
		add(buyPanel);
		add(inventPanel);
		add(sellPanel);
		
	}
	private void inventoryUpdate() {
		invTAfood.setText("");
		invTAseed.setText("");
		for (var f : game.getPlayer().getInventory().getFood().entrySet()) {
			invTAfood.append("[Food]-> " + f.getKey() + "\t|  [Quantity]-> " + f.getValue() + "\n");
		}
		for (var f : game.getPlayer().getInventory().getSeeds().entrySet()) {
			invTAseed.append("[Seed]-> " + f.getKey() + "\t|  [Quantity]-> " + f.getValue() + "\n");
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		inventoryUpdate();
	}
}