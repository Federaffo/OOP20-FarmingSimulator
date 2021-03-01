package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import engine.Game;

public class WindowManager extends JFrame{
    private static final long serialVersionUID = -5704310736291818589L;

    //private JFrame frame = new JFrame();
    private JLayeredPane lpanel;
    private JPanel mainPanel;
    private JPanel shopPanel;
    private Game game;
    
    
    public WindowManager(Game game) {
        this.game = game;
        setSize(1200, 700);
        setTitle("Farming Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        createPanel();
    }
    
    public void createPanel() {
        lpanel =  new JLayeredPane();
        
        shopPanel = new ShopDrawer(game);
        shopPanel.setBackground(Color.red);
        shopPanel.setBounds(0, 0, 600, 400);
        
        mainPanel = new MainScreenDrawer(game);
        mainPanel.setBackground(Color.blue);
        mainPanel.setBounds(0, 0, 600, 400);
        
        lpanel.setBounds(0, 0, 600, 400);
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
    
   
}
