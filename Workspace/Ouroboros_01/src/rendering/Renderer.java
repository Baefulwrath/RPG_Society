package rendering;

import java.awt.Rectangle;
import java.util.ArrayList;

import ouroboros.ProgramState;
import ui.Hud;
import ui.Menu;
import ui.Button;
import ui.Message;
import ui.TextArea;
import universe.SideScrollerWorld;
import universe.Tile;
import universe.TopDownWorld;
import universe.World;

import assets.AnimatedImage;
import assets.AnimatedNinePatch;
import assets.AssetHandler;
import assets.NinePatchImage;
import assets.TileImage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import static rendering.RenderingHandler.*;

public abstract class Renderer {
	public abstract void loadSpecificResources();
	public abstract void mobileRender();
	public abstract void staticRender();
	public ProgramState state = ProgramState.DEFAULT;
	private boolean defCentered = false;
	private boolean defSmooth = false;
	private int defRotation = 0;
	private float defOpacity = 1.0f;
	
	public Renderer(ProgramState s){
		state = s;
	}
	
	protected void drawAnimatedImage(AnimatedImage a){
		Sprite s = a.getImage();
		drawAnimatedImage(a, s.getX(), s.getY(), s.getWidth(), s.getHeight());
	}
	
	protected void drawAnimatedImage(AnimatedImage a, float x, float y, float w, float h){
		drawAnimatedImage(a, x, y, w, h, defRotation);
	}
	
	protected void drawAnimatedImage(AnimatedImage a, float x, float y, float w, float h, int rotation){
		drawAnimatedImage(a, x, y, w, h, rotation, defSmooth, Color.WHITE, defOpacity, defCentered);
	}
	
	protected void drawAnimatedImage(AnimatedImage a, Rectangle r, int rotation){
		drawAnimatedImage(a, r.x, r.y, r.width, r.height, rotation);
	}
	
	protected void drawAnimatedImage(AnimatedImage a, Rectangle r){
		drawAnimatedImage(a, r.x, r.y, r.width, r.height, defRotation);
	}

    protected void drawAnimatedImage(AnimatedImage a, Rectangle r, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	drawAnimatedImage(a, r.x, r.y, r.width, r.height, rotation, smooth, tint, opacity, centered);
    }
	
	protected void drawAnimatedImage(AnimatedImage a, float x, float y, float w, float h, int rotation, boolean smooth, Color tint, float opacity, boolean centered){
		drawImage(a.getImage(), x, y, w, h, rotation, smooth, tint, opacity, centered);
	}
	
	protected void drawAnimatedImage(AnimatedImage a, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered){
		drawImage(a.getImage(), x, y, scale, rotation, smooth, tint, opacity, centered);
	}

    protected void drawImage(Sprite sprite, float x, float y, float w, float h) {
    	drawImage(sprite, x, y, w, h, defRotation, defSmooth, Color.WHITE, defOpacity, defCentered);
    }

    protected void drawImage(Sprite sprite, float x, float y, float w, float h, int rotation) {
    	drawImage(sprite, x, y, w, h, rotation, defSmooth, Color.WHITE, defOpacity, defCentered);
    }

    protected void drawImage(Sprite sprite, Rectangle r, int rotation) {
    	drawImage(sprite, r.x, r.y, r.width, r.height, rotation);
    }

    protected void drawImage(Sprite sprite, Rectangle r) {
    	drawImage(sprite, r.x, r.y, r.width, r.height);
    }

    protected void drawImage(Sprite sprite, Rectangle r, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	drawImage(sprite, r.x, r.y, r.width, r.height, rotation, smooth, tint, opacity, centered);
    }
	
    protected void drawImage(Sprite sprite, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
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
    
    protected void drawImage(Sprite sprite, float x, float y, float w, float h, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
    		sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
    		sprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += w / 2;
    		y += h / 2;
    	}
    	sprite.setSize(1, 1);
    	sprite.setScale(w, h);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(sprite);
    }
    
    private void actualDrawImage(Sprite sprite){
    	sprite.draw(batch);
    }

    protected void drawString(String string, Rectangle r, LabelStyle style, float opacity) {
    	drawString(string, r.x, r.y, style, opacity);
    }

    protected void drawString(String string, float x, float y, LabelStyle style, float opacity) {
    	y -= style.font.getCapHeight();
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(batch, opacity);
    }
    
    protected void drawNinePatch(NinePatch img, float x, float y, float width, float height, Color tint){
    	img.setColor(tint);
    	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	img.draw(batch, x, y, width, height);
    }
    
    protected void drawNinePatch(NinePatch img, Rectangle box, Color tint){
    	img.setColor(tint);
    	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	img.draw(batch, box.x, box.y, box.width, box.height);
    }
    
