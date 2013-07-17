package input;

import ouroboros.OS;
import ouroboros.ProgramState;
import ui.UIHandler;

import static input.InputHandler.*;

public abstract class InputManager {
	
	public ProgramState state = ProgramState.DEFAULT;
	public InputRegion pausedInput;
	public InputRegion UiInput;
	public InputRegion restInput;
	
	public InputManager(ProgramState s, InputRegion paused, InputRegion Ui, InputRegion rest){
		state = s;
		pausedInput = paused;
		UiInput = Ui;
		restInput = rest;
	}
    
	public void keyDown(int keycode){
		getRegion().keyDown(keycode);
	}

	public void keyUp(int keycode){
		getRegion().keyUp(keycode);
	}

	public void keyTyped(char character){
		getRegion().keyTyped(character);
	}

	public void touchDown(int screenX, int screenY, int pointer, int button){
		getRegion().touchDown(screenX, screenY, pointer, button);
	}

	public void touchUp(int screenX, int screenY, int pointer, int button){
		getRegion().touchUp(screenX, screenY, pointer, button);
	}

	public void touchDragged(int screenX, int screenY, int pointer){
		getRegion().touchDragged(screenX, screenY, pointer);
	}

	public void mouseMoved(int screenX, int screenY){
		getRegion().mouseMoved(screenX, screenY);
	}

	public void scrolled(int amount){
		getRegion().scrolled(amount);
	}
	
	protected InputRegion getRegion(){
		if(OS.paused){
			return pausedInput;
		}else if(UIHandler.intersects(staticMouse)){
			return UiInput;
		}else{
			return restInput;
		}
	}
}
