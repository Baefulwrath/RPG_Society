package scripts;

import main.Main;
import main.Script;


public class SCRIPT_brushSize extends Script{

	@Override
	public void activate(String line) {
		if(line.equals("+")){
			Main.brush.resize(5, 5);
		}else if(Main.brush.getW() > 4){
			Main.brush.resize(-5, -5);
		}
	}
	
}
