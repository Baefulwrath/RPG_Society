package scripts;

import main.Main;
import main.Script;


public class SCRIPT_switchChunks extends Script{

	@Override
	public void activate(String line) {
		if(Main.showChunks){
			Main.showChunks = false;
		}else{
			Main.showChunks = true;
		}
	}
	
}
