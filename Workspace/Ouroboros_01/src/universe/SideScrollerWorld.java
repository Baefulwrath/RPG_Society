package universe;

public class SideScrollerWorld extends World{
	public SideScrollerTile[][] tiles = new SideScrollerTile[0][0];
	public int x = 0;
	public int y = 0;
	
	public void getEmptyWorld(int w, int h){
		tiles = new SideScrollerTile[x][y];
		for(int i = 0; i < x; i++){
			for(int i2 = 0; i2 < y; i2++){
				tiles[i][i2] = new SideScrollerTile(0, false);
			}
		}
	}
	
	public void getFromfolder(String path, boolean internal){
	}
}
