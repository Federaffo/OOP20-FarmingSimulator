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
import utils.Observer;

public class ShopDrawer extends GameDrawer{
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
		final int LEFTB_SPINNER = LEFTB/4;
		final int RIGHTB_SPINNER = RIGHTB/4;
		final int BOTTOMB_SPINNER = BOTTOMB/2;
		
		final Color sfondo = new Color(17, 96, 98);
		final int countSeed = g.getShop().getSeedItemList().size();
		
		final ObservableShopGUI obsShop= new ObservableShopGUI();
		final Observer sellButton= new ObserverShop();
		final Observer cmbox= new ObserverShop();
		final Observer buyButton= new ObserverShop();
		final Observer spinnerButton= new ObserverShop();
		obsShop.addObserver(sellButton);
		obsShop.addObserver(cmbox);
		obsShop.addObserver(buyButton);
		obsShop.addObserver(spinnerButton);	

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
		title.setFocusable(false);

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
		descr.setFocusable(false);

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
		titleInv.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB_SPINNER, RIGHTB));

		inventPanel.setLayout(new BoxLayout(inventPanel, BoxLayout.Y_AXIS));
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
		inventPanel.add(titleInv);
		inventPanel.setBackground(sfondo);
		
		invTAfood.setEditable(false);
		invTAfood.setFocusable(false);
		invTAseed.setEditable(false);
		invTAseed.setFocusable(false);
		
		jspF.setBackground(sfondo);
		jspF.setBorder(BorderFactory.createEmptyBorder(0, LEFTB_SPINNER,BOTTOMB_SPINNER, RIGHTB_SPINNER));
		
		jspS.setBackground(sfondo);
		jspS.setBorder(BorderFactory.createEmptyBorder(0, LEFTB_SPINNER, BOTTOMB_SPINNER, RIGHTB_SPINNER));
		
		scrollPanel.add(jspF);
		scrollPanel.add(jspS);
		inventPanel.add(scrollPanel);
		inventPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		inventoryUpdate();
		/* Fine Pannello Inventario */

		
		/* Pannello buy */
		JPanel buyPanel = new JPanel();
		buyPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		Font buyFont=new Font("Arial", Font.BOLD, 20);
		String[] itemString = new String[countSeed];
		int i = 0;
		for (SeedType seed : g.getShop().getSeedItemList()) {
			itemString[i++] = seed.getName();
		}

		buyPanel.setBackground(sfondo);
		buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.PAGE_AXIS));
		buyPanel.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB, RIGHTB));

		JComboBox<Object> selectSeed = new JComboBox<>(itemString);
		selectSeed.setFont(buyFont);
		
		int startValue = 0, minValue = 0, maxValue = 1000, step = 1;
		JSpinner quantity = new JSpinner(new SpinnerNumberModel(startValue, minValue, maxValue, step));
		quantity.setBorder(BorderFactory.createEmptyBorder(TOPB/4, LEFTB/4, BOTTOMB/4, RIGHTB/4));
		quantity.setFont(buyFont);
		quantity.setBackground(Color.WHITE);
		buyPanel.add(selectSeed);
		buyPanel.add(quantity);

		JTextField prezzoTot = new JTextField(
				"TOT: " + (SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
						* ((Integer) quantity.getValue()));
		prezzoTot.setEditable(false);
		prezzoTot.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		buyPanel.add(prezzoTot);
		buyPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		JButton buy = new JButton("COMPRA");
		buy.setFont(buyFont);
		buy.setBorder(BorderFactory.createEmptyBorder(0, LEFTB, 0, RIGHTB));
		buyPanel.add(buy);

		quantity.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				prezzoTot.setText("TOT: "
						+ Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
								* ((Integer) quantity.getValue())));
				obsShop.notifyObserver(true);
			}
		});
		selectSeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prezzoTot.setText("TOT: "
						+ Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
								* ((Integer) quantity.getValue())));
				obsShop.notifyObserver(true);
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
				obsShop.notifyObserver(true);
			}
		});
		/* Fine Pannello buy */

		
		/* Pannello sell */
		JPanel sellPanel = new JPanel();
		sellPanel.setBorder(BorderFactory.createEmptyBorder(TOPB, LEFTB, BOTTOMB, RIGHTB));
		sellPanel.setBackground(sfondo);
		JButton sellAll = new JButton("SELL ALL YOUR ITEMS");
		sellAll.setFont(buyFont);
		sellAll.setBorder(BorderFactory.createEmptyBorder((int) (TOPB*1.1), LEFTB, BOTTOMB, RIGHTB));

		sellPanel.add(sellAll, BorderLayout.CENTER);

		sellAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double money = g.sellAll();
				JOptionPane.showMessageDialog(sellPanel, "You earned " + money);
				obsShop.notifyObserver(true);
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

		for (var f : game.getPlayer().getInventory().getFoods().entrySet()) {
			invTAfood.append("[Food Item]-> " + f.getKey() + "\t [Quantity]-> " + f.getValue() + "\n");
		}
		for (var f : game.getPlayer().getInventory().getSeeds().entrySet()) {
			invTAseed.append("[Seed Item]-> " + f.getKey() + "\t [Quantity]-> " + f.getValue() + "\n");
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		inventoryUpdate();
	}	
	
	public class ObserverShop implements Observer<Boolean>{
		@Override
		public void update(Boolean notify) {
			inventoryUpdate();
			repaint();
			revalidate();
		}
	}
}