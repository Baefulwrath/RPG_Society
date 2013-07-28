package main;

public class World {
	public String id = "";
	public int x = 0;
	public int y = 0;
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
}
