package main;

import java.awt.Color;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
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
	public static InputHandler IH = new InputHandler();
	public static int chunkSize = 32;
	public static ArrayList<String> info = new ArrayList<String>();
	public static Mouse mouse = new Mouse();
	public static boolean painting = false;
	public static boolean showGrid = true;
	public static boolean showTileInfo = true;
	public static ArrayList<TileData> tileData = new ArrayList<TileData>();
	public static int defaultTileSize = 0;
	public static int chunkW = 0;
	public static int chunkH = 0;
	public static boolean lowDetail = true;
	
	public static void main(String[] args) {
		init();
		run();
	}

	public static void init(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addKeyListener(IH);
		frame.addMouseListener(IH);
		frame.addMouseWheelListener(IH);
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
			chunkW = in.readInt();
			chunkH = in.readInt();
			defaultTileSize = in.readInt();
			lowDetail = in.readBoolean();
			if(!lowDetail){
				System.exit(0);
			}
			Chunk[][] chunks = new Chunk[worldW][worldH];
			
			for(int wx = 0; wx < worldW; wx++){
				for(int wy = 0; wy < worldH; wy++){
					/*in.readInt();
					in.readInt();
					chunks[wx][wy] = new Chunk(wx * chunkSize, wy * chunkSize, in.readShort(),  in.readByte());*/
					chunks[wx][wy] = new Chunk(in.readInt(), in.readInt(), in.readShort(),  in.readByte());
					for(int i = 0; i < chunkW * chunkH; i++){
						in.readBoolean();
					}
				}
			}
			 
			in.close();
			world = new World(worldId, worldW, worldH, chunks);
			state = State.READY;
			info.add("Arrow keys move");
			info.add("+/- switches type");
			info.add("scroll +/- brush size");
			info.add("page u/d chunk size");
			info.add("ESC to exit");
			info.add("G to switch grid");
			info.add("I to switch tile info");
			info.add("S to save map");
			
			Scanner r = new Scanner(new File("tileData.data"));
			while(r.hasNextLine()){
				tileData.add(new TileData(r.nextLine(), r.nextLine()));
			}
			r.close();
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
		try {
			mouse.set(scr.getMousePosition());
		} catch (Exception e) {
		}
		if(painting){
			world.paint(mouse);
		}
		scr.repaint();
	}

    public static Color getRandomColor(boolean black) {
        return getColor(new Random().nextInt(13), black);
    }

    public static Color getColor(int col, boolean black) {
        Color C = Color.RED;
        while(col > 12){
        	col -= 12;
        }
        switch (col) {
            case 0:
                C = Color.BLUE;
                break;
            case 1:
                C = Color.CYAN;
                break;
            case 2:
                C = Color.DARK_GRAY;
                break;
            case 3:
                C = Color.GRAY;
                break;
            case 4:
                C = Color.GREEN;
                break;
            case 5:
                C = Color.LIGHT_GRAY;
                break;
            case 6:
                C = Color.MAGENTA;
                break;
            case 7:
                C = Color.ORANGE;
                break;
            case 8:
                C = Color.PINK;
                break;
            case 9:
                C = Color.RED;
                break;
            case 10:
                C = Color.WHITE;
                break;
            case 11:
                C = Color.YELLOW;
                break;
            case 12:
                if (black) {
                    C = Color.BLACK;
                }
                break;
        }
        return C;
    }

	
    public static void chunkUp() {
    	if(chunkSize < 512){
    		chunkSize *= 2;
    		reset();
    	}
	}

	public static void chunkDown() {
    	if(chunkSize > 2){
    		chunkSize /= 2;
    		reset();
    	}
	}
    
    private static void reset() {
    	/*for(int x = 0; x < world.chunks.length; x++){
    		for(int y = 0; y < world.chunks[x].length; y++){
    			world.chunks[x][y].setPos(x * chunkSize, y * chunkSize);
    		}
    	}*/
	}

	public static TileData getTileData() {
		return getTileData(mouse.type);
	}
	
	public static TileData getTileData(byte t){
		TileData td = new TileData("-", "-");
		try {
			TileData temp = tileData.get(t);
			td = temp;
		} catch (Exception e) {}
		return td;
	}
	
	public static void saveMap(){
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame, "Do you want to save map?", "Save map?", JOptionPane.YES_NO_OPTION)){
			doSaveMap();
		}
	}
	
	private static void doSaveMap(){
		try {
			state = State.WORKING;
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("result.map"))));
			
			
			out.writeChars(world.id);
			for(int i = 0; i < 5; i++){
				in.readChar();
			}
			out.writeInt(in.readInt());
			out.writeInt(in.readInt());
			out.writeInt(in.readInt());
			out.writeInt(in.readInt());
			out.writeInt(in.readInt());
			out.writeBoolean(in.readBoolean());
			

			for(int wx = 0; wx < world.w; wx++){
				for(int wy = 0; wy < world.h; wy++){
					out.writeInt(in.readInt());
					out.writeInt(in.readInt());
					if(lowDetail){
						out.writeShort(in.readShort());
						in.readByte();
						out.writeByte(world.chunks[wx][wy].type);
					}
					for(int cx = 0; cx < chunkW; cx++){
						for(int cy = 0; cy < chunkH; cy++){
							out.writeBoolean(in.readBoolean());
							if(!lowDetail){
								out.writeInt(in.readInt());
							}
						}
					}
				}
			}
			out.close();
			in.close();
			state = State.FINISHED;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
