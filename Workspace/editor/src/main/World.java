package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class World {
	public String title;
	public String id;
	public Tile[][] tiles;
	public int x;
	public int y;
	private static int TW = 64;
	
	public World(String t, String i, int w, int h, int xin, int yin){
		title = t;
		id = i;
		x = xin;
		y = yin;
		tiles = new Tile[w][h];
		for(int xi = 0; xi < w; xi++){
			for(int yi = 0; yi < h; yi++){
				tiles[xi][yi] = new Tile(TW * xi, TW * yi, 0, TW, TW, 0, false);
			}
		}
	}
	
	/*
	 	title
		id
		width
		height
		[tiles]
			x
			y
			z
			texture
			blocked
			type
	 */
	public World(String file){
		try {
			Scanner r = new Scanner(new File(file));
			title = r.nextLine();
			id = r.nextLine();
			tiles = new Tile[Integer.parseInt(r.nextLine())][Integer.parseInt(r.nextLine())];
			int x = 0;
			int y = 0;
			while(r.hasNextLine()){
				tiles[x][y] = new Tile(Integer.parseInt(r.nextLine()), Integer.parseInt(r.nextLine()), Integer.parseInt(r.nextLine()), TW, TW, Integer.parseInt(r.nextLine()), Boolean.parseBoolean(r.nextLine()));
			}
			r.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		}
		
	}
}
