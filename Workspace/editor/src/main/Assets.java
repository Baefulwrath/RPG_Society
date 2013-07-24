package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Assets {
	
	public static ArrayList<TileData> tileData = new ArrayList<TileData>();

	public static BufferedImage tileMap;
	public static BufferedImage grid;
	public static BufferedImage block;
	
	public static void init(){
		try {
			tileMap = ImageIO.read(new File("data/tilemap.png"));
			grid = ImageIO.read(new File("data/grid.png"));
			block = ImageIO.read(new File("data/block.png"));
			Scanner r = new Scanner(new File("data/tileData.txt"));
			int i = 0;
			while(r.hasNextLine()){
				tileData.add(new TileData(tileMap.getSubimage(tileMap.getHeight() * i, 0, tileMap.getHeight(), tileMap.getHeight()), r.nextLine(), r.nextLine()));
				i++;
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static BufferedImage getTileImage(int i) {
		return tileData.get(i).image;
	}
	
	public static String getTileTitle(int i){
		return tileData.get(i).title;
	}
	
	public static String getTileInfo(int i){
		return tileData.get(i).info;
	}

	public static String[] getTileTitles(boolean addIndex) {
		String[] values = new String[tileData.size()];
		for(int i = 0; i < values.length; i++){
			values[i] = i + getTileTitle(i);
		}
		return values;
	}
	
}
