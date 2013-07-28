package main;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static int worldWidth = 0;
	public static int worldHeight = 0;
	public static int chunkWidth = 0;
	public static int chunkHeight = 0;
	public static String worldId = "";
	public static byte defaultTileType = 0;
	public static int defaultTileSize = 0;
	public static boolean defaultTileBlock = false;
	public static boolean lowDetail = false;
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static InputHandler IH = new InputHandler();
	
	public static State state = State.LOADING;
	
	public static void main(String[] args) {
		init();
		state = State.READY;
		run();
	}
	
	public static void init(){
		setupSystem();
		loadFile();
	}
	
	public static void setupSystem(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 50);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(scr);
		frame.addKeyListener(IH);
		scr.repaint();
	}
	
	public static void loadFile(){
		Scanner r;
		try {
			r = new Scanner(new File("orders.txt"));
			worldWidth = Integer.parseInt(r.nextLine().substring(4));
			worldHeight = Integer.parseInt(r.nextLine().substring(4));
			chunkWidth = Integer.parseInt(r.nextLine().substring(4));
			chunkHeight = Integer.parseInt(r.nextLine().substring(4));
			worldId = r.nextLine().substring(4);
			defaultTileType = Byte.parseByte(r.nextLine().substring(4));
			defaultTileSize = Integer.parseInt(r.nextLine().substring(4));
			defaultTileBlock = Boolean.parseBoolean(r.nextLine().substring(4));
			lowDetail = Boolean.parseBoolean(r.nextLine().substring(4));
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void run(){
		while(true){
			try {
				Thread.sleep(1);
				update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update(){
		scr.repaint();
	}
	
	public static void work(){
		state = State.WORKING;
		update();
		File file = new File("maps/" + worldId + ".map");
		System.out.println("Starting work...");
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			int chunkX = 0;
			int chunkY = 0;
			
			out.writeChars(worldId);
			out.writeInt(worldWidth);
			out.writeInt(worldHeight);
			out.writeInt(chunkWidth);
			out.writeInt(chunkHeight);
			out.writeInt(defaultTileSize);
			out.writeBoolean(lowDetail);

			for(int wx = 0; wx < worldWidth; wx++){
				for(int wy = 0; wy < worldHeight; wy++){
					int currCX = chunkX * (defaultTileSize * chunkWidth);
					int currCY = chunkY * (defaultTileSize * chunkHeight);
					out.writeInt(currCX);
					out.writeInt(currCY);
					if(lowDetail){
						out.writeShort(new Random().nextInt(30000));
						out.writeByte(defaultTileType);
					}
					for(int cx = 0; cx < chunkWidth; cx++){
						for(int cy = 0; cy < chunkHeight; cy++){
							out.writeBoolean(defaultTileBlock);
							if(!lowDetail){
								out.writeInt(defaultTileType);
							}
						}
					}
					if(chunkX >= worldWidth){
						chunkX = 0;
						chunkY = increase(chunkY, worldHeight);
					}else{
						chunkX++;
					}
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		state = State.FINISHED;
		System.out.println("Finished.");
		scr.repaint();
	}
	
	public static int increase(int base, int limit){
		if(base >= limit){
			return 0;
		}else{
			return base + 1;
		}
	}

}
