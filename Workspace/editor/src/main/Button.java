package main;
import java.awt.Color;
import java.awt.Rectangle;


public class Button extends Rectangle{
	public String title = "";
	public String script = "";
	
	public Button(int xi, int yi, int w, int h, String ti, String scr){
		super(xi, yi, w, h);
		title = ti;
		script = scr;
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
		if(hover()){
			ScriptHandler.handleScript(script);
		}
	}
	
	public Color getBackColor(){
		if(hover()){
			return Color.LIGHT_GRAY;
		}else{
			return Color.BLACK;
		}
	}
	
	public Color getTextColor(){
		if(hover()){
			return Color.BLACK;
		}else{
			return Color.GREEN;
		}
	}
}
