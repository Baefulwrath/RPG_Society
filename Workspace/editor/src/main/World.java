package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class World {
	public String title;
	public String id;
	public String realm;
	public Tile[][][] tiles;
	public int x;
	public int y;
	private static int TW = 64;
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	public int speed = 5;
	
	public World(String t, String i, String r, int w, int h, int xin, int yin){
		title = t;
		id = i;
		realm = r;
		x = xin;
		y = yin;
		tiles = new Tile[w][h][1000];
		for(int xi = 0; xi < w; xi++){
			for(int yi = 0; yi < h; yi++){
				for(int zi = 0; zi < tiles[xi][yi].length; zi++){
					tiles[xi][yi][zi] = new Tile(TW * xi, TW * yi, TW, TW, 0, false);
				}
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
			realm = r.nextLine();
			tiles = new Tile[Integer.parseInt(r.nextLine())][Integer.parseInt(r.nextLine())][Integer.parseInt(r.nextLine())];
			while(r.hasNextLine()){
				tiles[Integer.parseInt(r.nextLine())][Integer.parseInt(r.nextLine())][Integer.parseInt(r.nextLine())] = new Tile(Integer.parseInt(r.nextLine()), Integer.parseInt(r.nextLine()), TW, TW, Integer.parseInt(r.nextLine()), Boolean.parseBoolean(r.nextLine()));
				
			}
			r.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		}
		
	}

	public void paint(Tile t) {
		for(int xi = 0; xi < tiles.length; xi++){
			for(int yi = 0; yi < tiles[xi].length; yi++){
				for(int zi = 0; zi < tiles[xi][yi].length; zi++){
					if(tiles[xi][yi][zi].intersects(t)){
						tiles[xi][yi][zi].mirror(t);
					}
				}
			}
		}
	}
	
	public void update(){
		if(up){
			move(0, -speed);
		}else if(down){
			move(0, speed);
		}
		if(left){
			move(-speed, 0);
		}else if(right){
			move(speed, 0);
		}
	}
	
	public void move(int xm, int ym){
		x += xm;
		y += ym;
		for(int xi = 0; xi < tiles.length; xi++){
			for(int yi = 0; yi < tiles[xi].length; yi++){
				for(int zi = 0; zi < tiles[xi][yi].length; zi++){
					tiles[xi][yi][zi].x += xm;
					tiles[xi][yi][zi].y += ym;
				}
			}
		}
	}
}
