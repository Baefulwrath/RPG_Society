package input;

import input.managers.*;

import java.util.HashMap;

import ouroboros.OS;
import ouroboros.ProgramNode;
import ouroboros.ProgramState;

import rendering.RenderingHandler;

import com.badlogic.gdx.InputProcessor;

public class InputHandler extends ProgramNode implements InputProcessor{
    public static Pointer mouse = new Pointer(0, 0, 0, 0);
    public static Pointer staticMouse = new Pointer(0, 0, 0, 0);
    public static HashMap<ProgramState, InputManager> managers = new HashMap<ProgramState, InputManager>();

    public static void setup(){
    	managers.put(ProgramState.DEFAULT, new INMA_DEFAULT(ProgramState.DEFAULT));
    	managers.put(ProgramState.MENU, new INMA_MENU(ProgramState.MENU));
    	managers.put(ProgramState.GAME, new INMA_GAME(ProgramState.GAME));
    	managers.put(ProgramState.EDITOR, new INMA_EDITOR(ProgramState.EDITOR));
    }
    
    public static InputManager getManager(){
    	return getManager(OS.state);
    }
    
    public static InputManager getManager(ProgramState state){
    	return managers.get(state);
    }
    
	@Override
	public boolean keyDown(int keycode) {
		getManager().keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		getManager().keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		getManager().keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		pointerAltered(screenX, screenY);
		pointersDown();
		getManager().touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		pointerAltered(screenX, screenY);
		pointersUp();
		getManager().touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		pointerAltered(screenX, screenY);
		getManager().touchDragged(screenX, screenY, pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		pointerAltered(screenX, screenY);
		getManager().mouseMoved(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		getManager().scrolled(amount);
		return false;
	}
    
    public void updateMouse(int screenX, int screenY){
        mouse = new Pointer((int) ((screenX + RenderingHandler.getScreenX()) * RenderingHandler.getZoomScale()), (int) ((-screenY - RenderingHandler.getScreenY()) * RenderingHandler.getZoomScale()), 1, 1);
        staticMouse = new Pointer((int) (screenX + RenderingHandler.getScreenX()), (int) (-screenY - RenderingHandler.getScreenY()), 1, 1);
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
