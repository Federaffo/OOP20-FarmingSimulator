package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import engine.Game;
import engine.GameState;

public class WindowManager extends JFrame {
	private static final long serialVersionUID = -5704310736291818589L;

	// private JFrame frame = new JFrame();
	private JLayeredPane lpanel;
	private GameDrawer mainPanel;
	private GameDrawer shopPanel;
	private GameDrawer infoPanel;
	private Game game;
	private Dimension windowSize;

	public WindowManager(Game game) {
		Dimension screenSize = getToolkit().getScreenSize();
		windowSize = new Dimension((int) (screenSize.width * 0.8), (int) ((screenSize.width * 0.8) * (0.5625)));
		this.game = game;
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		setBounds(0, 0, windowSize.width + 20, windowSize.height + 50);
		setTitle("Farming Simulator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		createPanel();
		pack();
	}

	public void createPanel() {
		lpanel = new JLayeredPane();

		shopPanel = new ShopDrawer(game, windowSize);
		shopPanel.setBackground(Color.red);
		shopPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		shopPanel.setPreferredSize(windowSize);

		mainPanel = new MainScreenDrawer(game, windowSize);
		mainPanel.setBackground(Color.green);
		mainPanel.setSize(windowSize);
		mainPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		mainPanel.setPreferredSize(windowSize);

		infoPanel = new InfoDrawer(game, windowSize);
		infoPanel.setBackground(Color.green);
		infoPanel.setSize(windowSize);
		infoPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		infoPanel.setPreferredSize(windowSize);

		lpanel.setBounds(0, 0, windowSize.width, windowSize.height);
		lpanel.add(mainPanel, 0, 0);
		lpanel.add(shopPanel, 1, 0);
		lpanel.add(infoPanel, 2, 0);
		lpanel.setPreferredSize(windowSize);
		lpanel.requestFocus();
		// setContentPane(mainPanel);
		add(lpanel);
//		add(shopPanel);
//		add(mainPanel);
		showMainScreen();
	}

	public void addKeyListener(KeyListener k) {
		System.out.println("listener added");
		this.mainPanel.addKeyListener(k);
	}

	private void disableAllPanel() {
		for (var jPan : lpanel.getComponents()) {
			jPan.setVisible(false);
		}
	}

	public void showShop() {
		disableAllPanel();
		shopPanel.setVisible(true);
		shopPanel.requestFocus();
	}

	public void showInfo() {
		disableAllPanel();
		infoPanel.setVisible(true);
		infoPanel.requestFocus();
	}

	public void showMainScreen() {
		disableAllPanel();
		mainPanel.setVisible(true);
		mainPanel.requestFocus();
	}

	public void setWindow(GameState gameState) {
		if (gameState == GameState.SHOP) {
			showShop();
		} else if (gameState == GameState.PLAY) {
			showMainScreen();
		} else if (gameState == GameState.INFO) {
			showInfo();
		}
	}

}