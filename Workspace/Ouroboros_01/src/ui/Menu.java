package ui;

import input.Pointer;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import assets.ButtonStyle;

import rendering.RenderingHandler;

public abstract class Menu extends UIObject{
	
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<TextArea> textAreas = new ArrayList<TextArea>();
	public static int itemInterval = 10;

	public Menu(String title, String script) {
		super(title, script, (int) RenderingHandler.getScreenX() + 200, (int) -RenderingHandler.getScreenY() - 200, 100, 100);
		RENDERTITLE = true;
		setup();
	}
	
	public Menu(Menu m) {
		super(m.TITLE, m.SCRIPT, (int) RenderingHandler.getScreenX() + 200, (int) -RenderingHandler.getScreenY() - 200, 100, 100);
		mirror(m);
	}
	
	public void mirror(Menu m) {
		for(int i = 0; i < m.buttons.size(); i++){
			addButton(m.buttons.get(i));
		}
		for(int i = 0; i < m.textAreas.size(); i++){
			addTextArea(m.textAreas.get(i));
		}
		BOX = m.BOX;
		OPACITY = m.OPACITY;
		RENDERTITLE = m.RENDERTITLE;
		SCRIPT = m.SCRIPT;
		TITLE = m.TITLE;
	}

	public void addButton(String text, String script, int x, int y, int w, int h, ButtonStyle bs){
		buttons.add(new Button(text, script, new Rectangle(x + BOX.x, y + BOX.y, w, h), bs));
	}
	
	public void addButton(String text, String script, int index, int w, int h, ButtonStyle bs){
		addButton(text, script, 20, getCurrentY() - 100, w, h, bs);
	}
	
	public void addButton(String text, String script, int w, int h, ButtonStyle bs){
		addButton(text, script, getIndex(), w, h, bs);
	}

	public void addButton(Button b){
		buttons.add(b);
	}
	
	public void addTextArea(String t, int x, int y, int w, int h, LabelStyle ls){
		textAreas.add(new TextArea(t, x + BOX.x, y + BOX.y, w, h, ls));
	}
	
	public void addTextArea(String t, int index, int w, int h, LabelStyle ls){
		addTextArea(t, 20, getCurrentY() - 100, w, h, ls);
	}
	
	public void addTextArea(String t, int w, int h, LabelStyle ls){
		addTextArea(t, getIndex(), w, h, ls);
	}
	
	public void addTextArea(TextArea ta){
		textAreas.add(ta);
	}
	
	private int getIndex(){
		return buttons.size() + getTextAreaRows();
	}
	
	private int getCurrentY(){
		int y = 0;
		for(int i = 0; i < buttons.size(); i++){
			y -= buttons.get(i).BOX.height + itemInterval;
		}
		for(int i = 0; i < textAreas.size(); i++){
			for(int i2 = 0; i2 < textAreas.get(i).TEXT.length; i2++){
				y -= textAreas.get(i).getHeight() + itemInterval;
			}
		}
		return y;
	}
	
	private int getTextAreaRows() {
		int temp = 0;
		for(int i = 0; i < textAreas.size(); i++){
			temp += textAreas.get(i).TEXT.length;
		}
		return temp;
	}
	
	@Override
	public boolean intersects(Rectangle r){
		boolean temp = false;
		if(BOX.intersects(r)){
			temp = true;
		}else{
			for(int i = 0; i < buttons.size(); i++){
				if(buttons.get(i).intersects(r)){
					temp = true;
					break;
				}
			}
		}
		return temp;
	}
	
	@Override
	public void typeUpdate(Pointer p){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).update(p);
		}
	}

	@Override
	public void touchDown() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).ready();
		}
	}

	@Override
	public void touchUp() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setActive();
		}
	}

}
