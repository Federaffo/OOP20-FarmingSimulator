package engine;

import java.awt.event.KeyEvent;

public class Game {

	public int x = 0;
	public int y = 0;
	
	private boolean w= false;
	private boolean a= false;
	private boolean s= false;
	private boolean d= false;


	public void loop() {
		if(w) {
			y-=5;
		}
		if(a) {
			x-=5;
		}
		if(s) {
			y+=5;
		}
		if(d) {
			x+=5;
		}
	}

	public void update() {

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 87) {
			w=true;
		}
		if(e.getKeyCode() == 65) {
			a=true;
		}
		if(e.getKeyCode() == 83) {
			s=true;
		}
		if(e.getKeyCode() == 68) {
			d=true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 87) {
			w=false;
		}
		if(e.getKeyCode() == 65) {
			a=false;
		}
		if(e.getKeyCode() == 83) {
			s=false;
		}
		if(e.getKeyCode() == 68) {
			d=false;
		}
	}

}
