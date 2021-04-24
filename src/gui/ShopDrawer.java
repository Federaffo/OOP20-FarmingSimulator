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

import control.Game;
import item.SeedType;
import utils.Observer;

public class ShopDrawer extends GameDrawer {
    private static final long serialVersionUID = 5108963132975063659L;
    private final int hgap;
    private final int vgap;
    private final int leftb;
    private final int rightb;
    private final int topb;
    private final int bottomb;
    private final int leftbScroll;
    private final int rightbScroll;
    private final int bottombScroll;
    private Game game;
    private JTextArea invTAfood = new JTextArea();
    private JTextArea invTAseed = new JTextArea();
    private static final Color SFONDO = new Color(17, 96, 98);
    private static final Font BUYFONT = new Font("Arial", Font.BOLD, 20);
    private final int countSeed;
    private final ObservableShopGUI<Boolean> obsShop = new ObservableShopGUI<>();

    public ShopDrawer(final Game g, final Dimension screenSize) {
        super(g, screenSize);
        this.game = g;
        this.hgap = (int) (screenSize.width * 0.005);
        this.vgap = (int) (screenSize.height * 0.01);
        this.leftb = (int) (screenSize.width * 0.08);
        this.rightb = (int) (screenSize.width * 0.08);
        this.topb = (int) (screenSize.height * 0.08);
        this.bottomb = (int) (screenSize.height * 0.08);
        this.leftbScroll = leftb / 4;
        this.rightbScroll = rightb / 4;
        this.bottombScroll = bottomb / 2;

        this.countSeed = g.getShop().getSeedItemList().size();

        final Observer<Boolean> sellButton = new ObserverShop<>();
        final Observer<Boolean> cmbox = new ObserverShop<>();
        final Observer<Boolean> buyButton = new ObserverShop<>();
        final Observer<Boolean> spinnerButton = new ObserverShop<>();
        obsShop.addObserver(sellButton);
        obsShop.addObserver(cmbox);
        obsShop.addObserver(buyButton);
        obsShop.addObserver(spinnerButton);

        setLayout(new GridLayout(2, 3, hgap, vgap));

        titlePanel();
        inventoryPanel();
        buyPanel();
        sellPanel();

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

    public void titlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(SFONDO);

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

        title.setBorder(BorderFactory.createEmptyBorder(topb, leftb, 0, rightb));
        title.setBackground(titlePanel.getBackground());
        descr.setBorder(BorderFactory.createEmptyBorder(0, leftb, bottomb, rightb));
        descr.setBackground(titlePanel.getBackground());

        titlePanel.add(title);
        titlePanel.add(descr);

        add(titlePanel);
    }

    public void inventoryPanel() {
        JPanel inventPanel = new JPanel();
        JPanel scrollPanel = new JPanel();
        Font font3 = new Font("Garamond", Font.BOLD, 40);
        JLabel titleInv = new JLabel("Inventario");
        JScrollPane jspF = new JScrollPane(invTAfood);
        JScrollPane jspS = new JScrollPane(invTAseed);

        titleInv.setForeground(Color.white);
        titleInv.setFont(font3);
        titleInv.setBorder(BorderFactory.createEmptyBorder(topb, leftb, bottombScroll, rightb));

        inventPanel.setLayout(new BoxLayout(inventPanel, BoxLayout.Y_AXIS));
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        inventPanel.add(titleInv);
        inventPanel.setBackground(SFONDO);

        invTAfood.setEditable(false);
        invTAfood.setFocusable(false);
        invTAseed.setEditable(false);
        invTAseed.setFocusable(false);

        jspF.setBackground(SFONDO);
        jspF.setBorder(BorderFactory.createEmptyBorder(0, leftbScroll, bottombScroll, rightbScroll));

        jspS.setBackground(SFONDO);
        jspS.setBorder(BorderFactory.createEmptyBorder(0, leftbScroll, bottombScroll, rightbScroll));

        scrollPanel.add(jspF);
        scrollPanel.add(jspS);
        inventPanel.add(scrollPanel);
        inventPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        inventoryUpdate();

        add(inventPanel);
    }

