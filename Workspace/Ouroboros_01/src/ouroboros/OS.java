package ouroboros;

import input.InputHandler;

import java.util.HashMap;

import ouroboros.modes.*;

import rendering.RenderingHandler;
import scripting.ScriptHandler;
import ui.UIHandler;

import assets.AssetHandler;

import com.badlogic.gdx.ApplicationListener;
import static com.badlogic.gdx.Gdx.*;

import static ouroboros.ProgramState.*;

public class OS implements ApplicationListener {
	public static ProgramState state = DEFAULT;
	public static HashMap<ProgramState, ProgramMode> modes = new HashMap<ProgramState, ProgramMode>();
	public static boolean paused = false;
	public static boolean exitProgram = false;
	public static InputHandler inputhandler = new InputHandler();
	
	public OS(ProgramState startupState, String defaultMenu){
    	state = startupState;
    	UIHandler.setDefaultMenu(defaultMenu);
    }
	
	@Override
	public void create() {
		RenderingHandler.setup();
		AssetHandler.setup();
		setupModes();
		inputhandler.setup();
		input.setInputProcessor(inputhandler);
		UIHandler.setup();
		ScriptHandler.setup();
	}
	
	public void setupModes(){
		modes.put(DEFAULT, new MODE_DEFAULT(DEFAULT));
		modes.put(MENU, new MODE_MENU(MENU));
		modes.put(GAME, new MODE_GAME(GAME));
		modes.put(EDITOR, new MODE_EDITOR(EDITOR));
	}

	@Override
	public void dispose() {
		RenderingHandler.dispose();
		AssetHandler.dispose();
		UIHandler.dispose();
		ScriptHandler.dispose();
	}

//A better name for this method would be "update", you can blame LibGdx for this.
	@Override
	public void render(){
		if(exitProgram){
			exit();
		}else{
			try{
				Thread.sleep(1);
				updateSpecific();
				updateGeneral();
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}
	
	public void updateSpecific(){
		switch(state){
			case DEFAULT:
				modes.get(DEFAULT).update();
				break;
			case MENU:
				modes.get(MENU).update();
				break;
			case EDITOR:
				modes.get(EDITOR).update();
				break;
			case GAME:
				modes.get(GAME).update();
				break;
		}
	}
	
	public void updateGeneral(){
		UIHandler.update(InputHandler.staticMouse);
		ScriptHandler.update();
		RenderingHandler.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public static void changeState(ProgramState s){
		state = s;
	}
	
	public static void changeState(String s){
		state = ProgramState.parseState(s);
	}
	
	public void exit(){
		dispose();
		System.exit(0);
	}
}
