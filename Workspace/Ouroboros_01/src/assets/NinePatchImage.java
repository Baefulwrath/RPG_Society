package assets;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NinePatchImage {
	public Rectangle BOX;
	public NinePatch N;

	public NinePatchImage(NinePatch n, int x, int y, int w, int h){
		N = n;
		BOX = new Rectangle(x, y, w, h);
	}
	
	public NinePatchImage(NinePatch n, Rectangle r){
		N = n;
		BOX = r;
	}
	
	public void draw(SpriteBatch batch){
		N.draw(batch, BOX.x, BOX.y, BOX.width, BOX.height);
	}
}
