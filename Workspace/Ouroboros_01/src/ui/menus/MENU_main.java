package ui.menus;

import assets.AssetHandler;
import ui.Menu;

public class MENU_main extends Menu{

	public MENU_main() {
		super("Main Menu", "");
	}

	@Override
	protected void content() {
	}

	@Override
	protected void setup() {
		addButton("Continue", "setMenu_continue", 150, 30, AssetHandler.basicButtonStyle);
		addButton("New Game", "setMenu_newGame", 150, 30, AssetHandler.basicButtonStyle);
		addButton("Load Game", "setMenu_loadGame", 150, 30, AssetHandler.basicButtonStyle);
		addButton("Options", "setMenu_options", 150, 30, AssetHandler.basicButtonStyle);
		addButton("About", "setMenu_about", 150, 30, AssetHandler.basicButtonStyle);
		addButton("Exit", "setMenu_exit", 150, 30, AssetHandler.basicButtonStyle);
	}
	
}
