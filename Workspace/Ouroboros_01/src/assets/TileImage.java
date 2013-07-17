package assets;

import java.awt.Rectangle;

import universe.Tile;

public class TileImage extends Tile{
	
	public Rectangle BOX = new Rectangle();

	public TileImage(int tex, boolean block, int x, int y, int w, int h) {
		super(tex, block);
		BOX = new Rectangle(x, y, w, h);
	}

	public TileImage(int tex, boolean block, Rectangle r) {
		super(tex, block);
		BOX = r;
	}
	
	public boolean intersects(Rectangle r){
		return BOX.intersects(r);
	}
	
}
