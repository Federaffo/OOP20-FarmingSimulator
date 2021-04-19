package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Game;
import engine.GameSaver;
import engine.GameState;

public class WindowManager extends JFrame {
	private static final double SCREEN_RESIZER = 0.8;
	private static final double ASPECT_RATIO = 0.5625;

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
		windowSize = new Dimension((int) (screenSize.width * SCREEN_RESIZER), (int) ((screenSize.width * SCREEN_RESIZER) * ASPECT_RATIO));
		this.game = game;

		setBounds(0, 0, windowSize.width, windowSize.height);
		setTitle("Farming Simulator");
		setLocationRelativeTo(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
				int result = JOptionPane.showConfirmDialog(null, "Would You Like to Save?", "Exit", dialogButton);
				if (result == JOptionPane.YES_OPTION) {
					new GameSaver().save(game);
					System.exit(0);
				}else if(result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
		});

		setResizable(false);
		setVisible(true);
		createPanel();
		pack();
	}

	public void createPanel() {
		lpanel = new JLayeredPane();
		double quitPanelBounds = 0.06;

		shopPanel = new ShopDrawer(game, windowSize);
//		shopPanel.setBackground(shopPanel.getBackground());
		shopPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		shopPanel.setPreferredSize(windowSize);

		mainPanel = new MainScreenDrawer(game, windowSize);
		mainPanel.setSize(windowSize);
		mainPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		mainPanel.setPreferredSize(windowSize);

		infoPanel = new InfoDrawer(game, windowSize);
		infoPanel.setSize(windowSize);
		infoPanel.setBounds(0, 0, windowSize.width, windowSize.height);
		infoPanel.setPreferredSize(windowSize);

		quitPanel = new QuitDrawer(game, windowSize);
		quitPanel.setSize(windowSize);
		quitPanel.setBounds(0, 0, windowSize.width, (int) (windowSize.height * quitPanelBounds));
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
		// disableAllPanel();
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