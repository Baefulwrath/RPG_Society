package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class NPCAssetPack {
	public String title = "";
	public AnimatedImage body_idle;			//slight breathing
	public AnimatedImage body_panting;		//chest movement, panting
	public AnimatedImage head_idle;			//idle, still
	public AnimatedImage head_bobbing;		//slight bobbing
	public AnimatedImage legs_idle;			//standing still
	public AnimatedImage legs_running;		//running
	public AnimatedImage legs_sitting;		//sitting
	public AnimatedImage legs_sneaking;		//sneaking, crouching
	public AnimatedImage legs_walking;		//walking
	public AnimatedImage upper_firing1;		//bow
	public AnimatedImage upper_firing2;		//crossbow, gun-like weapon
	public AnimatedImage upper_firing3;		//magical projectile
	public AnimatedImage upper_idle;		//standing or sitting still
	public AnimatedImage upper_striking1;	//one-handed melee
	public AnimatedImage upper_striking2;	//two-handed melee
	
	public NPCAssetPack(String t, String path){
		loadAP(t, path);
	}
	
	public void loadAP(String t, String path){
		Texture temp = new Texture(Gdx.files.internal(path + "body/" + t + "_idle.png"));
		body_idle = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "body/" + t + "_panting.png"));
		body_panting = new AnimatedImage(temp, temp.getHeight());
		
		temp = new Texture(Gdx.files.internal(path + "head/" + t + "_idle.png"));
		head_idle = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "head/" + t + "_bobbing.png"));
		head_bobbing = new AnimatedImage(temp, temp.getHeight());
		
		temp = new Texture(Gdx.files.internal(path + "legs/" + t + "_idle.png"));
		legs_idle = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "legs/" + t + "_running.png"));
		legs_running = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "legs/" + t + "_sitting.png"));
		legs_sitting = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "legs/" + t + "_sneaking.png"));
		legs_sneaking = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "legs/" + t + "_walking.png"));
		legs_walking = new AnimatedImage(temp, temp.getHeight());

		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_firing1.png"));
		upper_firing1 = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_firing2.png"));
		upper_firing2 = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_firing3.png"));
		upper_firing3 = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_idle.png"));
		upper_idle = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_striking1.png"));
		upper_striking1 = new AnimatedImage(temp, temp.getHeight());
		temp = new Texture(Gdx.files.internal(path + "upper/" + t + "_striking2.png"));
		upper_striking2 = new AnimatedImage(temp, temp.getHeight());
	}
	
//Skicka data om player och få tillbaka en array med bilderna som skal renderas i korrekt ordning.
	public AnimatedImage[] getImage(){
		AnimatedImage[] t = new AnimatedImage[4];
		t[0] = legs_idle;
		t[1] = body_idle;
		t[2] = upper_idle;
		t[3] = head_idle;
		return t;
	}
}
