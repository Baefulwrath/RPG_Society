package ui;

public abstract class Menu extends UIComponent{
	public Menu(String title, String id, int tx, int ty, int x, int y, int w, int h){
		super(title, id, x, y, w, h);
		UICT = UICType.MENU;
		TITLEX = tx;
		TITLEY = ty;
	}
	
	protected int TITLEX = 0;
	protected int TITLEY = 0;
	
	public int getTitleX(){
		return TITLEX + BOX.x;
	}
	
	public int getTitleY(){
		return TITLEY + BOX.y;
	}
	
}
