package main;
import java.awt.Point;
import java.awt.Rectangle;


public class Brush extends Tile{
	public Rectangle BOX = new Rectangle();
	public boolean down = false;
	
	public Brush(int x, int y, int w, int h){
		super(x, y, w, h, 0, false);
		BOX = new Rectangle(x, y, w, h);
	}
	
	public void position(Point p, boolean centered){
		if(centered){
			x = p.x - (w / 2);
			y = p.y - (h / 2);
		}else{
			x = p.x;
			y = p.y;
		}
	}
	
	public void resize(int wdiff, int hdiff){
		w += wdiff;
		h += hdiff;
	}
	
	@Override
	public Rectangle getR(){
		return new Rectangle(x, y, w, h);
	}
}
