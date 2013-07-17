package ui.menus;

import assets.AssetHandler;
import ui.Menu;

public class MENU_exit extends Menu{

	public MENU_exit() {
		super(AssetHandler.getExitText(), "");
	}

	@Override
	protected void content() {
	}

	@Override
	protected void setup() {
		addButton(AssetHandler.getExitTitle(false), "setMenu_main", 150, 30, AssetHandler.basicButtonStyle);
		addButton(AssetHandler.getExitTitle(true), "exit_", 150, 30, AssetHandler.basicButtonStyle);
	}
	
}
