package main;

import java.awt.Rectangle;

public class Chunk {
	public Tile[][] tiles = new Tile[0][0];
	private static int TW = 64;
	private Rectangle r = new Rectangle();
	
	public Chunk(int x, int y, int w, int h){
		r = new Rectangle((x * w) * TW, (y * h) * TW, w * TW, h * TW);
		tiles = new Tile[w][h];
		for(int xi = 0; xi < w; xi++){
			for(int yi = 0; yi < h; yi++){
				tiles[xi][yi] = new Tile(TW * (xi + (x * w)), TW * (yi + (y * h)), TW, TW, 0, false);
			}
		}
	}
	
	public Rectangle getR(){
		return new Rectangle(r.x + Main.world.x, r.y + Main.world.y, r.width, r.height);
	}
}
