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
	private GameDrawer quitPanel;
	private Game game;
	private Dimension windowSize;

	public WindowManager(Game game) {
		Dimension screenSize = getToolkit().getScreenSize();
		windowSize = new Dimension((int) (screenSize.width * 0.8), (int) ((screenSize.width * 0.8) * (0.5625)));
		this.game = game;
		
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
		infoPanel.setBackground(Color.yellow);
		infoPanel.setSize(windowSize);
		infoPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		infoPanel.setPreferredSize(windowSize);
		
		quitPanel = new QuitDrawer(game, windowSize);
		quitPanel.setBackground(Color.black);
		quitPanel.setSize(windowSize);
		quitPanel.setBounds(0, 0, windowSize.width, (int)(windowSize.height*0.06));
		quitPanel.setPreferredSize(windowSize);

		lpanel.setBounds(0, 0, windowSize.width, windowSize.height);
		lpanel.add(mainPanel, 0, 0);
		lpanel.add(shopPanel, 1, 0);
		lpanel.add(infoPanel, 2, 0);
		lpanel.add(quitPanel, 3, 0);
		lpanel.setPreferredSize(windowSize);
		lpanel.requestFocus();

		add(lpanel);
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
		showQuitScreen();
	}

	public void showInfo() {
		//disableAllPanel();
		infoPanel.setVisible(true);
		infoPanel.requestFocus();
		showQuitScreen();
	}

	public void showMainScreen() {
		disableAllPanel();
		mainPanel.setVisible(true);
		mainPanel.requestFocus();
	}
	
	public void showQuitScreen() {
		//disableAllPanel();
		quitPanel.setVisible(true);
		quitPanel.requestFocus();
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