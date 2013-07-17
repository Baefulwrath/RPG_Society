package ui.menus;

import assets.AssetHandler;
import ui.Menu;

public class MENU_about extends Menu{

	public MENU_about() {
		super("About", "");
	}

	@Override
	protected void content() {
	}

	@Override
	protected void setup() {
		addTextArea("RPG Society, it's an rpg so... you know. That's cool", 1000, 20, AssetHandler.basicLabelStyle);
		addButton("Back", "setMenu_main", 100, 30, AssetHandler.basicButtonStyle);
	}
	
}
