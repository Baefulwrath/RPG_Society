package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(Main.state == State.READY){
			Main.work();
		}else if(Main.state == State.FINISHED){
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
