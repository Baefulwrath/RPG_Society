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
		addTextArea("This text is supposed to be above the 'back'-button.", 1000, 20, AssetHandler.basicLabelStyle);
		addButton("Back", "setMenu_main", 100, 30, AssetHandler.basicButtonStyle);
	}
	
}
