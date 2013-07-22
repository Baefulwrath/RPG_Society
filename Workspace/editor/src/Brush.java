import java.awt.Rectangle;


public class Brush extends Tile{
	public Rectangle BOX = new Rectangle();
	public boolean down = false;
	
	public Brush(int x, int y, int z, int w, int h){
		super(x, y, z, w, h, 0, false);
		BOX = new Rectangle(x, y, w, h);
	}
	
	public Brush(Rectangle r, int z){
		super(r, z, 0, false);
		BOX = r;
	}
}
