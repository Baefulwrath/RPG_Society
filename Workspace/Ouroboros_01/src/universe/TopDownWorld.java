package universe;

public class TopDownWorld extends World{
	public TopDownTile[][] tiles = new TopDownTile[0][0];
	public int x = 0;
	public int y = 0;
	
	public void getEmptyWorld(int w, int h){
		tiles = new TopDownTile[x][y];
		for(int i = 0; i < x; i++){
			for(int i2 = 0; i2 < y; i2++){
				tiles[i][i2] = new TopDownTile(0, false);
			}
		}
	}
	
	public void getFromfolder(String path, boolean internal){
	}
}
