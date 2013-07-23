package main;
import java.awt.Color;
import java.awt.Rectangle;


public class Button extends Rectangle{
	public String title = "";
	public String script = "";
	public boolean pressed = false;
	
	public Button(int xi, int yi, int w, int h, String ti, String scr){
		super(xi, yi, w, h);
		title = ti;
		script = scr;
	}
	
	public void press(Pointer p){
		if(hover() && !pressed){
			ScriptHandler.handleScript(script);
			pressed = true;
		}
	}
	
	public boolean hover(){
		return hover(InputHandler.mouse);
	}
	
	public boolean hover(Pointer p){
		if(intersects(p)){
			return true;
		}else{
			return false;
		}
	}
	
	public void release(){
		pressed = false;
	}
	
	public Color getBackColor(){
		if(hover()){
			return Color.GRAY;
		}else{
			return Color.LIGHT_GRAY;
		}
	}
	
	public Color getTextColor(){
		if(hover()){
			return Color.WHITE;
		}else{
			return Color.BLACK;
		}
	}
}
