package main;
import java.awt.Point;
import java.awt.Rectangle;


public class Pointer extends Rectangle{
	public boolean down = false;
	
	public Pointer(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void position(int xi, int yi){
		x = xi;
		y = yi;
	}
	
	public void position(Point p){
		x = p.x;
		y = p.y;
	}
}
