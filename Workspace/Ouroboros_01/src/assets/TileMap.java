package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileMap {
	private Texture t;
	private int w = 0;
	public TileMap(Texture texture){
		w = texture.getHeight();
		t = texture;
	}
	public Sprite getSprite(int i){
		Sprite s = new Sprite();
		try{
			s = new Sprite(new TextureRegion(t, w * i, 0, w, w));
		}catch(Exception ex){
			s = new Sprite(new TextureRegion(t, 0, 0, w, w));
		}
		return s;
	}
}
