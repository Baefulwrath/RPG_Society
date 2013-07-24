package scripts;

import main.Main;
import main.Script;


public class SCRIPT_brushZ extends Script{

	@Override
	public void activate(String line) {
		if(line.equals("+")){
			Main.brush.z++;
		}else{
			Main.brush.z--;
		}
	}
	
}
