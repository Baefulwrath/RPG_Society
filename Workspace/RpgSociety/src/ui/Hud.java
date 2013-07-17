package ui;


public abstract class Hud extends UIComponent{
	public Hud(String title, String id, int x, int y, int w, int h){
		super(title, id, x, y, w, h);
		UICT = UICType.HUD;
	}
	
}
