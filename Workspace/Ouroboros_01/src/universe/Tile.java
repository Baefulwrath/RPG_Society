package universe;

public abstract class Tile {
	public int TEXTURE = 0;
	public boolean BLOCKED = false;
	
	public Tile(int tex, boolean block){
		TEXTURE = tex;
		BLOCKED = block;
	}
}
