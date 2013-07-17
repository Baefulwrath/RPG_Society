package assets;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.Sprite;



public class AnimatedImage extends Animation{
	
	public Sprite[] imgs = new Sprite[0];
	public boolean setBounds = false;
	
	public AnimatedImage(Sprite s, int x, int y, int w, int h){
		imgs = new Sprite[1];
		s.setBounds(x, y, w, h);
		imgs[0] = s;
		setBounds = true;
	}

	public AnimatedImage(Sprite s, Rectangle r){
		imgs = new Sprite[1];
		s.setBounds(r.x, r.y, r.width, r.height);
		imgs[0] = s;
		setBounds = true;
	}

	public AnimatedImage(Sprite[] s, int x, int y, int w, int h){
		for(int i = 0; i < s.length; i++){
			s[i].setBounds(x, y, w, h);
		}
		imgs = s;
		setBounds = true;
	}

	public AnimatedImage(Sprite[] s, Rectangle r){
		for(int i = 0; i < s.length; i++){
			s[i].setBounds(r.x, r.y, r.width, r.height);
		}
		imgs = s;
		setBounds = true;
	}

	public AnimatedImage(Sprite s){
		imgs = new Sprite[1];
		imgs[0] = s;
		setBounds = false;
	}

	public AnimatedImage(Sprite[] s){
		imgs = s;
		setBounds = false;
	}
	
	public Sprite getImage(){
		cImg = getCurrent(cImg, imgs.length);
		return imgs[cImg];
	}
	
	public void add(Sprite s){
		Sprite[] temp = imgs;
		imgs = new Sprite[imgs.length + 1];
		for(int i = 0; i < temp.length; i++){
			imgs[i] = temp[i];
		}
		imgs[imgs.length - 2] = s;
	}

}
