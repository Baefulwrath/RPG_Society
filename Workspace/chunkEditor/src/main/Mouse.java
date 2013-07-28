package main;

import java.awt.Point;
import java.awt.Rectangle;

public class Mouse {
	public int x = 0;
	public int y = 0;
	public int brushSize = 5;
	public byte type = 0;
	
	public void set(Point p){
		x = p.x;
		y = p.y;
	}
	
	public void change(int diff){
		if(diff < 0 && type > Byte.MIN_VALUE){
			type += diff;
		}else if(type < Byte.MAX_VALUE){
			type += diff;
		}
	}
	
	public Rectangle getBrush(){
		return new Rectangle(x - (brushSize / 2), y - (brushSize / 2), brushSize, brushSize);
	}

	public void sizeUp() {
		if(brushSize < 100 - 5){
			brushSize += 5;
		}
	}

	public void sizeDown() {
		if(brushSize > 4){
			brushSize -= 5;
		}
	}
}
