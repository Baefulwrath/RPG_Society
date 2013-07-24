package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class World {
	public String title;
	public String id;
	public String cell;
	public Tile[][] tiles;
	public int x;
	public int y;
	private static int TW = 64;
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	public int speed = 5;
	
	public World(String t, String i, String c, int w, int h, int xin, int yin){
		title = t;
		id = i;
		cell = c;
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
			cell = r.nextLine();
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

	public void paint(Tile t) {
		for(int xi = 0; xi < tiles.length; xi++){
			for(int yi = 0; yi < tiles[xi].length; yi++){
				if(tiles[xi][yi].intersects(t) && t.z == tiles[xi][yi].z){
					tiles[xi][yi].mirror(t);
				}
			}
		}
	}
	
	public void update(){
		if(up){
			y -= speed;
		}else if(down){
			y += speed;
		}
		if(left){
			x -= speed;
		}else if(right){
			x += speed;
		}
	}
}
