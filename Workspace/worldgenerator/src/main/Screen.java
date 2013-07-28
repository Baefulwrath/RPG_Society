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
		switch(Main.state){
		case FINISHED:
			g2d.setColor(Color.GREEN);
			break;
		case LOADING:
			g2d.setColor(Color.RED);
			break;
		case READY:
			g2d.setColor(Color.GREEN);
			break;
		case WORKING:
			g2d.setColor(Color.BLUE);
			break;
		}
		g2d.drawString(Main.state.toString() + " - " + System.currentTimeMillis(), 2, 12);
	}
}