    public void buyPanel() {
        final int hBuy = screenSize.height;
        final int wBuy = screenSize.width;
        JPanel buyPanel = new JPanel();
        buyPanel.add(Box.createRigidArea(new Dimension(0, hgap)));

        String[] itemString = new String[countSeed];
        int i = 0;
        for (SeedType seed : game.getShop().getSeedItemList()) {
            itemString[i++] = seed.getName();
        }

        buyPanel.setBackground(SFONDO);
        buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.PAGE_AXIS));
        buyPanel.setBorder(BorderFactory.createEmptyBorder(topb, leftb, bottomb, rightb));

        JComboBox<Object> selectSeed = new JComboBox<>(itemString);
        selectSeed.setFont(BUYFONT);

        int startValue = 0, minValue = 0, maxValue = 1000, step = 1;
        JSpinner quantity = new JSpinner(new SpinnerNumberModel(startValue, minValue, maxValue, step));
        // quantity.setBorder(BorderFactory.createEmptyBorder(TOPB/4, LEFTB/4,
        // BOTTOMB/4, RIGHTB/4));
        quantity.setFont(BUYFONT);
        quantity.setBackground(Color.WHITE);
        buyPanel.add(selectSeed);
        buyPanel.add(quantity);

        JTextField prezzoTot = new JTextField(
                "TOT: " + (SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
                        * ((Integer) quantity.getValue()));
        prezzoTot.setEditable(false);
        prezzoTot.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        prezzoTot.setMaximumSize(new Dimension(wBuy, hBuy));
        buyPanel.add(prezzoTot);
        buyPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        JButton buy = new JButton("COMPRA");
        buy.setFont(BUYFONT);
        buy.setBorder(BorderFactory.createEmptyBorder(0, leftb, 0, rightb));
        buyPanel.add(buy);

        quantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                // TODO Auto-generated method stub
                prezzoTot.setText("TOT: "
                        + Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
                                * ((Integer) quantity.getValue())));
                obsShop.notifyObserver(true);
            }
        });
        selectSeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                prezzoTot.setText("TOT: "
                        + Double.toString((SeedType.getSeedType(selectSeed.getSelectedItem().toString()).getPrice())
                                * ((Integer) quantity.getValue())));
                obsShop.notifyObserver(true);
            }
        });
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (game.buy(SeedType.getSeedType(selectSeed.getSelectedItem().toString()),
                        (Integer) quantity.getValue())) {
                    JOptionPane.showMessageDialog(buyPanel, "Nice! Purchase made!");
                } else {
                    JOptionPane.showMessageDialog(buyPanel, "You haven't got enough  money!");
                }
                obsShop.notifyObserver(true);
            }
        });

        add(buyPanel);
    }

    public void sellPanel() {
        JPanel sellPanel = new JPanel();
        sellPanel.setBorder(BorderFactory.createEmptyBorder(topb, leftb, bottomb, rightb));
        sellPanel.setBackground(SFONDO);
        JButton sellAll = new JButton("SELL ALL YOUR ITEMS");
        sellAll.setFont(BUYFONT);
        sellAll.setBorder(BorderFactory.createEmptyBorder((int) (topb * 1.1), leftb, bottomb, rightb));

        sellPanel.add(sellAll, BorderLayout.CENTER);

        sellAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                double money = game.sellAll();
                JOptionPane.showMessageDialog(sellPanel, "You earned " + money);
                obsShop.notifyObserver(true);
            }
        });

        add(sellPanel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        inventoryUpdate();
    }

    public class ObserverShop<Boolean> implements Observer<Boolean> {
        /**
         * {@inheritDoc}
         */
        @Override
        public void update(final Boolean notify) {
            inventoryUpdate();
            repaint();
            revalidate();
        }
    }
}