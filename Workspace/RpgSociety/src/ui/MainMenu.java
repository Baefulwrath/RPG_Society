package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import render.NinePatchImage;
import render.Renderinghandler;

public abstract class MainMenu extends Menu{
	public MainMenu(String title, String id){
		super(title, id, 200, -100, (int) Renderinghandler.getScreenX(), (int) -Renderinghandler.getScreenY(), (int) Renderinghandler.w, (int) Renderinghandler.h);
		UICT = UICType.MAINMENU;
		RENDERTITLE = true;
	}
	
	public void addMainLabel(String t, int x, int y, LabelStyle s){
		addLabel(t, x + TITLEX, y + TITLEY, s);
	}
	
	public void addMainImage(Sprite s, int x, int y, int w, int h){
		addImage(s, x + TITLEX, y + TITLEY, w, h);
	}

	public void addMainImage(Texture t, int x, int y, int w, int h){
		addImage(t, x + TITLEX, y + TITLEY, w, h);
	}
	
	public void addMainNinePatch(NinePatch n, int x, int y, int w, int h){
		addNinePatch(n, x + TITLEX, y + TITLEY, w, h);
	}
	
	public void addMainNinePatch(NinePatchImage n){
		n.X += TITLEX;
		n.Y += TITLEY;
		addNinePatch(n);
	}

	public void addMainButton(String text, String script, int x, int y, int w, int h, ButtonStyle bs){
		addButton(text, script, x + TITLEX, y + TITLEY, w, h, bs);
	}
}
