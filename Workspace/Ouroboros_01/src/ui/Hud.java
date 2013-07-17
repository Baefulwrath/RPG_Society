package ui;

import input.Pointer;

import java.awt.Rectangle;
import java.util.ArrayList;


import assets.ButtonStyle;
import assets.NinePatchImage;
import assets.TileImage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class Hud extends UIObject{
	
	public String group[] = new String[0];
	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<TextArea> textAreas = new ArrayList<TextArea>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<CollisionArea> collisionAreas = new ArrayList<CollisionArea>();
	public ArrayList<Image> images = new ArrayList<Image>();
	public ArrayList<NinePatchImage> ninePatches = new ArrayList<NinePatchImage>();
	public ArrayList<TileImage> tileImages = new ArrayList<TileImage>();

	public Hud(String title, String script, int x, int y, int w, int h, String[] g) {
		super(title, script, x, y, w, h);
		group = g;
		setup();
	}
	
	public Hud(String title, String script, Rectangle r, String[] g) {
		super(title, script, r);
		group = g;
		setup();
	}
	
	public boolean contains(String g){
		boolean temp = false;
		for(int i = 0; i < group.length; i++){
			if(group[i].equals(g)){
				temp = true;
				break;
			}
		}
		return temp;
	}
	
	@Override
	public void typeUpdate(Pointer p){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).update(p);
		}
	}
	
	public void addLabel(String title, int x, int y, LabelStyle ls){
		Label l = new Label(title, ls);
		l.setPosition(x, y);
		labels.add(l);
	}
	
	public void addTextArea(String text, int x, int y, int w, int h, LabelStyle ls){
		textAreas.add(new TextArea(text, x, y, w, h, ls));
	}
	
	public void addButton(String title, String script, int x, int y, int w, int h, ButtonStyle bs){
		buttons.add(new Button(title, script, new Rectangle(x, y, w, h), bs));
	}
	
	public void addCollisionAreas(int x, int y, int w, int h){
		collisionAreas.add(new CollisionArea(x, y, w, h));
	}
	
	public void addImage(Sprite s, int x, int y, int w, int h){
		Image i = new Image(s);
		i.setBounds(x, y, w, h);
		images.add(i);
	}
	
	public void addImage(TextureRegion r, int x, int y, int w, int h){
		addImage(new Sprite(r), x, y, w, h);
	}
	
	public void addImage(Texture t, int x, int y, int w, int h){
		addImage(new Sprite(t), x, y, w, h);
	}
	
	public void addNinePatch(NinePatch n, int x, int y, int w, int h){
		NinePatchImage npi = new NinePatchImage(n, x, y, w, h);
		ninePatches.add(npi);
	}
	
	public void addNinePatch(NinePatchImage n){
		ninePatches.add(n);
	}
	
	public void addTileImage(int tex, boolean block, Rectangle r){
		tileImages.add(new TileImage(tex, block, r));
	}
	
	public void addTileImage(int tex, boolean block, int x, int y, int w, int h){
		tileImages.add(new TileImage(tex, block, x, y, w, h));
	}

	@Override
	public void touchDown() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).ready();
		}
	}

	@Override
	public void touchUp() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setActive();
		}
	}
	
	@Override
	public boolean intersects(Rectangle r){
		boolean temp = false;
		if(BOX.intersects(r)){
			temp = true;
		}else{
			for(int i = 0; i < buttons.size(); i++){
				if(buttons.get(i).intersects(r)){
					temp = true;
					break;
				}
			}
			if(!temp){
				for(int i = 0; i < collisionAreas.size(); i++){
					if(collisionAreas.get(i).intersects(r)){
						temp = true;
						break;
					}
				}
			}
		}
		return temp;
	}
}
