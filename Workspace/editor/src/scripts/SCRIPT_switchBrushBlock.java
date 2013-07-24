package scripts;

import main.Main;
import main.Script;


public class SCRIPT_switchBrushBlock extends Script{

	@Override
	public void activate(String line) {
		if(Main.brush.block){
			Main.brush.block = false;
		}else{
			Main.brush.block = true;
		}
	}
	
}
