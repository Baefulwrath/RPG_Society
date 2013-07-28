package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InputHandler implements KeyListener, MouseListener, MouseWheelListener{
	
	@Override
	public void keyPressed(KeyEvent e) {
		int KC = e.getKeyCode();
		switch(KC){
			case KeyEvent.VK_LEFT:
				Main.world.move(Main.chunkSize / 2, 0);
				break;
			case KeyEvent.VK_RIGHT:
				Main.world.move(-Main.chunkSize / 2, 0);
				break;
			case KeyEvent.VK_UP:
				Main.world.move(0, Main.chunkSize / 2);
				break;
			case KeyEvent.VK_DOWN:
				Main.world.move(0, -Main.chunkSize / 2);
				break;
			case KeyEvent.VK_PLUS:
				Main.mouse.change(1);
				break;
			case KeyEvent.VK_MINUS:
				Main.mouse.change(-1);
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_PAGE_UP:
				Main.chunkUp();
				break;
			case KeyEvent.VK_PAGE_DOWN:
				Main.chunkDown();
				break;
			case KeyEvent.VK_G:
				if(Main.showGrid){
					Main.showGrid = false;
				}else{
					Main.showGrid = true;
				}
				break;
			case KeyEvent.VK_I:
				if(Main.showTileInfo){
					Main.showTileInfo = false;
				}else{
					Main.showTileInfo = true;
				}
				break;
			case KeyEvent.VK_S:
				Main.saveMap();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int B = e.getButton();
		switch(B){
			case MouseEvent.BUTTON1:
				Main.painting = true;
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int B = e.getButton();
		switch(B){
			case MouseEvent.BUTTON1:
				Main.painting = false;
				break;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		if(rot < 0){
			Main.mouse.sizeUp();
		}else{
			Main.mouse.sizeDown();
		}
	}

}
