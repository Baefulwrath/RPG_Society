package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		g2d.setColor(Main.getColor(Main.mouse.type, true));
		Rectangle r = Main.mouse.getBrush();
		g2d.drawRect(r.x, r.y, r.width, r.height);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(new Rectangle2D.Float(getWidth() - 150, 0, 150, getHeight()));
		g2d.setColor(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(getWidth() - 145, 5, 140, getHeight() - 10));
		g2d.setColor(Color.GREEN);
		g2d.drawString("Type: " + Main.mouse.type, getWidth() - 140, getHeight() - 20);
		g2d.drawString(Main.getTileData().title, getWidth() - 140, getHeight() - 64);
		g2d.drawString(Main.getTileData().info, getWidth() - 140, getHeight() - 50);
		for(int i = 0; i < Main.info.size(); i++){
			g2d.drawString(Main.info.get(i), getWidth() - 140, 20 + (i * 16));
		}
	}
	
	private void drawMap(Graphics2D g2d, World w){
		for(int x = 0; x < w.chunks.length; x++){
			for(int y = 0; y < w.chunks[x].length; y++){
				drawChunk(g2d, w.chunks[x][y], w.x, w.y, Main.chunkSize);
			}
		}
	}

	private void drawChunk(Graphics2D g2d, Chunk chunk, int x, int y, int size) {
		if(onScreen(chunk.x + x, chunk.y + y, size, size)){
			g2d.setColor(Main.getColor(chunk.type, false));
			g2d.fill(new Rectangle2D.Float(chunk.x + x, chunk.y + y, size, size));
			g2d.setColor(Color.BLACK);
			if(Main.showGrid){
				g2d.drawRect(chunk.x + x, chunk.y + y, size, size);
			}
			if(Main.showTileInfo && Main.chunkSize >= 16){
				g2d.drawString("" + chunk.type, chunk.x + x + 3, chunk.y + y + 16);
			}
		}
	}
	
	public boolean onScreen(int x, int y, int w, int h){
		return new Rectangle(x, y, w, h).intersects(new Rectangle(0, 0, getWidth(), getHeight()));
	}
}
