package main;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main {

	/**
	 * @param args
	 */
	
	public static World world;
	public static int width = 1280;
	public static int height = 720;
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static ArrayList<InfoBox> infoBoxes = new ArrayList<InfoBox>();
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static InputHandler IH = new InputHandler();
	public static Brush brush = new Brush(0, 0, 10, 10);
	
	public static boolean painting = false;
	public static boolean initialized = false;
	public static long timeSinceLastUpdate = 0;
	public static ArrayList<Long> times = new ArrayList<Long>();
	public static long lastUpdate = 0;
	
	public static String message = "Ready for work :)";
	public static boolean showGrid = true;
	public static boolean showChunks = true;
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	private static void init(){
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(IH);
		frame.addMouseListener(IH);
		frame.addMouseMotionListener(IH);
		frame.addMouseWheelListener(IH);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		Assets.init();
		world = new World("UNTITLED", "NOID", "NOCELL", 10, 10, 0, 0, 50, 50);
		loadUI();
		ScriptHandler.setup();
		initialized = true;
	}
	
	private static void loadUI() {
		buttons.clear();
		buttons.add(new Button(width - 180, 30, 150, 20, "Change Brush Type", "changeBrushType_"));
		buttons.add(new Button(width - 180, 130, 150, 20, "Switch 'Block'", "switchBrushBlock_"));
		buttons.add(new Button(width - 180, 160, 150, 20, "Plus Z", "brushZ_+"));
		buttons.add(new Button(width - 180, 190, 150, 20, "Minus Z", "brushZ_-"));
		buttons.add(new Button(width - 180, 350, 70, 20, "Grid", "switchGrid_"));
		buttons.add(new Button(width - 105, 350, 75, 20, "Chunks", "switchChunks_"));
		buttons.add(new Button(width - 180, 380, 150, 20, "Plus Speed", "worldSpeed_+"));
		buttons.add(new Button(width - 180, 410, 150, 20, "Minus Speed", "worldSpeed_-"));
		buttons.add(new Button(width - 180, 440, 150, 20, "Plus Brush Size", "brushSize_+"));
		buttons.add(new Button(width - 180, 470, 150, 20, "Minus Brush Size", "brushSize_-"));
		buttons.add(new Button(width - 180, 500, 150, 20, "New World", "world_new"));
		buttons.add(new Button(width - 180, 530, 150, 20, "Load World", "world_load"));
		buttons.add(new Button(width - 180, 560, 150, 20, "Save World", "world_save"));
		buttons.add(new Button(width - 180, 590, 150, 20, "Change Tileset", "changeTileset_"));
		
		infoBoxes.clear();
		infoBoxes.add(new InfoBox(width - 150, 5, 100, 16, new String[]{InputHandler.mouse.x + ", " + InputHandler.mouse.y}));
		infoBoxes.add(new InfoBox(width - 140, 60, 100, 60, new String[]{Assets.getTileTitle(brush.type), Assets.getTileInfo(brush.type), "Bock: " + brush.block}));
		infoBoxes.add(new InfoBox(width - 180, 230, 150, 100, new String[]{"Show Grid: " + showGrid, "Show Chunks: " + showChunks, "Speed: " + world.speed, "Brush Size: " + brush.getW(), "Title: " + world.title, "ID: " + world.id, "Realm: " + world.realm}));
		infoBoxes.add(new InfoBox(width - 210, height - 70, 180, 16, new String[]{message}));
		infoBoxes.add(new InfoBox(width - 210, height - 90, 40, 16, new String[]{"" + timeSinceLastUpdate}));
	}

	public static void run(){
		while(true){
			try{
				if(initialized){
					Thread.sleep(25);
					update();
				}
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}
	
	public static void update(){
		width = frame.getWidth();
		height = frame.getHeight();
		loadUI();
		if(painting){
			paint();
		}
		ScriptHandler.update();
		InputHandler.update();
		world.update();
		scr.repaint();
		timeUpdate();
	}
	
	public static void timeUpdate(){
		times.add(System.currentTimeMillis() - lastUpdate);
		if(times.size() > 10){
			times.remove(0);
		}
		long temp = 0;
		for(int i = 0; i < times.size(); i++){
			temp += times.get(i);
		}
		timeSinceLastUpdate = temp / times.size();
		lastUpdate = System.currentTimeMillis();
	}
	
	public static void exit(){
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame, "Exit?", "Exit?", JOptionPane.YES_NO_OPTION)){
			System.exit(0);
		}
	}

	public static void paint() {
		world.paint(brush);
	}

    public static Color getRandomColor(boolean black) {
        int r = new Random().nextInt(13);
        return getColor(r, black);
    }

    public static Color getColor(int col, boolean black) {
        Color C = Color.RED;
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

}
