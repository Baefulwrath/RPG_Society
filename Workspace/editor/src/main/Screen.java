package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class Screen extends JPanel{
	
	public Screen(){
		
	}
	
	public void paint(Graphics g){
		try {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);
			g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
			drawWorld(g2d, Main.world);
			g2d.setColor(Color.BLUE);
			g2d.drawRect(Main.brush.x, Main.brush.y, Main.brush.width, Main.brush.height);
			g2d.setColor(Color.DARK_GRAY);
			g2d.fill(new Rectangle2D.Float(getWidth() - 200, 0, 200, getHeight()));
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fill(new Rectangle2D.Float(getWidth() - 200, 0, 5, getHeight()));
			g2d.drawImage(Assets.getTileImage(Main.brush.type), getWidth() - 170, 60, 32, 32, null);
			for(int i = 0; i < Main.infoBoxes.size(); i++){
				drawInfoBox(g2d, Main.infoBoxes.get(i));
			}
			for(int i = 0; i < Main.buttons.size(); i++){
				drawButton(g2d, Main.buttons.get(i));
			}
		} catch (Exception ex) {
		}
	}
	
	public void drawButton(Graphics2D g2d, Button b){
		g2d.setColor(b.getBackColor());
		g2d.fill(new Rectangle2D.Float(b.x, b.y, b.width, b.height));
		g2d.setColor(b.getTextColor());
		g2d.drawString(b.title, b.x + 10, b.y + b.height - 2);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(b.x, b.y, b.width, b.height);
	}
	
	public void drawWorld(Graphics2D g2d, World w){
		if(w.tiles.length > 0){
			for(int x = 0; x < w.tiles.length; x++){
				for(int y = 0; y < w.tiles[x].length; y++){
					drawTile(g2d, w.tiles[x][y], w.x, w.y);
				}
			}
		}
	}
	
	public void drawTile(Graphics2D g2d, Tile t, int x, int y){
		g2d.drawImage(Assets.getTileImage(t.type), t.x + x, t.y + y, t.width, t.height, null);
		if(Main.showGrid){
			g2d.drawImage(Assets.grid, t.x + x, t.y + y, t.width, t.height, null);
		}
		if(t.block){
			g2d.drawImage(Assets.block, t.x + x, t.y + y, t.width, t.height, null);
		}
	}
	
	public void drawInfoBox(Graphics2D g2d, InfoBox n){
		g2d.setColor(n.getBackColor());
		g2d.fill(new Rectangle2D.Float(n.x, n.y, n.width, n.height));
		g2d.setColor(n.getTextColor());
		for(int i = 0; i < n.text.length; i++){
			g2d.drawString(n.text[i], n.x + 2, n.y + 12 + (12 * i));
		}
		g2d.setColor(Color.GRAY);
		g2d.drawRect(n.x, n.y, n.width, n.height);
	}
}
