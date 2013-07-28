package main;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	/**
	 * @param args
	 */
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static DataInputStream in;
	public static DataOutputStream out;
	public static File file;
	public static World world;
	public static State state = State.LOADING;
	public static boolean initialized = false;
	
	public static void main(String[] args) {
		init();
		run();
	}

	public static void init(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(scr);
		scr.repaint();
		try {
			file = new File(JOptionPane.showInputDialog(frame, "Input Filename", "File"));
			if(!file.exists()){
				System.exit(0);
			}
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			String worldId = "";
			for(int i = 0; i < 5; i++){
				worldId += (char) in.readChar();
			}
			int worldW = in.readInt();
			int worldH = in.readInt();
			int chunkW = in.readInt();
			int chunkH = in.readInt();
			int defTS = in.readInt();
			boolean lowDetail = in.readBoolean();
			if(!lowDetail){
				System.exit(0);
			}
			Chunk[][] chunks = new Chunk[worldW][worldH];
			
			for(int wx = 0; wx < worldW; wx++){
				for(int wy = 0; wy < worldH; wy++){
					chunks[wx][wy] = new Chunk(in.readInt(), in.readInt(), in.readShort(),  in.readByte());
					for(int i = 0; i < chunkW * chunkH; i++){
						in.readBoolean();
					}
				}
			}
			
			world = new World(worldId, worldW, worldH, chunks);
			state = State.READY;
			initialized = true;
			scr.repaint();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void run(){
		while(true){
			try {
				Thread.sleep(25);
				update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update(){
		scr.repaint();
	}
}
