package main;

public class Chunk {
	public int x = 0;
	public int y = 0;
	public short seed = 0;
	public byte type = 0;
	
	public Chunk(int xi, int yi, short seedi, byte typei){
		x = xi;
		y = yi;
		seed = seedi;
		type = typei;
	}
}