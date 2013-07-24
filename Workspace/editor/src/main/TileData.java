package main;

import java.awt.image.BufferedImage;

public class TileData {
	public BufferedImage image;
	public String title;
	public String info;
	public TileData(BufferedImage i, String t, String in){
		image = i;
		title = t;
		info = in;
	}
}
