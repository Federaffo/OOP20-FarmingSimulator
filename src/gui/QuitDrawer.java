package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import control.Game;

public class QuitDrawer extends GameDrawer {

    private static final int FONT_DIM = 30;

    public QuitDrawer(final Game game, final Dimension screenSize) {
        super(game, screenSize);
        generateQuitPanel(game, screenSize);

    }

    private void generateQuitPanel(final Game g, final Dimension screenSize) {
        final int rightb = (int) -(screenSize.width * 0.90);

        JButton quit = new JButton();
        Font f = new Font("Arial", Font.BOLD, FONT_DIM);
        quit.setText("X");
        quit.setBackground(Color.red);
        quit.setFont(f);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                g.play();
            }
        });
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightb));

        add(quit);

        setOpaque(false);
    }
}
