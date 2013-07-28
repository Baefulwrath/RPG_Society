package main;

import java.awt.Rectangle;

public class World {
	public String id = "";
	public int x = 20;
	public int y = 20;
	public int w = 0;
	public int h = 0;
	public Chunk[][] chunks;
	
	public World(String i, int wi, int hi, Chunk[][] c){
		id = i;
		w = wi;
		h = hi;
		chunks = c;
	}
	
	public void move(int xm, int ym){
		x += xm;
		y += ym;
	}

	public void paint(Mouse m) {
		for(int xi = 0; xi < chunks.length; xi++){
			for(int yi = 0; yi < chunks[xi].length; yi++){
				Chunk c = chunks[xi][yi];
				if(m.getBrush().intersects(new Rectangle(c.x + x, c.y + y, Main.chunkSize, Main.chunkSize))){
					chunks[xi][yi].paint(m);
				}
			}
		}
	}
}
