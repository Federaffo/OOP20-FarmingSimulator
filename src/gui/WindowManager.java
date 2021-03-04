package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import engine.Game;

public class WindowManager extends JFrame{
    private static final long serialVersionUID = -5704310736291818589L;

    //private JFrame frame = new JFrame();
    private JLayeredPane lpanel;
    private GameDrawer mainPanel;
    private GameDrawer shopPanel;
    private Game game;
    private Dimension screenSize;
    
    public WindowManager(Game game) {
    	screenSize = getToolkit().getScreenSize();
    	
        this.game = game;
        //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setBounds(0, 0, (int) (screenSize.width*0.8),  (int) (screenSize.height*0.8));
        setTitle("Farming Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        createPanel();
    }
    
    public void createPanel() {
        lpanel =  new JLayeredPane();
        
        shopPanel = new ShopDrawer(game,screenSize);
        shopPanel.setBackground(Color.red);
        shopPanel.setBounds(0, 0,  (int) (screenSize.width*0.50),  (int) (screenSize.height*0.50));
        
        mainPanel = new MainScreenDrawer(game,screenSize);
        mainPanel.setBackground(Color.green);
        mainPanel.setBounds(0, 0, (int) (screenSize.width*0.8), (int) (screenSize.height*0.8));
        
        lpanel.setBounds(0, 0,  (int) (screenSize.width*0.8),  (int) (screenSize.height*0.8));
        lpanel.add(mainPanel, 0, 0);
        lpanel.add(shopPanel, 1, 0);
        
        add(lpanel);
    }
    
    public void addKeyListener(KeyListener k) {
        System.out.println("listener added");
        this.mainPanel.addKeyListener(k);
    }
    
    public void switchPanel() {
        if(mainPanel.isVisible()) {
            shopPanel.setVisible(true);
            shopPanel.requestFocus();
            mainPanel.setVisible(false);
        }else {
            mainPanel.setVisible(true);
            mainPanel.requestFocus();
            shopPanel.setVisible(false);
        }
        
        
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
	
	public void showMainScreen() {
		disableAllPanel();
		mainPanel.setVisible(true);
		mainPanel.requestFocus();
	}
    
   
}