package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Screen extends JPanel{
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
		g2d.setColor(Color.WHITE);
		g2d.drawString(Main.state.toString(), 2, 16);
		if(Main.initialized){
			drawMap(g2d, Main.world);
		}
	}
	
	public void drawMap(Graphics2D g2d, World w){
		
	}
}
