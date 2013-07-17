package ui;

import input.Pointer;

import java.awt.Rectangle;

public abstract class UIObject {
	public Rectangle BOX = new Rectangle();
	public float OPACITY = 1.0f;
	public boolean RENDERTITLE = false;
	public String TITLE = "UNTITLED";
	public String SCRIPT = "";
	
	
	public UIObject(String title, String script, int x, int y, int w, int h){
		TITLE = title;
		SCRIPT = script;
		BOX = new Rectangle(x, y, w, h);
	}
	
	public UIObject(String title, String script, Rectangle r){
		TITLE = title;
		SCRIPT = script;
		BOX = r;
	}
	
	public void update(Pointer p){
		clear();
		content();
		typeUpdate(p);
	}
	
	public abstract void typeUpdate(Pointer p);

	protected abstract void content();

	private void clear() {
	}

	protected abstract void setup();
	
	public abstract void touchDown();

	public abstract void touchUp();

	public abstract boolean intersects(Rectangle r);
}
