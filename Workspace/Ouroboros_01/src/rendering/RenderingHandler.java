package rendering;


import java.util.HashMap;
import java.util.Map;

import ouroboros.OS;
import ouroboros.ProgramNode;
import static ouroboros.ProgramState.*;
import ouroboros.ProgramState;
import rendering.renderers.*;

import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.graphics.GL10.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingHandler extends ProgramNode{
	public static float w = 0;
	public static float h = 0;
	public static float zoom = graphics.getWidth() * 2;
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static HashMap<ProgramState, Renderer> renderers = new HashMap<ProgramState, Renderer>();
	public static float zoomMax = graphics.getWidth() * 4;
	public static float zoomMin = graphics.getWidth() / 4;
	public static float zoomSpeed = 32;
	public static float drawOpacity = 1;
	public static boolean zoomIn = false;
	public static boolean zoomOut = false;
	private static long lastRender = 0;
	private static long renderInterval = 5;
    public static int fps = 120;
    public static Sprite testImg;

	public static void setup(){
		w = graphics.getWidth();
		h = graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		testImg = new Sprite(new Texture(files.internal("data/testImg.png")));
		setupRenderers();
	}
    
	public static void setupRenderers(){
		renderers.put(ProgramState.DEFAULT, new REND_DEFAULT(ProgramState.DEFAULT));
		renderers.put(ProgramState.MENU, new REND_MENU(ProgramState.MENU));
		renderers.put(ProgramState.GAME, new REND_GAME(ProgramState.GAME));
		renderers.put(ProgramState.EDITOR, new REND_EDITOR(ProgramState.EDITOR));
	}
	
	public static void render(){
		if(readyToRender()){
			zoomIn();
			zoomOut();
			newFrame();
			mobileRender();
			staticRender();
		}
	}
	
	public static boolean readyToRender(){
		boolean temp = false;
		renderInterval = 1000 / fps;
		if(lastRender + renderInterval <= System.currentTimeMillis()){
			temp = true;
			lastRender = System.currentTimeMillis();
		}
		return temp;
	}
	
	public static void mobileRender(){
		camera.zoom = zoom;
		prepRender();
		doRender(true);
		endRender();
	}
	
	public static void staticRender(){
		camera.zoom = w;
		prepRender();
		doRender(false);
		endRender();
	}
	
	public static void newFrame(){
		graphics.getGL10().glClearColor(0, 0.05f, 0.05f, 1.0f);
		graphics.getGL10().glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		graphics.getGL10().glDisable(GL_CULL_FACE);
	}
	
	public static void prepRender(){
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		batch.begin();
	}
	
	public static void endRender(){
		batch.end();
	}
	
	public static void doRender(boolean mobile){
		if(mobile){
			getRenderer().mobileRender();
		}else{
			getRenderer().staticRender();
		}
	}
	
	public static void dispose(){
		batch.dispose();
	}
    
    public static float getZoomScale(){
    	return zoom / w;
    }
    
    public static float getZoomScale_In(){
    	return zoom / w - 1;
    }
    
    public static void zoomIn(){
    	if(zoomIn && zoom > zoomMin){
    		zoom -= zoomSpeed;
    	}
    }

    public static void zoomOut(){
    	if(zoomOut && zoom < zoomMax){
    		zoom += zoomSpeed;
    	}
    }

    public static float getScreenX() {
        return -(w / 2);
    }

    public static float getScreenY() {
        return -(h / 2);
    }
    
    public static Renderer getRenderer(){
    	return renderers.get(OS.state);
    }
}
