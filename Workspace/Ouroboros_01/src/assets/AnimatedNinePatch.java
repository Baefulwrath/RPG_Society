package assets;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimatedNinePatch extends Animation{
	
	private NinePatch[] imgs = new NinePatch[0];
	public Rectangle BOX = new Rectangle();

	public AnimatedNinePatch(NinePatch n, int x, int y, int w, int h) {
		imgs = new NinePatch[1];
		imgs[0] = n;
		BOX = new Rectangle(x, y, w, h);
	}
	
	public AnimatedNinePatch(NinePatchImage n) {
		imgs = new NinePatch[1];
		imgs[0] = n.N;
		BOX = new Rectangle(n.BOX.x, n.BOX.y, n.BOX.width, n.BOX.height);
	}
	public AnimatedNinePatch(NinePatch n, Rectangle r) {
		imgs = new NinePatch[1];
		imgs[0] = n;
		BOX = r;
	}
	
	public AnimatedNinePatch(NinePatch[] n, int x, int y, int w, int h) {
		imgs = n;
		BOX = new Rectangle(x, y, w, h);
	}
	
	public AnimatedNinePatch(NinePatchImage[] n, int x, int y, int w, int h) {
		imgs = new NinePatch[n.length];
		for(int i = 0; i < n.length; i++){
			imgs[i] = n[i].N;
		}
		BOX = new Rectangle(n[0].BOX.x, n[0].BOX.y, n[0].BOX.width, n[0].BOX.height);
	}
	
	public AnimatedNinePatch(NinePatchImage[] n) {
		imgs = new NinePatch[n.length];
		for(int i = 0; i < n.length; i++){
			imgs[i] = n[i].N;
		}
	}
	
	public AnimatedNinePatch(NinePatch[] n) {
		imgs = n;
	}
	
	public NinePatchImage getImage(){
		cImg = getCurrent(cImg, imgs.length);
		return new NinePatchImage(imgs[cImg], BOX);
	}
	
	public void add(NinePatch n){
		NinePatch[] temp = imgs;
		imgs = new NinePatch[imgs.length + 1];
		for(int i = 0; i < temp.length; i++){
			imgs[i] = temp[i];
		}
		imgs[imgs.length - 2] = n;
	}
}
