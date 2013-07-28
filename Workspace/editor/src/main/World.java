package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class World {
	public String title;
	public String id;
	public String realm;
	private Chunk[][] chunks = new Chunk[0][0];
	public int x;
	public int y;
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	public int speed = 5;
	public int chunkWidth = 0;
	public int chunkHeight = 0;
	
	public World(String t, String i, String r, int w, int h, int xin, int yin, int cw, int ch){
		title = t;
		id = i;
		realm = r;
		x = xin;
		y = yin;
		chunkWidth = cw;
		chunkHeight = ch;
		chunks = new Chunk[w][h];
		for(int xi = 0; xi < chunks.length; xi++){
			for(int yi = 0; yi < chunks[xi].length; yi++){
				chunks[xi][yi] = new Chunk(xi, yi, cw, ch);
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
			/*
			 * You need to write this shit some time you know...
			 */
			r.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		}
	}

	public void paint(Brush b) {
		Chunk[] c = getChunks();
		for(int ci = 0; ci < c.length; ci++){
			Chunk a = c[ci];
			for(int tx = 0; tx < a.tiles.length; tx++){
				for(int ty = 0; ty < a.tiles[tx].length; ty++){
					Tile t = a.tiles[tx][ty];
					if(a.tiles[tx][ty].getR().intersects(t.getR())){
						a.tiles[tx][ty].mirror(b);
					}
				}
			}
		}
	}
	
	public Chunk[] getChunks() {
		ArrayList<Chunk> c = new ArrayList<Chunk>();
		for(int xi = 0; xi < chunks.length; xi++){
			for(int yi = 0; yi < chunks[xi].length; yi++){
				if(Main.scr.onScreen(chunks[xi][yi].getR())){
					c.add(chunks[xi][yi]);
				}
			}
		}
		Chunk[] ca = new Chunk[c.size()];
		for(int i = 0; i < ca.length; i++){
			ca[i] = c.get(i);
		}
		return ca;
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
	}
}
