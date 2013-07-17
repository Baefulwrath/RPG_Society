package ui;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class CollisionArea extends Rectangle{
	
	public CollisionArea(int xi, int yi, int wi, int hi){
		x = xi;
		y = yi;
		width = wi;
		height = hi;
	}
	
	public CollisionArea(Rectangle r){
		x = r.x;
		y = r.y;
		width = r.width;
		height = r.height;
	}
}
