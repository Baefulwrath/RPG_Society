package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Assets {
	
	public static ArrayList<BufferedImage> tileImages = new ArrayList<BufferedImage>();
	
	public static void init(){
		try {
			BufferedImage tileMap = ImageIO.read(new File("data/tilemap.png"));
			for(int i = 0; i < tileMap.getWidth() / tileMap.getHeight(); i++){
				tileImages.add(tileMap.getSubimage(tileMap.getHeight() * i, 0, tileMap.getHeight(), tileMap.getHeight()));
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static BufferedImage getTileImage(int t) {
		return tileImages.get(t);
	}
	
}
