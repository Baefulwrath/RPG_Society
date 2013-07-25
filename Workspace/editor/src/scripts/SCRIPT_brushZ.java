package scripts;

import main.Main;
import main.Script;


public class SCRIPT_brushZ extends Script{

	@Override
	public void activate(String line) {
		if(line.equals("+")){
			if(Main.brush.z < Main.world.tiles[0][0].length){
				Main.brush.z++;
			}
		}else{
			if(Main.brush.z > 0){
				Main.brush.z--;
			}
		}
	}
	
}
