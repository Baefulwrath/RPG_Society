import java.util.ArrayList;

import javax.swing.JFrame;


public class Main {

	/**
	 * @param args
	 */
	
	public static World world;
	public static int width = 1280;
	public static int height = 720;
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static InputHandler IH = new InputHandler();
	
	public static boolean initialized = false;
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(IH);
		frame.addMouseListener(IH);
		frame.addMouseMotionListener(IH);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		Assets.init();
		world = new World("", "", 10, 10, 0, 0);
		initialized = true;
	}
	
	public static void run(){
		while(true){
			try{
				if(initialized){
					Thread.sleep(5);
					update();
				}
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}
	
	public static void update(){
		scr.repaint();
	}
	
	public static void exit(){
		System.exit(0);
	}

}
