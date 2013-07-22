package universe;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;

public class TopDownWorld extends World{
	public TopDownTile[][] tiles = new TopDownTile[0][0];
	public int x = 0;
	public int y = 0;
	public int tileWidth = 0;
	
	public TopDownWorld(int w, int h){
		setEmptyWorld(w, h);
	}

	public TopDownWorld(TopDownTile[][] t){
		tiles = t;
	}

	public TopDownWorld(String path, boolean internal){
		getFromFolder(path, internal);
	}
	
	public void setEmptyWorld(int w, int h){
		tiles = new TopDownTile[x][y];
		for(int i = 0; i < x; i++){
			for(int i2 = 0; i2 < y; i2++){
				tiles[i][i2] = new TopDownTile(0, false);
			}
		}
	}
	
	public void getFromFolder(String path, boolean internal){
		Scanner r;
		if(internal){
			r = new Scanner(Gdx.files.internal(path).readString());
		}else{
			r = new Scanner(Gdx.files.external(path).readString());
		}
		
		r.close();
	}
}
