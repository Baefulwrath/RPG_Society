package scripts;

import main.Main;
import main.Script;


public class SCRIPT_brushSize extends Script{

	@Override
	public void activate(String line) {
		if(line.equals("+")){
			Main.brush.width += 5;
			Main.brush.height += 5;
		}else if(Main.brush.width > 4){
			Main.brush.width -= 5;
			Main.brush.height -= 5;
		}
	}
	
}