    protected void drawNinePatches(ArrayList<NinePatch> nps, int x, int y, int w, int h){
    	for(int i = 0; i < nps.size(); i++){
    		drawNinePatch(nps.get(i), x, y, w, h);
    	}
    }
    
    protected void drawNinePatchImages(ArrayList<NinePatchImage> nps){
    	for(int i = 0; i < nps.size(); i++){
    		NinePatchImage n = nps.get(i);
    		drawNinePatchImage(n);
    	}
    }
    
    protected void drawNinePatch(NinePatch n, float x, float y, float w, float h){
    	drawNinePatch(n, x, y, w, h, Color.WHITE);
    }
    
    protected void drawAnimatedNinePatch(AnimatedNinePatch a){
    	drawNinePatchImage(a.getImage());
    }
    
    protected void drawNinePatchImage(NinePatchImage n){
    	drawNinePatch(n.N, n.BOX.x, n.BOX.y, n.BOX.width, n.BOX.height);
    }
    
    protected void drawButton(Button b, float opacity){
    	drawNinePatch(b.getTex(), b.BOX, Color.WHITE);
    	drawString(b.TITLE, b.BOX.x + b.TITLEX, b.BOX.y + b.getTextY(), b.getLabelStyle(), opacity);
    }
    
    protected void drawButtons(ArrayList<Button> bs, float opacity){
    	for(int i = 0; i < bs.size(); i++){
    		drawButton(bs.get(i), opacity);
    	}
    }
    
    protected void drawMenu(Menu m){
    	drawButtons(m.buttons, m.OPACITY);
    	drawTextAreas(m.textAreas, m.OPACITY);
    	if(m.RENDERTITLE){
    		drawString(m.TITLE, m.BOX.x, m.BOX.y, AssetHandler.titleLabelStyle, 1.0f);
    		drawString("_____________________", m.BOX.x, m.BOX.y - 6, AssetHandler.titleLabelStyle, 1.0f);
    	}
    }
    
    protected void drawMessages(ArrayList<Message> m, float x, float y, boolean up){
    	LabelStyle style = AssetHandler.messageLabelStyle;
    	for(int i = 0; i < m.size(); i++){
    		if(up){
    			drawString(m.get(i).TEXT, x, y + (i * (style.font.getCapHeight() + 3)), style, 0.5f);
    		}else{
    			drawString(m.get(i).TEXT, x, y - (i * (style.font.getCapHeight() + 3)) - style.font.getCapHeight(), style, 0.5f);
    		}
    	}
    }
    
    protected void drawTextAreas(ArrayList<TextArea> t, float opacity){
    	for(int i = 0; i < t.size(); i++){
    		drawTextArea(t.get(i), opacity);
    	}
    }
    
    protected void drawTextArea(TextArea ta, float opacity){
    	if(ta.RENDERBACKGROUND){
    		drawImage(ta.BACKGROUND, ta.BOX);
    	}
    	for(int i = 0; i < ta.TEXT.length; i++){
    		ta.TEXT[i].draw(batch, opacity);
    	}
    }
    
    protected void drawHud(Hud h){
    	drawLabels(h.labels, h.OPACITY);
    	drawTextAreas(h.textAreas, h.OPACITY);
    	drawButtons(h.buttons, h.OPACITY);
    	drawNinePatchImages(h.ninePatches);
    	drawTileImages(h.tileImages, h.OPACITY);
    	if(h.RENDERTITLE){
    		drawString(h.TITLE, h.BOX.x, h.BOX.y, AssetHandler.titleLabelStyle, h.OPACITY);
    	}
    }
    
    protected void drawHuds(Hud[] h){
    	for(int i = 0; i < h.length; i++){
    		drawHud(h[i]);
    	}
    }
    
    protected void drawLabels(ArrayList<Label> l, float opacity){
    	for(int i = 0; i < l.size(); i++){
    		drawLabel(l.get(i), opacity);
    	}
    }
    
    protected void drawLabel(Label l, float opacity){
    	l.draw(batch, opacity);
    }
    
    protected void drawTileImages(ArrayList<TileImage> ti, float opacity){
    	for(int i = 0; i < ti.size(); i++){
    		drawTileImage(ti.get(i), opacity);
    	}
    }
    
    protected void drawTileImage(TileImage t, float opacity){
    	drawTile(t, t.BOX.x, t.BOX.y, t.BOX.width, t.BOX.height, opacity);
    }
    
    protected void drawTile(Tile t, int x, int y, int w, int h, float opacity){
//SKRIV FÄRDIGT KODEN HÄR.
    	drawString("TILE", x, y, AssetHandler.debugLabelStyle, opacity);
    }
    
    protected void drawTDWorld(TopDownWorld w){
    	
    }
    
    protected void drawSSWorld(SideScrollerWorld w){
    	
    }
}
