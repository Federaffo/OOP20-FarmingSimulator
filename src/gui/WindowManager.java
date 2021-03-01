package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class WindowManager extends JFrame{
    private static final long serialVersionUID = -5704310736291818589L;

    //private JFrame frame = new JFrame();
    private JPanel lpanel;
    private JPanel panel;
    private JPanel shopPanel;
    
    public WindowManager() {
        initUI();
        //panel = new GamePanel();
    }
    
    private void initUI() {
        setSize(500, 800);
        setTitle("Farming Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
//    public void createWindow() {
//        this.frame.setTitle("Farming Simulator");
//        this.frame.setContentPane(panel);
//        this.frame.pack();
//        this.frame.setVisible(true);
//    }
//    
    

    public void addKeyListener(KeyListener k) {
        System.out.println("listener added");
        this.panel.addKeyListener(k);
    }
    
//    private class GamePanel extends JPanel{
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            var g2d = (Graphics2D) g;
//            g2d.setColor(Color.blue);
//            g2d.drawRect(10, 10, 100, 100);
//            super.repaint();
//        }
//    }
    
    public void switchPanel() {
        if(panel.isVisible()) {
            shopPanel.setVisible(true);
            panel.setVisible(false);
        }else {
            panel.setVisible(true);
            shopPanel.setVisible(false);
        }
        
        
    }
    
    public void addShopPanel(JPanel pan) {
        this.shopPanel = pan;
        add(pan);
        
    }

    public void addPanel(JLayeredPane lpane) {
        this.panel = (JPanel) lpane.getComponent(0);
        this.shopPanel = (JPanel) lpane.getComponent(1);
        add(lpane, BorderLayout.CENTER);
    }
    
   
}
