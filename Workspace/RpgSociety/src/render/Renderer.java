package render;

import java.awt.Rectangle;
import java.util.ArrayList;

import ui.Button;
import ui.Menu;
import ui.Message;
import ui.TileImage;
import world.Tile;
import world.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class Renderer {
	public Renderer(String id){
		ID = id;
	}
	public abstract void loadSpecificResources();
	public abstract void mobileRender();
	public abstract void staticRender();
	public String ID;
	
	public void drawWorld(World w){
		for(int x = 0; x < w.TILES.length; x++){
			for(int y = 0; y < w.TILES[x].length; y++){
			}
		}
	}
	
	public void drawWorldRegion(World w, Rectangle region){
		
	}

    public void drawImage(Sprite sprite, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
    		sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
    		sprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += sprite.getWidth() / 2;
    		y += sprite.getHeight() / 2;
    	}
    	sprite.setSize(1, 1);
    	sprite.setScale(scale);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(sprite);
    }
    
    public void drawImage(Sprite sprite, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
    		sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
    		sprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += width / 2;
    		y += height / 2;
    	}
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(sprite);
    }
    
    private void actualDrawImage(Sprite sprite){
    	sprite.draw(Renderinghandler.batch);
    }

    public void drawString(String string, float x, float y, LabelStyle style, float opacity) {
    	y -= style.font.getCapHeight();
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(Renderinghandler.batch, opacity);
    }
    
    public void drawNinePatch(NinePatch img, float x, float y, float width, float height, Color tint){
    	img.setColor(tint);
    	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	img.draw(Renderinghandler.batch, x, y, width, height);
    }
    
    public void drawNinePatch(NinePatch img, Rectangle box, Color tint){
    	img.setColor(tint);
    	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	img.draw(Renderinghandler.batch, box.x, box.y, box.width, box.height);
    }
    
    public void drawButton(Button b){
		drawNinePatch(b.getTex(), b.BOX, Color.WHITE);
		drawString(b.TITLE + " - " + b.ACTIVE + " - " + b.HOVER, b.BOX.x + b.TITLEX, b.BOX.y + b.getTextY(), b.STYLE.LABELSTYLE, 1.0f);
    }
    
    public void drawMenus(ArrayList<Menu> m){
    	for(int i = 0; i < m.size(); i++){
    		drawMenu(m.get(i));
    	}
    }
    
    public void drawMenu(Menu m){
    	for(int i = 0; i < m.ninepatches.size(); i++){
    		m.ninepatches.get(i).draw(Renderinghandler.batch);
    	}
    	for(int i = 0; i < m.labels.size(); i++){
            m.labels.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	for(int i = 0; i < m.images.size(); i++){
            m.images.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	for(int i = 0; i < m.buttons.size(); i++){
    		drawButton(m.buttons.get(i));
    	}
    	for(int i = 0; i < m.tileImages.size(); i++){
    		drawTileImage(m.tileImages.get(i));
    	}
    	if(m.RENDERTITLE){
    		drawString(m.TITLE, m.getTitleX(), m.getTitleY(), Assethandler.titleLabelStyle, m.OPACITY);
    		drawString("----------------", m.getTitleX(), m.getTitleY() - 16, Assethandler.titleLabelStyle, m.OPACITY);
    	}
    }
    
    public void drawTileImage(TileImage t) {
    	
	}
    
	public void drawMessages(ArrayList<Message> m, float x, float y, boolean up){
    	LabelStyle style = Assethandler.messageLabelStyle;
    	for(int i = 0; i < m.size(); i++){
    		if(up){
    			drawString(m.get(i).TEXT, x, y + (i * (style.font.getCapHeight() + 3)), style, 0.5f);
    		}else{
    			drawString(m.get(i).TEXT, x, y - (i * (style.font.getCapHeight() + 3)) - style.font.getCapHeight(), style, 0.5f);
    		}
    	}
    }
	
}
