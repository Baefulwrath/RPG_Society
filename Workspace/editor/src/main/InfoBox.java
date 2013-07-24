package main;
import java.awt.Color;
import java.awt.Rectangle;


public class InfoBox extends Rectangle{
	public String[] text = new String[0];
	
	public InfoBox(int xi, int yi, int w, int h, String[] ti){
		super(xi, yi, w, h);
		text = ti;
	}
	
	public Color getBackColor(){
		return Color.BLACK;
	}
	
	public Color getTextColor(){
		return Color.WHITE;
	}
}
