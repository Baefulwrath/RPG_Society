package ui;

import input.Pointer;

import java.awt.Rectangle;
import java.util.ArrayList;

import render.NinePatchImage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class UIComponent {
	
	public UICType UICT = UICType.DEFAULT;
	
	public String TITLE = "UNTITLED";
	public String ID = "NOID";
	public Rectangle BOX = new Rectangle();
	public float OPACITY = 1.0f;
	public boolean RENDERTITLE = false;

	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<Image> images = new ArrayList<Image>();
	public ArrayList<NinePatchImage> ninepatches = new ArrayList<NinePatchImage>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<TileImage> tileImages = new ArrayList<TileImage>();
	public ArrayList<Rectangle> collisionAreas = new ArrayList<Rectangle>();
	
	public UIComponent(String title, String id, int x, int y, int w, int h){
		TITLE = title;
		ID = id;
		BOX = new Rectangle(x, y, w, h);
	}

	public void addLabel(String t, int x, int y, LabelStyle s){
		Label l = new Label(t, s);
		l.setPosition(x + BOX.x, y + BOX.y);
		labels.add(l);
	}
	
	public void addImage(Sprite s, int x, int y, int w, int h){
		Image i = new Image(s);
		i.setBounds(x + BOX.x, y + BOX.y, w, h);
		images.add(i);
	}
	
	public void addImage(Texture t, int x, int y, int w, int h){
		addImage(new Sprite(t), x + BOX.x, y + BOX.y, w, h);
	}
	
	public void addNinePatch(NinePatch n, int x, int y, int w, int h){
		NinePatchImage npi = new NinePatchImage(n, x + BOX.x, y + BOX.y, w, h);
		ninepatches.add(npi);
	}
	
	public void addNinePatch(NinePatchImage n){
		ninepatches.add(n);
	}
	
	public void addButton(String text, String script, int x, int y, int w, int h, ButtonStyle bs){
		buttons.add(new Button(text, script, new Rectangle(x + BOX.x, y + BOX.y, w, h), bs));
	}
	
	public void update(){
		clear();
		setup();
	}
	
	public void clear(){
		labels.clear();
		images.clear();
		ninepatches.clear();
		buttons.clear();
		tileImages.clear();
		collisionAreas.clear();
	}
	
	public abstract void setup();
	
	public void inputUpdate(Pointer p){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).inputUpdate(p);
		}
	}
}
