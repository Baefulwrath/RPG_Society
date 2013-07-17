package rendering.renderers;

import ouroboros.ProgramState;
import assets.AssetHandler;

import com.badlogic.gdx.graphics.Color;

import rendering.Renderer;
import rendering.RenderingHandler;
import ui.UIHandler;

public class REND_MENU extends Renderer{

	public REND_MENU(ProgramState s){
		super(s);
	}

	@Override
	public void loadSpecificResources() {
	}

	@Override
	public void mobileRender() {
	}

	@Override
	public void staticRender() {
		drawMessages(UIHandler.messages, RenderingHandler.getScreenX(), -RenderingHandler.getScreenY(), false);
		drawMenu(UIHandler.getMenu());
	}
	
}
