package main;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	
	public static Pointer mouse = new Pointer(0, 0, 1, 1);

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
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
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1){
			Main.painting = true;
		}else if(button == MouseEvent.BUTTON3){
			ScriptHandler.handleScript("changeBrushType_");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Main.painting = false;
		for(int i = 0; i < Main.buttons.size(); i++){
			Main.buttons.get(i).release();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
			case KeyEvent.VK_UP:
				Main.world.down = true;
				break;
			case KeyEvent.VK_DOWN:
				Main.world.up = true;
				break;
			case KeyEvent.VK_LEFT:
				Main.world.right = true;
				break;
			case KeyEvent.VK_RIGHT:
				Main.world.left = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
			case KeyEvent.VK_UP:
				Main.world.down = false;
				break;
			case KeyEvent.VK_DOWN:
				Main.world.up = false;
				break;
			case KeyEvent.VK_LEFT:
				Main.world.right = false;
				break;
			case KeyEvent.VK_RIGHT:
				Main.world.left = false;
				break;
			case KeyEvent.VK_PLUS:
				ScriptHandler.handleScript("brushSize_+");
				break;
			case KeyEvent.VK_MINUS:
				ScriptHandler.handleScript("brushSize_-");
				break;
			case KeyEvent.VK_B:
				ScriptHandler.handleScript("switchBrushBlock_");
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void update(){
		try {
			mouse.position(Main.scr.getMousePosition());
			Main.brush.position(Main.scr.getMousePosition(), true);
		} catch (Exception ex) {}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		if(rot > 0){
			//Down
			ScriptHandler.handleScript("brushSize_-");
		}else{
			//Up
			ScriptHandler.handleScript("brushSize_+");
		}
	}
}
