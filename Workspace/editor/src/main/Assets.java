package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Assets {
	
	public static ArrayList<TileData> tileData = new ArrayList<TileData>();
	public static HashMap<String, BufferedImage> tileMaps = new HashMap<String, BufferedImage>();
	public static String activeMap = "test";
	public static BufferedImage grid;
	public static BufferedImage block;
	public static BufferedImage overlay;
	
	public static void init(){
		try {
			grid = ImageIO.read(new File("data/grid.png"));
			block = ImageIO.read(new File("data/block.png"));
			overlay = ImageIO.read(new File("data/overlay.png"));
			Scanner r = new Scanner(new File("tilesets/INDEX.index"));
			activeMap = r.nextLine();
			while(r.hasNextLine()){
				String line = r.nextLine();
				tileMaps.put(line.substring(0, line.indexOf('.')), ImageIO.read(new File("tilesets/" + line)));
			}
			r.close();
			Scanner r2 = new Scanner(new File("data/tileData.data"));
			int i = 0;
			while(r2.hasNextLine()){
				tileData.add(new TileData(getTileMap().getSubimage(getTileMap().getHeight() * i, 0, getTileMap().getHeight(), getTileMap().getHeight()), r2.nextLine(), r2.nextLine()));
				i++;
			}
			r2.close();
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	public static void setTileImages(String map){
		activeMap = map;
		for(int i = 0; i < tileData.size(); i++){
			tileData.get(i).image = getTileMap().getSubimage(getTileMap().getHeight() * i, 0, getTileMap().getHeight(), getTileMap().getHeight());
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
			if(addIndex){
				values[i] = i + getTileTitle(i);
			}else{
				values[i] = getTileTitle(i);
			}
		}
		return values;
	}
	
	public static BufferedImage getTileMap(){
		return tileMaps.get(activeMap);
	}

	public static String[] getTilesetNames() {
		String[] values = new String[tileMaps.size()];
		int i = 0;
	    for(Map.Entry<String, BufferedImage> entry : tileMaps.entrySet()){
	    	values[i] = entry.getKey();
	    	i++;
	    }
		return values;
	}
	
}
