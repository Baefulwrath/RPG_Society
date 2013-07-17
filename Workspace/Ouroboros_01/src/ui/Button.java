package ui;

import input.Pointer;

import java.awt.Rectangle;

import scripting.ScriptHandler;

import assets.ButtonStyle;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Button {
	public boolean HOVER = false;
	public boolean ACTIVE = false;
	public boolean READY = false;
	public String TITLE = "";
	public String SCRIPT = "";
	public ButtonStyle STYLE;
	public Rectangle BOX = new Rectangle();
	public int TITLEX = 16;

	public Button(String text, String buttonScript, Rectangle locdim, ButtonStyle style) {
		TITLE = text;
		SCRIPT = buttonScript;
		BOX = locdim;
		STYLE = style;
	}
	public void update(Pointer p){
		hoverCheck(p);
		if(ACTIVE && HOVER){
			activate();
		}else{
			ACTIVE = false;
		}
	}
	
	private void hoverCheck(Pointer p) {
		if(intersects(p)){
			HOVER = true;
		}else{
			HOVER = false;
		}
	}

	public boolean intersects(Rectangle r) {
		if(BOX.intersects(r)){
			return true;
		}else{
			return false;
		}
	}

	public LabelStyle getLabelStyle(){
		return STYLE.LABELSTYLE;
	}
	
	public NinePatch getTex(){
		if(READY && HOVER){
			return STYLE.DOWN;
		}else if(HOVER){
			return STYLE.HOVER;
		}else{
			return STYLE.UP;
		}
	}
	
	public int getTextY(){
		int temp = (int) ((BOX.height / 2) + (STYLE.LABELSTYLE.font.getCapHeight() / 2));
		return temp;
	}

	public void inputUpdate(Pointer p) {
		if(intersects(p)){
			HOVER = true;
		}else{
			HOVER = false;
		}
		if(p.down){
			ACTIVE = true;
		}else{
			activate();
			ACTIVE = false;
		}
	}
	
	public void ready(){
		READY = true;
	}
	
	public void setActive(){
		ACTIVE = true;
		READY = false;
	}
	
	private void activate(){
		ACTIVE = false;
		ScriptHandler.handleScript(SCRIPT);
	}
}
