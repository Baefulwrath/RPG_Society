package input;

import ui.UIHandler;

public abstract class InputRegion {
	
	public InputRegionType type = InputRegionType.PAUSED;
	
	public InputRegion(InputRegionType t){
		type = t;
	}
	
	public abstract void keyDown(int keycode);

	public abstract void keyUp(int keycode);

	public abstract void keyTyped(char character);

	public abstract void touchDown(int screenX, int screenY, int pointer, int button);

	public abstract void touchUp(int screenX, int screenY, int pointer, int button);

	public abstract void touchDragged(int screenX, int screenY, int pointer);

	public abstract void mouseMoved(int screenX, int screenY);

	public abstract void scrolled(int amount);
	
	public void touchDownUI(){
		UIHandler.touchDown();
	}
	
	public void touchUpUI(){
		UIHandler.touchUp();
	}
}
