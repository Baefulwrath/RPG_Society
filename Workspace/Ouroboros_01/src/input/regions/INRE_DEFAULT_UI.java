package input.regions;

import input.InputRegion;
import input.InputRegionType;

public class INRE_DEFAULT_UI extends InputRegion{

	public INRE_DEFAULT_UI() {
		super(InputRegionType.UI);
	}

	@Override
	public void keyDown(int keycode) {
	}

	@Override
	public void keyUp(int keycode) {
	}

	@Override
	public void keyTyped(char character) {
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		touchDownUI();
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
		touchUpUI();
	}

	@Override
	public void touchDragged(int screenX, int screenY, int pointer) {
	}

	@Override
	public void mouseMoved(int screenX, int screenY) {
	}

	@Override
	public void scrolled(int amount) {
	}

}
