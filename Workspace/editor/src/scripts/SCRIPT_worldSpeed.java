package scripts;

import main.Main;
import main.Script;


public class SCRIPT_worldSpeed extends Script{

	@Override
	public void activate(String line) {
		if(line.equals("+")){
			Main.world.speed++;
		}else{
			Main.world.speed--;
		}
	}
	
}
