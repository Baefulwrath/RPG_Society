package main;
import java.awt.Point;
import java.awt.Rectangle;


public class Tile extends Rectangle{
	public int type;
	public boolean block;
	public int z = 0;
	public Tile(int xi, int yi, int z, int w, int h, int ty, boolean blocked){
		super(xi, yi, w, h);
		type = ty;
		block = blocked;
	}
	public Tile(Rectangle r, int z, int ty, boolean blocked){
		super(r.x, r.y, r.width, r.height);
		type = ty;
		block = blocked;
	}
	
	public boolean hits(Tile t, boolean depth){
		if(depth){
			if(t.z == z && intersects(t)){
				return true;
			}else{
				return false;
			}
		}else{
			if(intersects(t)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public void position(int xi, int yi, boolean centered){
		x = xi;
		y = yi;
	}
	
	public void position(Point p, boolean centered){
		if(centered){
			x = p.x - (width / 2);
			y = p.y - (height / 2);
		}else{
			x = p.x;
			y = p.y;
		}
	}
	
	public void mirror(Tile t) {
		type = t.type;
		block = t.block;
		z = t.z;
	}
}
