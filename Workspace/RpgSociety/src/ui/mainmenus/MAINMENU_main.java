package ui.mainmenus;

import render.Assethandler;
import ui.MainMenu;

public class MAINMENU_main extends MainMenu{

	public static String id = "mainmenu";
	
	public MAINMENU_main(){
		super("Main Menu", id);
	}
	
	@Override
	public void setup() {
		addMainButton("test button", "print_test", 20, -100, 200, 32, Assethandler.basicButtonStyle);
		addMainButton("test button 2", "print_test2", 20, -140, 200, 32, Assethandler.basicButtonStyle);
	}
	
}
