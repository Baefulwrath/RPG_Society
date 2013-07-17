package rendering.renderers;

import ouroboros.ProgramState;
import assets.AssetHandler;

import com.badlogic.gdx.graphics.Color;

import rendering.Renderer;
import rendering.RenderingHandler;
import ui.UIHandler;

public class REND_DEFAULT extends Renderer{

	public REND_DEFAULT(ProgramState s){
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
		drawImage(RenderingHandler.testImg, 0, 0, RenderingHandler.w, RenderingHandler.h, 0, true, Color.WHITE, 1.0f, true);
		drawMessages(UIHandler.messages, RenderingHandler.getScreenX(), -RenderingHandler.getScreenY(), false);
		drawMenu(UIHandler.getMenu());
		drawHuds(UIHandler.getHuds());
	}
	
}
