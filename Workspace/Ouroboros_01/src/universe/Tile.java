package universe;

public abstract class Tile {
	public int TEXTURE = 0;
	public boolean BLOCKED = false;
	public int x = 0;
	public int y = 0;
	
	public Tile(int tex, boolean block){
		TEXTURE = tex;
		BLOCKED = block;
	}
}
