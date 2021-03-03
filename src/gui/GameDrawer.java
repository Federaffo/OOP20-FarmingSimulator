package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import engine.Game;
import engine.KeyNotifier;

public abstract class GameDrawer extends JPanel {
	private static final long serialVersionUID = -7700514648149727065L;

	private KeyNotifier notifier;
	protected Game game;

	public GameDrawer(Game game) {
		this.game = game;
		notifier = new KeyNotifier(game);
		addKeyListener(new MyKeyListener());
		addMouseWheelListener(new MyMouseListener());
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}

	private class MyMouseListener implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			notifier.mouseWheelMoved(e);
		}
	}

	private class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			notifier.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			notifier.keyReleased(e);
		}

	}
}
