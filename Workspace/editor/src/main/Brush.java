package main;
import java.awt.Rectangle;


public class Brush extends Tile{
	public Rectangle BOX = new Rectangle();
	public boolean down = false;
	public int z = 4000;
	
	public Brush(int x, int y, int zi, int w, int h){
		super(x, y, w, h, 0, false);
		BOX = new Rectangle(x, y, w, h);
		z = zi;
	}
	
	public Brush(Rectangle r, int zi){
		super(r, 0, false);
		BOX = r;
		z = zi;
	}
	
	public int getDistance(int zi){
		int distance = z - zi;
		if(distance < 0){
			distance = -distance;
		}
		return distance;
	}
}
