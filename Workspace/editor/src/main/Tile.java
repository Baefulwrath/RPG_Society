package main;
import java.awt.Rectangle;


public class Tile extends Rectangle{
	public int texture;
	public boolean block;
	public int z = 0;
	public Tile(int xi, int yi, int z, int w, int h, int tex, boolean blocked){
		super(xi, yi, w, h);
		texture = tex;
		block = blocked;
	}
	public Tile(Rectangle r, int z, int tex, boolean blocked){
		super(r.x, r.y, r.width, r.height);
		texture = tex;
		block = blocked;
	}
}
