package scripts;

import main.Main;
import main.Script;


public class SCRIPT_switchGrid extends Script{

	@Override
	public void activate(String line) {
		if(Main.showGrid){
			Main.showGrid = false;
		}else{
			Main.showGrid = true;
		}
	}
	
}
