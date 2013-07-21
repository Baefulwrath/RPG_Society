package ouroboros;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		loadStartupSettings(cfg);
		if(cfg.fullscreen && getSystemRes){
			Toolkit toolkit =  Toolkit.getDefaultToolkit();
			Dimension dim = toolkit.getScreenSize();
			cfg.width = dim.width;
			cfg.height = dim.height;
		}
		
		new LwjglApplication(new OS(startupState, defaultMenu), cfg);
	}
	
	public static boolean getSystemRes = false;
	public static String defaultMenu = "main";
	public static ProgramState startupState = ProgramState.DEFAULT;
	
	public static void loadStartupSettings(LwjglApplicationConfiguration cfg){
		try{
			Scanner reader = new Scanner(new File("STARTUPSETTINGS.txt"));
			cfg.title = reader.nextLine().substring(6);
			cfg.useGL20 = Boolean.parseBoolean(reader.nextLine().substring(8));
			cfg.width = Integer.parseInt(reader.nextLine().substring(6));
			cfg.height = Integer.parseInt(reader.nextLine().substring(7));
			cfg.resizable = Boolean.parseBoolean(reader.nextLine().substring(10));
			cfg.fullscreen = Boolean.parseBoolean(reader.nextLine().substring(11));
			getSystemRes = Boolean.parseBoolean(reader.nextLine().substring(13));
			startupState = ProgramState.parseState(reader.nextLine().substring(13));
			defaultMenu =  reader.nextLine().substring(12);
			reader.close();
		}catch(Exception ex){
			ex.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "There seems to be some kind of disturbence at startup,\nMy guess is this has to do with the STARTUPSETTINGS.txt -file.\nFix that shit.", "ERROR", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}
