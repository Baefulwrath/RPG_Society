package main;

import java.awt.Rectangle;


public class Tile{
	public int type;
	public boolean block;
	protected int x = 0;
	protected int y = 0;
	protected int w = 0;
	protected int h = 0;
	
	public Tile(int xi, int yi, int wi, int hi, int ty, boolean blocked){
		x = xi;
		y = yi;
		w = wi;
		h = hi;
		type = ty;
		block = blocked;
	}
	
	public Rectangle getR(){
		return new Rectangle(x + Main.world.x, y + Main.world.y, w, h);
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	public void mirror(Tile t) {
		type = t.type;
		block = t.block;
	}
}
