package input;

import java.awt.Rectangle;

import render.Renderinghandler;
import ui.UIhandler;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;

public class Inputhandler implements InputProcessor{
    public static Pointer mouse = new Pointer(0, 0, 0, 0);
    public static Pointer staticMouse = new Pointer(0, 0, 0, 0);
    
    
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		pointerAltered(screenX, screenY);
		pointersDown();
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		pointerAltered(screenX, screenY);
		pointersUp();
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		pointerAltered(screenX, screenY);
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		pointerAltered(screenX, screenY);
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
    
    public void updateMouse(int screenX, int screenY){
        mouse = new Pointer((int) ((screenX + Renderinghandler.getScreenX()) * Renderinghandler.getZoomScale()), (int) ((-screenY - Renderinghandler.getScreenY()) * Renderinghandler.getZoomScale()), 1, 1);
        staticMouse = new Pointer((int) (screenX + Renderinghandler.getScreenX()), (int) (-screenY - Renderinghandler.getScreenY()), 1, 1);
    }
    
    public void pointerAltered(int screenX, int screenY){
		updateMouse(screenX, screenY);
    }
    
    public void pointersDown(){
    	mouse.down = true;
    	staticMouse.down = true;
    }
    
    public void pointersUp(){
    	mouse.down = false;
    	staticMouse.down = false;
    }

}
